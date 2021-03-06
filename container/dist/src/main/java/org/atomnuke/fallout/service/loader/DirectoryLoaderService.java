package org.atomnuke.fallout.service.loader;

import org.atomnuke.container.packaging.loader.PackageLoader;
import java.io.File;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.atomnuke.container.packaging.bindings.BindingEnvironmentFactory;
import org.atomnuke.container.packaging.bindings.PackageLoadingException;
import org.atomnuke.container.packaging.DeployedPackage;
import org.atomnuke.container.packaging.PackageContext;
import org.atomnuke.container.packaging.Unpacker;
import org.atomnuke.container.packaging.UnpackerException;
import org.atomnuke.container.packaging.archive.zip.ArchiveExtractor;
import org.atomnuke.container.packaging.bindings.environment.BindingEnvironmentManagerImpl;
import org.atomnuke.container.packaging.loader.impl.BindingAwarePackageLoader;
import org.atomnuke.container.packaging.resource.ResourceManager;
import org.atomnuke.container.packaging.resource.ResourceManagerImpl;
import org.atomnuke.container.service.annotation.NukeBootstrap;
import org.atomnuke.container.service.annotation.Requires;
import org.atomnuke.plugin.ReferenceInstantiationException;
import org.atomnuke.service.ServiceManager;
import org.atomnuke.service.ServiceContext;
import org.atomnuke.service.gc.ReclamationHandler;
import org.atomnuke.lifecycle.InitializationException;
import org.atomnuke.service.runtime.AbstractRuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author zinic
 */
@NukeBootstrap
@Requires(ReclamationHandler.class)
public class DirectoryLoaderService extends AbstractRuntimeService {

   private static final String LOADER_SVC_NAME = "org.atomnuke.container.packaging.loader.impl.DirectoryLoaderService";
   private static final Logger LOG = LoggerFactory.getLogger(DirectoryLoaderService.class);

   private final BindingEnvironmentFactory bindingEnvFactory;
   private final ResourceManager rootResourceManager;

   private File deploymentDirectory, libraryDirectory;
   private PackageLoader packageLoader;

   public DirectoryLoaderService() {
      super(PackageLoader.class);

      rootResourceManager = new ResourceManagerImpl();
      bindingEnvFactory = new BindingEnvironmentManagerImpl(rootResourceManager);
   }

   @Override
   public String name() {
      return LOADER_SVC_NAME;
   }

   @Override
   public Object instance() {
      return packageLoader;
   }

   @Override
   public void init(ServiceContext sc) throws InitializationException {
      LOG.info("Directory package loader service starting.");

      deploymentDirectory = new File(sc.environment().deploymentDirectory());
      libraryDirectory = new File(sc.environment().libraryDirectory());
      packageLoader = new BindingAwarePackageLoader(bindingEnvFactory);

      try {
         loadPackages();
         loadServices(sc.serviceManager());
      } catch (PackageLoadingException ple) {
         LOG.error(ple.getMessage(), ple);
      }
   }

   @Override
   public void destroy() {
      LOG.info("Directory package loader service stopping.");
   }

   private void loadServices(ServiceManager serviceManager) {
      for (PackageContext loadedPackage : packageLoader.packageContexts()) {
         try {
            // Gather services from the package
            gatherServices(serviceManager, loadedPackage);
         } catch (ReferenceInstantiationException bie) {
            LOG.error("Failed to init services.");
         }
      }
   }

   private void gatherServices(ServiceManager serviceManager, PackageContext pkgContext) throws ReferenceInstantiationException {
      pkgContext.packageBindings().resolveServices(serviceManager);
      serviceManager.resolve();
   }

   private void loadPackages() throws PackageLoadingException {
      final Unpacker archiveUnpacker = new ArchiveExtractor(deploymentDirectory);

      if (!libraryDirectory.exists()) {
         if (!libraryDirectory.mkdirs()) {
            throw new PackageLoadingException("Unable to make library directory: " + libraryDirectory.getAbsolutePath());
         }
      }

      if (!libraryDirectory.isDirectory()) {
         throw new PackageLoadingException(libraryDirectory.getAbsolutePath() + " is not a valid library directory.");
      }

      final List<DeployedPackage> deployedPackages = new LinkedList<DeployedPackage>();

      for (File archive : libraryDirectory.listFiles()) {
         if (!archive.isDirectory()) {
            final DeployedPackage nextPackage = loadFile(archive, archiveUnpacker);

            if (nextPackage != null) {
               deployedPackages.add(nextPackage);
            }
         }

         // Ignore directories for now
      }

      // Load the root context first
      packageLoader.load(UUID.randomUUID() + ".ROOT", rootResourceManager);

      // Load all of the isolate contexts
      for (DeployedPackage deployedPackage : deployedPackages) {
         try {
            LOG.info("Loading package \"" + deployedPackage.archiveUri().toString() + "\"");
            packageLoader.load(deployedPackage.archiveUri().toString(), deployedPackage.resourceManager());

            LOG.info("Package \"" + deployedPackage.archiveUri().toString() + "\" loaded");
         } catch (PackageLoadingException ple) {
            LOG.error("Failed to load package (" + deployedPackage.archiveUri().toString() + ") - Reason: " + ple.getMessage(), ple);
         }
      }
   }

   private DeployedPackage loadFile(File archive, final Unpacker archiveUnpacker) {
      final URI archiveUri = archive.toURI();

      if (archiveUnpacker.canUnpack(archiveUri)) {
         LOG.info("Extracting package \"" + archiveUri.toString() + "\"");

         try {
            return archiveUnpacker.unpack(rootResourceManager, archiveUri);
         } catch (UnpackerException ue) {
            LOG.error("Failed to unpack package (" + archive.getAbsolutePath() + ") - Reason: " + ue.getMessage(), ue);
         }
      }

      return null;
   }
}
