package org.atomnuke.atom.sax;

import org.atomnuke.atom.xml.AtomElement;

/**
 * @deprecated org.atomnuke.atom.io replaces this package
 *
 * @author zinic
 */

@Deprecated
public class InvalidStateException extends IllegalStateException {

   private AtomElement invalidElement;

   public InvalidStateException(AtomElement invalidElement, String s) {
      super(s);
      this.invalidElement = invalidElement;
   }

   public AtomElement getInvalidElement() {
      return invalidElement;
   }
}