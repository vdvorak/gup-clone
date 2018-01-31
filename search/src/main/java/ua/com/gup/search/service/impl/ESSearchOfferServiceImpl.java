package ua.com.gup.search.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.com.gup.search.model.search.ESCategoriesOffersStatistic;
import ua.com.gup.search.model.search.ESCategoriesStatistic;
import ua.com.gup.search.model.search.ESOffer;
import ua.com.gup.search.model.filter.OfferFilter;
import ua.com.gup.search.repository.ESCategoryRepository;
import ua.com.gup.search.repository.ESOfferRepository;
import ua.com.gup.search.service.ESSearchOfferService;
import ua.com.gup.search.util.Locale;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ESSearchOfferServiceImpl implements ESSearchOfferService {

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
    public List<ESCategoriesOffersStatistic> countOffersInCategoriesByStatus(String offerStatus) throws IOException {
        return esOfferRepository.countOffersInCategoriesByStatus(offerStatus);
    }

    @Override
    public Page findIdsByFilter(OfferFilter filter, Pageable pageable) throws IOException {
        return new PageImpl(esOfferRepository.findOffersIdsByFilter(filter).stream().collect(Collectors.toList()));
    }

    @Override
    public Set<String> suggestByOffersTitlesAndDescriptions(String query) throws IOException {
        return esOfferRepository.suggestByOffersTitlesAndDescriptions(query);
    }

}
