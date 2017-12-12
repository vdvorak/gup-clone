package ua.com.gup.rent.repository.rent.offer;


import org.springframework.data.domain.Pageable;
import ua.com.gup.rent.filter.RentOfferFilter;
import ua.com.gup.common.model.enumeration.CommonCurrency;
import ua.com.gup.common.model.enumeration.CommonStatus;
import ua.com.gup.rent.model.mongo.rent.RentOffer;
import ua.com.gup.rent.model.rent.RentOfferCategoryCount;

import java.util.Collection;
import java.util.List;
import ua.com.gup.common.model.image.ImageStorage;

public interface RentOfferRepositoryCustom {

    long countByFilter(RentOfferFilter offerFilter, CommonStatus offerStatus);

    List<RentOffer> findByFilter(RentOfferFilter offerFilter, CommonStatus offerStatus, Pageable pageable);

    List<RentOffer> findByFilter(RentOfferFilter offerFilter, CommonStatus offerStatus, String excludedId, Pageable pageable);

    List<RentOffer> findByFilter(RentOfferFilter offerFilter, List<CommonStatus> offerStatuses, Pageable pageable);

    List<RentOffer> findByFilter(RentOfferFilter offerFilter, List<CommonStatus> offerStatuses, Collection<String> excludedIds, Pageable pageable);

    List<RentOfferCategoryCount> searchCategoriesByString(String string, int page, int size);

    void updateBasePriceByExchangeRate(CommonStatus status, CommonCurrency currency, CommonCurrency baseCurrency, double exchangeRate);
    
    List<ImageStorage> findOfferImages(String offerId);
    
    ImageStorage findOfferImage(String offerId, String imageId);
    
    Boolean isExistsOfferImage(String offerId, String imageId);

    void deleteOfferImage(ImageStorage image);

}


