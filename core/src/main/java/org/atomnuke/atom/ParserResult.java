package org.atomnuke.atom;

import org.atomnuke.atom.model.Entry;
import org.atomnuke.atom.model.Feed;


/**
 * @deprecated org.atomnuke.atom.io replaces this package
 *
 * @author zinic
 */

@Deprecated
public interface ParserResult {

   Feed getFeed();

   Entry getEntry();
}