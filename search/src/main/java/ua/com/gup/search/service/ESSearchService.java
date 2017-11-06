package ua.com.gup.search.service;

import ua.com.gup.search.model.ESCategoriesStatistic;
import ua.com.gup.search.model.ESOffer;
import ua.com.gup.search.util.Locale;

import java.io.IOException;
import java.util.List;

public interface ESSearchService {


    Iterable<ESOffer> findByQueryAndCategoriesIds(String query, Integer[] categoriesIds) throws IOException;

    List<ESCategoriesStatistic> countOffersInCategoriesByQuery(String query, Locale locale) throws IOException;

    List<ESCategoriesStatistic> countOffersInCategoriesByStatusAndProfileId (String offerStatus, String profileId) throws IOException;

    List<String> suggestByOffersTitlesAndDescriptions(String query) throws IOException;
}
