package org.atomnuke.container.packaging;

import org.atomnuke.container.packaging.bindings.PackageBindings;

/**
 *
 * @author zinic
 */
public interface PackageContext {

   String name();

   PackageBindings packageBindings();
}
