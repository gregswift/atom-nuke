package net.jps.nuke.listener.eps.selectors;

import java.util.Arrays;
import java.util.List;
import net.jps.nuke.atom.model.Category;
import net.jps.nuke.atom.model.Entry;
import net.jps.nuke.atom.model.Feed;
import net.jps.nuke.listener.eps.selector.Selector;
import net.jps.nuke.listener.eps.selector.SelectorResult;

/**
 *
 * @author zinic
 */
public class CategorySelector implements Selector {

   private final String[] feedTerms;
   private final String[] entryTerms;

   public CategorySelector(String[] entryTerms) {
      this(new String[0], entryTerms);
   }

   public CategorySelector(String[] feedTerms, String[] entryTerms) {
      this.feedTerms = Arrays.copyOf(feedTerms, feedTerms.length);
      this.entryTerms = Arrays.copyOf(entryTerms, entryTerms.length);
   }

   public static boolean hasCategoryTerm(String[] termsToSearchThrough, List<Category> categories) {
      for (Category category : categories) {
         if (hasCategoryTerm(termsToSearchThrough, category.term())) {
            return true;
         }
      }

      return false;
   }

   public static boolean hasCategoryTerm(String[] termsToSearchThrough, String termToFind) {
      if (termToFind != null) {
         for (String targetCategory : termsToSearchThrough) {
            if (targetCategory.equals(termToFind)) {
               return true;
            }
         }
      }

      return false;
   }

   @Override
   public SelectorResult select(Feed feed) {
      if (feedTerms.length == 0 || hasCategoryTerm(feedTerms, feed.categories())) {
         return SelectorResult.PROCESS;
      }

      return SelectorResult.PASS;
   }

   @Override
   public SelectorResult select(Entry entry) {
      if (entryTerms.length == 0 || hasCategoryTerm(entryTerms, entry.categories())) {
         return SelectorResult.PROCESS;
      }

      return SelectorResult.PASS;
   }
}
