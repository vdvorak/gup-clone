package ua.com.gup.search.repository;

import ua.com.gup.search.model.ESCategoriesCount;
import ua.com.gup.search.model.ESOffer;

import java.io.IOException;
import java.util.List;

public interface ESOfferRepository {



    List<ESCategoriesCount> countAggregatedOffersCategories(String query) throws IOException;

    Iterable<ESOffer> findByQueryAndCategoriesIds(String query, Integer[] categoriesIds);
}
