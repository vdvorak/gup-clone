package ua.com.gup.search.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.gup.search.model.ESCategoriesOffersStatistic;
import ua.com.gup.search.model.filter.rent.RentOfferFilter;

import java.io.IOException;
import java.util.List;

public interface ESSearchRentOfferService extends ESSearchService<RentOfferFilter> {

    List<ESCategoriesOffersStatistic> countOffersInCategoriesByStatus(String offerStatus) throws IOException;
}
