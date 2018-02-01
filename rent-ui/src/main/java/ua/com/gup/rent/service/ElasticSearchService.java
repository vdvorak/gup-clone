package ua.com.gup.rent.service;

import org.springframework.data.domain.Pageable;
import ua.com.gup.rent.model.mongo.rent.RentOffer;
import ua.com.gup.rent.model.mongo.rent.calendar.RentOfferCalendarChild;
import ua.com.gup.rent.service.dto.rent.offer.filter.RentOfferFilterDTO;
import ua.com.gup.rent.service.dto.search.CategoryOffersStatistic;

import java.util.List;
import java.util.Map;

public interface ElasticSearchService {


    void indexRentOffer(RentOffer rentOffer);

    void reIndexRentOffers();

    void indexRentOfferCalendars(RentOffer rentOffer);

    void indexRentOfferCalendars(RentOffer rentOffer, RentOfferCalendarChild... calendars);

    Map<String, Object> findIdsPageByFilter(RentOfferFilterDTO offerFilter, Pageable pageable);

    List<CategoryOffersStatistic> countOffersInCategoriesByStatus(String status);

    List<CategoryOffersStatistic> countOffersInCategoriesByFilter(String status, String profileId);

    Map calculatePrice(RentOfferFilterDTO offerFilter);

    String[] findSuggests(String query);
}
