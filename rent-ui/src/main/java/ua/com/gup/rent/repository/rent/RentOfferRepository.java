package ua.com.gup.rent.repository.rent;

import ua.com.gup.common.model.enumeration.CommonCurrency;
import ua.com.gup.common.model.enumeration.CommonStatus;
import ua.com.gup.common.repository.CommonOfferRepository;
import ua.com.gup.rent.model.mongo.rent.RentOffer;
import ua.com.gup.rent.service.dto.rent.offer.filter.RentOfferFilterDTO;

public interface RentOfferRepository  extends CommonOfferRepository<RentOffer,RentOfferFilterDTO>{

    void updateBasePriceByExchangeRate(CommonStatus status, CommonCurrency currency, CommonCurrency baseCurrency, double exchangeRate);

}
