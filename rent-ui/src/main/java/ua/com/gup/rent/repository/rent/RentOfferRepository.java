package ua.com.gup.rent.repository.rent;

import ua.com.gup.common.model.enumeration.CommonCurrency;
import ua.com.gup.common.model.enumeration.CommonStatus;
import ua.com.gup.common.repository.CommonOfferRepository;
import ua.com.gup.rent.filter.RentOfferFilter;
import ua.com.gup.rent.model.mongo.rent.RentOffer;

public interface RentOfferRepository  extends CommonOfferRepository<RentOffer,RentOfferFilter>{

    void updateBasePriceByExchangeRate(CommonStatus status, CommonCurrency currency, CommonCurrency baseCurrency, double exchangeRate);

}
