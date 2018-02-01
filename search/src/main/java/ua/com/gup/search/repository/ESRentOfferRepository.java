package ua.com.gup.search.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.gup.search.model.ESCategoriesOffersStatistic;
import ua.com.gup.search.model.ESCategoriesStatistic;
import ua.com.gup.search.model.filter.rent.RentOfferCalculateRentPriceFilter;
import ua.com.gup.search.model.filter.rent.RentOfferFilter;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ESRentOfferRepository {

    List<ESCategoriesStatistic> countOffersInCategoriesByStatusAndProfileId(String offerStatus, String profileId) throws IOException;

    Page findIdsByFilter(RentOfferFilter offerFilter, Pageable pageable) throws IOException;

    List<ESCategoriesOffersStatistic> countOffersInCategoriesByStatus(String offerStatus) throws IOException;

    void indexRentOffer(Map<String, Object> map) throws IOException;

    void indexRentOfferCalendars(String rentOfferId, Map<String, Object> rentOfferCalendarMap) throws IOException;

    void clearRentOfferIndex() throws IOException;

    Set<String> suggestByOffersTitlesAndDescriptions(String query) throws IOException;

    Integer calculateRentPrice(RentOfferCalculateRentPriceFilter filter) throws IOException;
}
