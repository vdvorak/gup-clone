package ua.com.gup.search.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ua.com.gup.search.model.ESCategoriesOffersStatistic;
import ua.com.gup.search.model.ESCategoriesStatistic;
import ua.com.gup.search.model.filter.rent.RentOfferFilter;
import ua.com.gup.search.repository.ESRentOfferRepository;
import ua.com.gup.search.service.ESSearchRentOfferService;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class ESSearchRentOfferServiceImpl implements ESSearchRentOfferService {

    @Autowired
    private ESRentOfferRepository esOfferRepository;

    @Override
    public List<ESCategoriesStatistic> countOffersInCategoriesByStatusAndProfileId(String offerStatus, String profileId) throws IOException {
        return esOfferRepository.countOffersInCategoriesByStatusAndProfileId(offerStatus, profileId);
    }

    @Override
    public Page findIdsByFilter(RentOfferFilter offerFilter, Pageable pageable) throws IOException {
        return esOfferRepository.findIdsByFilter(offerFilter, pageable);
    }

    @Override
    public List<ESCategoriesOffersStatistic> countOffersInCategoriesByStatus(String offerStatus) throws IOException {
        return esOfferRepository.countOffersInCategoriesByStatus(offerStatus);
    }

    @Override
    public Set<String> suggestByOffersTitlesAndDescriptions(String query) throws IOException {
        return Collections.EMPTY_SET;
    }

}
