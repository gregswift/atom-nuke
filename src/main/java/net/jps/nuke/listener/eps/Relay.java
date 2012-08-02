package net.jps.nuke.listener.eps;

import java.util.LinkedList;
import java.util.List;
import net.jps.nuke.atom.model.Entry;
import net.jps.nuke.atom.model.Feed;
import net.jps.nuke.listener.AtomListener;
import net.jps.nuke.listener.AtomListenerException;
import net.jps.nuke.listener.AtomListenerResult;
import net.jps.nuke.listener.ListenerResult;
import net.jps.nuke.listener.eps.eventlet.AtomEventlet;
import net.jps.nuke.listener.eps.selector.DefaultSelector;
import net.jps.nuke.listener.eps.selector.Selector;
import net.jps.nuke.task.context.TaskContext;
import net.jps.nuke.task.lifecycle.DestructionException;
import net.jps.nuke.task.lifecycle.InitializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * EPS Relay - The event processing system relay
 *
 * "Oh, yes! For humans, touch can connect you to an object in a very personal
 * way; make it seem more real."
 *
 * -Jean-Luc Picard
 *
 * @author zinic
 */
public class Relay implements AtomListener, AtomEventHandlerRelay {

   private static final Logger LOG = LoggerFactory.getLogger(Relay.class);
   private final List<HandlerConduit> epsConduits;
   private TaskContext taskCtx;

   public Relay() {
      epsConduits = new LinkedList<HandlerConduit>();
   }

   private static void destroyConduit(TaskContext tc, HandlerConduit conduit) {
      try {
         conduit.destroy(tc);
      } catch (DestructionException de) {
         LOG.error("Exception caught while destroying AtomEventHandler. Reason: " + de.getMessage(), de);
      }
   }

   private synchronized List<HandlerConduit> copyConduits() {
      return new LinkedList<HandlerConduit>(epsConduits);
   }

   private synchronized boolean removeConduit(HandlerConduit conduit) {
      destroyConduit(taskCtx, conduit);

      return epsConduits.remove(conduit);
   }

   @Override
   public void enlistHandler(AtomEventlet handler) throws InitializationException {
      enlistHandler(handler, DefaultSelector.INSTANCE);
   }

   @Override
   public synchronized void enlistHandler(AtomEventlet handler, Selector selector) throws InitializationException {
      handler.init(taskCtx);

      epsConduits.add(new HandlerConduit(handler, selector));
   }

   @Override
   public void init(TaskContext tc) throws InitializationException {
      taskCtx = tc;
   }

   @Override
   public void destroy(TaskContext tc) throws DestructionException {
      for (HandlerConduit conduit : epsConduits) {
         destroyConduit(tc, conduit);
      }

      epsConduits.clear();
   }

   @Override
   public ListenerResult entry(Entry entry) throws AtomListenerException {
      for (HandlerConduit conduit : copyConduits()) {
         switch (conduit.select(entry)) {
            case HALT:
               removeConduit(conduit);
               break;

            default:
         }
      }

      return AtomListenerResult.ok();
   }

   @Override
   public ListenerResult feedPage(Feed page) throws AtomListenerException {
      for (HandlerConduit conduit : copyConduits()) {
         switch (conduit.select(page)) {
            case HALT:
               removeConduit(conduit);
               break;

            default:
         }
      }

      return AtomListenerResult.ok();
   }
}
