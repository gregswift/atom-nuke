package org.atomnuke.atom.model.impl;

import java.util.List;
import org.atomnuke.atom.model.Author;
import org.atomnuke.atom.model.Category;
import org.atomnuke.atom.model.Generator;
import org.atomnuke.atom.model.Id;
import org.atomnuke.atom.model.Icon;
import org.atomnuke.atom.model.Link;
import org.atomnuke.atom.model.Logo;
import org.atomnuke.atom.model.Rights;
import org.atomnuke.atom.model.Source;
import org.atomnuke.atom.model.Subtitle;
import org.atomnuke.atom.model.Title;
import org.atomnuke.atom.model.Updated;

/**
 *
 * @author zinic
 */
public class SourceImpl extends AtomCommonAttributesImpl implements Source {

   private List<Author> authors;
   private List<Category> categories;
   private List<Link> links;
   private Generator generator;
   private Icon icon;
   private Id id;
   private Logo logo;
   private Rights rights;
   private Subtitle subtitle;
   private Title title;
   private Updated updated;

   public void setAuthors(List<Author> authors) {
      this.authors = authors;
   }

   public void setCategories(List<Category> categories) {
      this.categories = categories;
   }

   public void setLinks(List<Link> links) {
      this.links = links;
   }

   public void setGenerator(Generator generator) {
      this.generator = generator;
   }

   public void setIcon(Icon icon) {
      this.icon = icon;
   }

   public void setId(Id id) {
      this.id = id;
   }

   public void setLogo(Logo logo) {
      this.logo = logo;
   }

   public void setRights(Rights rights) {
      this.rights = rights;
   }

   public void setSubtitle(Subtitle subtitle) {
      this.subtitle = subtitle;
   }

   public void setTitle(Title title) {
      this.title = title;
   }

   public void setUpdated(Updated updated) {
      this.updated = updated;
   }

   @Override
   public List<Author> authors() {
      return authors;
   }

   @Override
   public List<Category> categories() {
      return categories;
   }

   @Override
   public Generator generator() {
      return generator;
   }

   @Override
   public Icon icon() {
      return icon;
   }

   @Override
   public Id id() {
      return id;
   }

   @Override
   public List<Link> links() {
      return links;
   }

   @Override
   public Logo logo() {
      return logo;
   }

   @Override
   public Rights rights() {
      return rights;
   }

   @Override
   public Subtitle subtitle() {
      return subtitle;
   }

   @Override
   public Title title() {
      return title;
   }

   @Override
   public Updated updated() {
      return updated;
   }
}
