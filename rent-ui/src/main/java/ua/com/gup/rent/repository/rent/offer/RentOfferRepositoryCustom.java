package ua.com.gup.rent.repository.rent.offer;


import org.springframework.data.domain.Pageable;
import ua.com.gup.rent.filter.RentOfferFilter;
import ua.com.gup.rent.model.enumeration.RentOfferCurrency;
import ua.com.gup.rent.model.enumeration.RentOfferStatus;
import ua.com.gup.rent.model.mongo.rent.RentOffer;
import ua.com.gup.rent.model.rent.RentOfferCategoryCount;

import java.util.Collection;
import java.util.List;

public interface RentOfferRepositoryCustom {

    long countByFilter(RentOfferFilter offerFilter, RentOfferStatus offerStatus);

    List<RentOffer> findByFilter(RentOfferFilter offerFilter, RentOfferStatus offerStatus, Pageable pageable);

    List<RentOffer> findByFilter(RentOfferFilter offerFilter, RentOfferStatus offerStatus, String excludedId, Pageable pageable);

    List<RentOffer> findByFilter(RentOfferFilter offerFilter, List<RentOfferStatus> offerStatuses, Pageable pageable);

    List<RentOffer> findByFilter(RentOfferFilter offerFilter, List<RentOfferStatus> offerStatuses, Collection<String> excludedIds, Pageable pageable);

    List<RentOfferCategoryCount> searchCategoriesByString(String string, int page, int size);

    void updateBasePriceByExchangeRate(RentOfferStatus status, RentOfferCurrency currency, RentOfferCurrency baseCurrency, double exchangeRate);

}


