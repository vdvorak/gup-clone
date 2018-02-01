package ua.com.gup.search.service;

import ua.com.gup.search.model.ESCategoriesOffersStatistic;
import ua.com.gup.search.model.filter.rent.RentOfferCalculateRentPriceFilter;
import ua.com.gup.search.model.filter.rent.RentOfferFilter;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ESSearchRentOfferService extends ESSearchService<RentOfferFilter> {

    void indexRentOffer(Map<String, Object> o) throws IOException;

    void clearRentOfferIndex() throws IOException;

    void indexRentOfferCalendars(String rentOfferId, Map<String, Object> rentOfferCalendarMap) throws IOException;

    List<ESCategoriesOffersStatistic> countOffersInCategoriesByStatus(String offerStatus) throws IOException;

    Integer calculateRentPrice(RentOfferCalculateRentPriceFilter filter) throws IOException;
}
