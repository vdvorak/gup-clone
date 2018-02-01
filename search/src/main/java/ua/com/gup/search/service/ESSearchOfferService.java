package ua.com.gup.search.service;

import ua.com.gup.search.model.search.ESCategoriesStatistic;
import ua.com.gup.search.model.search.ESOffer;
import ua.com.gup.search.model.filter.OfferFilter;
import ua.com.gup.search.util.Locale;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface ESSearchOfferService extends ESSearchService<OfferFilter> {

    Iterable<ESOffer> findByQueryAndCategoriesIds(String query, Integer[] categoriesIds) throws IOException;

    List<ESCategoriesStatistic> countOffersInCategoriesByQuery(String query, Locale locale) throws IOException;

    Set<String> suggestByOffersTitlesAndDescriptions(String query) throws IOException;

}
