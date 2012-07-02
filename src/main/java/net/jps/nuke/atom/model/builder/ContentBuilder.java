package net.jps.nuke.atom.model.builder;

import java.net.URI;
import net.jps.nuke.atom.model.Content;
import net.jps.nuke.atom.model.impl.ContentImpl;

/**
 *
 * @author zinic
 */
public class ContentBuilder extends ContentImpl {

   public static ContentBuilder newBuilder() {
      return new ContentBuilder();
   }

   protected ContentBuilder() {
      value = new StringBuilder();
   }

   public Content build() {
      return this;
   }

   public void setType(String type) {
      this.type = type;
   }

   public void setSrc(String src) {
      this.src = src;
   }

   public StringBuilder getValueBuilder() {
      return this.value;
   }

   public void setBase(URI base) {
      this.base = base;
   }

   public void setLang(String lang) {
      this.lang = lang;
   }
}