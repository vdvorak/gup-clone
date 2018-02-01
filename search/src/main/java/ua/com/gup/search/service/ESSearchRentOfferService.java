package ua.com.gup.search.service;

import ua.com.gup.search.model.filter.RentOfferCalculateRentPriceFilter;
import ua.com.gup.search.model.filter.RentOfferFilter;
import ua.com.gup.search.model.search.ESCategoriesOffersStatistic;
import ua.com.gup.search.model.search.ESCategoriesStatistic;
import ua.com.gup.search.model.search.ESRentOfferRentPrice;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ESSearchRentOfferService extends ESSearchService<RentOfferFilter> {

    void indexRentOffer(Map<String, Object> o) throws IOException;

    void clearRentOfferIndex() throws IOException;

    void indexRentOfferCalendars(String rentOfferId, Map<String, Object> rentOfferCalendarMap) throws IOException;

    List<ESCategoriesOffersStatistic> countOffersInCategoriesByStatus(String offerStatus) throws IOException;

    ESRentOfferRentPrice calculateRentPrice(RentOfferCalculateRentPriceFilter filter) throws IOException;
}
