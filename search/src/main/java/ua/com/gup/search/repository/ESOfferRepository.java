package ua.com.gup.search.repository;

import ua.com.gup.search.model.ESCategoriesStatistic;
import ua.com.gup.search.model.ESOffer;

import java.io.IOException;
import java.util.List;

public interface ESOfferRepository {



    List<ESCategoriesStatistic> countAggregatedOffersCategories(String query) throws IOException;

    Iterable<ESOffer> findByQueryAndCategoriesIds(String query, Integer[] categoriesIds);

    List<String> suggestByOffersTitlesAndDescriptions(String query);

    List<ESCategoriesStatistic> countOffersInCategoriesByStatusAndProfileId(String offerStatus, String profileId) throws IOException;
}
