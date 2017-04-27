package ua.com.gup.repository.custom;


import org.springframework.data.domain.Pageable;
import ua.com.gup.domain.Offer;
import ua.com.gup.domain.enumeration.Currency;
import ua.com.gup.domain.enumeration.OfferStatus;
import ua.com.gup.repository.filter.OfferFilter;

import java.util.List;

public interface OfferRepositoryCustom {

    List<Offer> findByFilter(OfferFilter offerFilter, OfferStatus offerStatus, Pageable pageable);

    List<Offer> findByFilter(OfferFilter offerFilter, List<OfferStatus> offerStatuses, Pageable pageable);

    void updateBasePriceByExchangeRate(OfferStatus status, Currency currency, Currency baseCurrency, double exchangeRate);
}


