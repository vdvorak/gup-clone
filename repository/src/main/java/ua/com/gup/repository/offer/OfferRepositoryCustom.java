package ua.com.gup.repository.offer;


import org.springframework.data.domain.Pageable;
import ua.com.gup.common.model.enumeration.CommonStatus;
import ua.com.gup.mongo.composition.domain.offer.Offer;
import ua.com.gup.mongo.model.filter.OfferFilter;
import ua.com.gup.mongo.model.offer.OfferCategoryCount;

import java.util.Collection;
import java.util.List;

public interface OfferRepositoryCustom {

    long countByFilter(OfferFilter offerFilter, CommonStatus offerStatus);

    List<Offer> findByFilter(OfferFilter offerFilter, CommonStatus offerStatus, Pageable pageable);

    List<Offer> findByFilter(OfferFilter offerFilter, CommonStatus offerStatus, String excludedId, Pageable pageable);

    List<Offer> findByFilter(OfferFilter offerFilter, List<CommonStatus> offerStatuses, Pageable pageable);

    List<Offer> findByFilter(OfferFilter offerFilter, List<CommonStatus> offerStatuses, Collection<String> excludedIds, Pageable pageable);

    List<OfferCategoryCount> searchCategoriesByString(String string, int page, int size);
}


