package org.atomnuke.atom.sax;

import org.atomnuke.atom.xml.AtomElement;

/**
 * @deprecated org.atomnuke.atom.io replaces this package
 *
 * @author zinic
 */

@Deprecated
public class HandlerContext<T> {

   private final AtomElement elementDef;
   private final T builder;

   public HandlerContext(AtomElement elementDef, T builder) {
      this.elementDef = elementDef;
      this.builder = builder;
   }

   public AtomElement getElementDef() {
      return elementDef;
   }

   public T builder() {
      return builder;
   }
}