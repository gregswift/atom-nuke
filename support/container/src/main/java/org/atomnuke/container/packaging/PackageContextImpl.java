package org.atomnuke.container.packaging;

import java.net.URI;
import org.atomnuke.container.packaging.bindings.PackageBindings;

/**
 *
 * @author zinic
 */
public class PackageContextImpl implements PackageContext {

   private final PackageBindings bindingContextManager;
   private final String name;

   public PackageContextImpl(PackageBindings bindingContextManager, String name) {
      this.bindingContextManager = bindingContextManager;
      this.name = name;
   }

   @Override
   public String name() {
      return name;
   }

   @Override
   public PackageBindings packageBindings() {
      return bindingContextManager;
   }
}
