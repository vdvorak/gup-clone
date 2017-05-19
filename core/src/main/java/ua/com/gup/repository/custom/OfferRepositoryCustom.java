package ua.com.gup.repository.custom;


import org.springframework.data.domain.Pageable;
import ua.com.gup.domain.Offer;
import ua.com.gup.domain.enumeration.Currency;
import ua.com.gup.domain.enumeration.OfferStatus;
import ua.com.gup.domain.filter.OfferFilter;

import java.util.Collection;
import java.util.List;

public interface OfferRepositoryCustom {

    List<Offer> findByFilter(OfferFilter offerFilter, OfferStatus offerStatus, Pageable pageable);

    List<Offer> findByFilter(OfferFilter offerFilter, OfferStatus offerStatus, String excludedId, Pageable pageable);

    List<Offer> findByFilter(OfferFilter offerFilter, List<OfferStatus> offerStatuses, Pageable pageable);

    List<Offer> findByFilter(OfferFilter offerFilter, List<OfferStatus> offerStatuses, Collection<String> excludedIds, Pageable pageable);

    void incrementViews(String id);

    void incrementViewsBySeoUrl(String seoUrl);

    void incrementPhoneViews(String id);

    void incrementFavorites(String id);

    void updateBasePriceByExchangeRate(OfferStatus status, Currency currency, Currency baseCurrency, double exchangeRate);
}


