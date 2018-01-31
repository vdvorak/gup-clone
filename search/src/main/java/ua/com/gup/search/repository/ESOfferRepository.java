package ua.com.gup.search.repository;

import ua.com.gup.search.model.search.ESCategoriesOffersStatistic;
import ua.com.gup.search.model.search.ESCategoriesStatistic;
import ua.com.gup.search.model.search.ESOffer;
import ua.com.gup.search.model.filter.OfferFilter;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface ESOfferRepository {



    List<ESCategoriesStatistic> countAggregatedOffersCategories(String query) throws IOException;

    Iterable<ESOffer> findByQueryAndCategoriesIds(String query, Integer[] categoriesIds);

    Set<String> suggestByOffersTitlesAndDescriptions(String query) throws IOException;

    List<ESCategoriesStatistic> countOffersInCategoriesByStatusAndProfileId(String offerStatus, String profileId) throws IOException;

    List<ESCategoriesOffersStatistic> countOffersInCategoriesByStatus(String offerStatus) throws IOException;

    Set<String> findOffersIdsByFilter(OfferFilter filter) throws IOException;
}
