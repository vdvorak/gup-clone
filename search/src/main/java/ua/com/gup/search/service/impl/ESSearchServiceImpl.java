package ua.com.gup.search.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.search.model.ESCategoriesStatistic;
import ua.com.gup.search.model.ESOffer;
import ua.com.gup.search.repository.ESCategoryRepository;
import ua.com.gup.search.repository.ESOfferRepository;
import ua.com.gup.search.service.ESSearchService;
import ua.com.gup.search.util.Locale;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
public class ESSearchServiceImpl implements ESSearchService {

    @Autowired
    private ESOfferRepository esOfferRepository;
    @Autowired
    private ESCategoryRepository esCategoryRepository;


    @Override
    public Iterable<ESOffer> findByQueryAndCategoriesIds(String query, Integer[] categoriesIds) throws IOException {
        return esOfferRepository.findByQueryAndCategoriesIds(query, categoriesIds);
    }

    @Override
    public List<ESCategoriesStatistic> countOffersInCategoriesByQuery(String query, Locale locale) throws IOException {
        List<ESCategoriesStatistic> categories = esOfferRepository.countAggregatedOffersCategories(query);
        for (ESCategoriesStatistic c : categories) {
            String title = esCategoryRepository.findOneByCode(c.getCode(), locale).getTitle();
            c.setTitle(title);
        }
        return categories;
    }

    @Override
    public List<ESCategoriesStatistic> countOffersInCategoriesByStatusAndProfileId(String offerStatus, String profileId) throws IOException {
        return esOfferRepository.countOffersInCategoriesByStatusAndProfileId(offerStatus, profileId);
    }

    @Override
    public Set<String> suggestByOffersTitlesAndDescriptions(String query) throws IOException {
        return esOfferRepository.suggestByOffersTitlesAndDescriptions(query);
    }

}
