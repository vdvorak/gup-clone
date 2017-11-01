package ua.com.gup.search.service;

import ua.com.gup.search.model.ESCategoriesCount;
import ua.com.gup.search.model.ESOffer;
import ua.com.gup.search.util.Locale;

import java.io.IOException;
import java.util.List;

public interface ESSearchService {


    Iterable<ESOffer> findByQueryAndCategoriesIds(String query, Integer[] categoriesIds) throws IOException;

    List<ESCategoriesCount> countMatchesInCategories(String query, Locale locale) throws IOException;
}
