package org.atomnuke.util.source;

import org.atomnuke.source.result.AtomSourceResult;
import java.util.LinkedList;
import java.util.Queue;
import org.atomnuke.atom.model.Entry;
import org.atomnuke.source.AtomSourceException;
import org.atomnuke.source.action.ActionType;
import org.atomnuke.source.action.AtomSourceActionImpl;
import org.atomnuke.source.result.AtomSourceResultImpl;

/**
 *
 * @author zinic
 */
public class QueueSourceImpl implements QueueSource {

   private final Queue<Entry> entryQueue;

   public QueueSourceImpl() {
      entryQueue = new LinkedList<Entry>();
   }

   @Override
   public synchronized void put(Entry e) {
      entryQueue.add(e);
   }

   @Override
   public synchronized AtomSourceResult poll() throws AtomSourceException {
      if (entryQueue.isEmpty()) {
         return new AtomSourceResultImpl(new AtomSourceActionImpl(ActionType.SLEEP));
      }

      return new AtomSourceResultImpl(entryQueue.poll());
   }
}
