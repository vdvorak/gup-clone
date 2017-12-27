package ua.com.gup.rent.repository.rent;

import org.springframework.data.domain.Pageable;
import ua.com.gup.common.model.enumeration.CommonCurrency;
import ua.com.gup.common.model.enumeration.CommonStatus;
import ua.com.gup.common.repository.CommonOfferRepository;
import ua.com.gup.rent.filter.RentOfferFilter;
import ua.com.gup.rent.model.mongo.rent.RentOffer;
import ua.com.gup.rent.model.rent.RentOfferCategoryCount;
import ua.com.gup.rent.repository.abstracted.generic.RentOfferGenericRepository;

import java.util.Collection;
import java.util.List;

public interface RentOfferRepository  extends CommonOfferRepository<RentOffer,RentOfferFilter>{

    long countByFilter(RentOfferFilter offerFilter, CommonStatus offerStatus);

    List<RentOffer> findByFilter(RentOfferFilter offerFilter, CommonStatus offerStatus, Pageable pageable);

    List<RentOffer> findByFilter(RentOfferFilter offerFilter, CommonStatus offerStatus, String excludedId, Pageable pageable);

    List<RentOffer> findByFilter(RentOfferFilter offerFilter, List<CommonStatus> offerStatuses, Pageable pageable);

    List<RentOffer> findByFilter(RentOfferFilter offerFilter, List<CommonStatus> offerStatuses, Collection<String> excludedIds, Pageable pageable);

    void updateBasePriceByExchangeRate(CommonStatus status, CommonCurrency currency, CommonCurrency baseCurrency, double exchangeRate);

}
