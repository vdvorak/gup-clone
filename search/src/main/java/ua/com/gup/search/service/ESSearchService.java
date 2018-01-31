package ua.com.gup.search.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.gup.search.model.search.ESCategoriesOffersStatistic;
import ua.com.gup.search.model.search.ESCategoriesStatistic;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface ESSearchService<F> {

    List<ESCategoriesStatistic> countOffersInCategoriesByStatusAndProfileId(String offerStatus, String profileId) throws IOException;

    List<ESCategoriesOffersStatistic> countOffersInCategoriesByStatus(String offerStatus) throws IOException;

    Set<String> suggestByOffersTitlesAndDescriptions(String query) throws IOException;

    Page findIdsByFilter(F filter, Pageable pageable) throws IOException;
}
