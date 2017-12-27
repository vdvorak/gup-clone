package ua.com.gup.server.repository;


import org.springframework.data.domain.Pageable;
import ua.com.gup.common.model.enumeration.CommonStatus;
import ua.com.gup.common.repository.CommonOfferRepository;
import ua.com.gup.mongo.composition.domain.offer.Offer;
import ua.com.gup.mongo.model.filter.OfferFilter;
import ua.com.gup.mongo.model.filter.OfferFilterOptions;
import ua.com.gup.mongo.model.offer.OfferCategoryCount;
import ua.com.gup.mongo.model.other.EntityPage;

import java.util.Collection;
import java.util.List;

public interface OfferRepository extends CommonOfferRepository<Offer,OfferFilter> {

    long countByFilter(OfferFilter offerFilter, CommonStatus offerStatus);

    List<Offer> findByFilter(OfferFilter offerFilter, CommonStatus offerStatus, Pageable pageable);

    List<Offer> findByFilter(OfferFilter offerFilter, CommonStatus offerStatus, String excludedId, Pageable pageable);

    List<Offer> findByFilter(OfferFilter offerFilter, List<CommonStatus> offerStatuses, Pageable pageable);

    List<Offer> findByFilter(OfferFilter offerFilter, List<CommonStatus> offerStatuses, Collection<String> excludedIds, Pageable pageable);


    /**
     * Find offers based on filter options.
     *
     * @param offerFilterOptions - the OfferFilterOptions object contain options from filter.
     * @return - the EntityPage object which contain
     * list of found offers which relevant to filterOption object.
     */
    EntityPage<Offer> findOffersWithOptions(OfferFilterOptions offerFilterOptions);
}


