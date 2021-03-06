package org.atomnuke.atom.model.builder;

import org.atomnuke.atom.model.Link;

/**
 *
 * @author zinic
 */
class LinkImpl extends AtomCommonAttributesImpl implements Link {

   private String href, rel, type, hreflang, title;
   private Integer length;

   public void setHref(String href) {
      this.href = href;
   }

   public void setRel(String rel) {
      this.rel = rel;
   }

   public void setType(String type) {
      this.type = type;
   }

   public void setHreflang(String hreflang) {
      this.hreflang = hreflang;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public void setLength(Integer length) {
      this.length = length;
   }

   @Override
   public String href() {
      return href;
   }

   @Override
   public String rel() {
      return rel;
   }

   @Override
   public String type() {
      return type;
   }

   @Override
   public String hreflang() {
      return hreflang;
   }

   @Override
   public String title() {
      return title;
   }

   @Override
   public Integer length() {
      return length;
   }

   @Override
   public int hashCode() {
      int hash = 7;

      hash = 31 * hash + (this.href != null ? this.href.hashCode() : 0);
      hash = 31 * hash + (this.rel != null ? this.rel.hashCode() : 0);
      hash = 31 * hash + (this.type != null ? this.type.hashCode() : 0);
      hash = 31 * hash + (this.hreflang != null ? this.hreflang.hashCode() : 0);
      hash = 31 * hash + (this.title != null ? this.title.hashCode() : 0);
      hash = 31 * hash + (this.length != null ? this.length.hashCode() : 0);

      return hash + super.hashCode();
   }
}
