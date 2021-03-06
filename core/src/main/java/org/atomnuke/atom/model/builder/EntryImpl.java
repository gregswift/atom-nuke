package org.atomnuke.atom.model.builder;

import java.util.List;
import org.atomnuke.atom.model.Author;
import org.atomnuke.atom.model.Category;
import org.atomnuke.atom.model.Content;
import org.atomnuke.atom.model.Contributor;
import org.atomnuke.atom.model.Entry;
import org.atomnuke.atom.model.Id;
import org.atomnuke.atom.model.Link;
import org.atomnuke.atom.model.Published;
import org.atomnuke.atom.model.Rights;
import org.atomnuke.atom.model.Source;
import org.atomnuke.atom.model.Summary;
import org.atomnuke.atom.model.Title;
import org.atomnuke.atom.model.Updated;


/**
 *
 * @author zinic
 */
class EntryImpl extends AtomCommonAttributesImpl implements Entry {

   private List<Author> authors;
   private List<Contributor> contributors;
   private List<Category> categories;
   private List<Link> links;
   private Id id;
   private Rights rights;
   private Title title;
   private Updated updated;
   private Content content;
   private Summary summary;
   private Published published;
   private Source source;

   public void setAuthors(List<Author> authors) {
      this.authors = authors;
   }

   public void setContributors(List<Contributor> contributors) {
      this.contributors = contributors;
   }

   public void setCategories(List<Category> categories) {
      this.categories = categories;
   }

   public void setLinks(List<Link> links) {
      this.links = links;
   }

   public void setId(Id id) {
      this.id = id;
   }

   public void setRights(Rights rights) {
      this.rights = rights;
   }

   public void setTitle(Title title) {
      this.title = title;
   }

   public void setUpdated(Updated updated) {
      this.updated = updated;
   }

   public void setContent(Content content) {
      this.content = content;
   }

   public void setSummary(Summary summary) {
      this.summary = summary;
   }

   public void setPublished(Published published) {
      this.published = published;
   }

   public void setSource(Source source) {
      this.source = source;
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
   public Content content() {
      return content;
   }

   @Override
   public List<Contributor> contributors() {
      return contributors;
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
   public Published published() {
      return published;
   }

   @Override
   public Rights rights() {
      return rights;
   }

   @Override
   public Source source() {
      return source;
   }

   @Override
   public Summary summary() {
      return summary;
   }

   @Override
   public Title title() {
      return title;
   }

   @Override
   public Updated updated() {
      return updated;
   }

   @Override
   public int hashCode() {
      int hash = 5;

      hash = 37 * hash + (this.authors != null ? this.authors.hashCode() : 0);
      hash = 37 * hash + (this.contributors != null ? this.contributors.hashCode() : 0);
      hash = 37 * hash + (this.categories != null ? this.categories.hashCode() : 0);
      hash = 37 * hash + (this.links != null ? this.links.hashCode() : 0);

      hash = 13 * hash + (this.id != null ? this.id.hashCode() : 0);
      hash = 13 * hash + (this.rights != null ? this.rights.hashCode() : 0);
      hash = 13 * hash + (this.title != null ? this.title.hashCode() : 0);
      hash = 13 * hash + (this.updated != null ? this.updated.hashCode() : 0);
      hash = 13 * hash + (this.content != null ? this.content.hashCode() : 0);
      hash = 13 * hash + (this.summary != null ? this.summary.hashCode() : 0);
      hash = 13 * hash + (this.published != null ? this.published.hashCode() : 0);
      hash = 13 * hash + (this.source != null ? this.source.hashCode() : 0);

      return hash + super.hashCode();
   }
}
