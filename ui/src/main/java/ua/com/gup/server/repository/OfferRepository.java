package ua.com.gup.server.repository;


import ua.com.gup.common.repository.CommonOfferRepository;
import ua.com.gup.mongo.composition.domain.offer.Offer;
import ua.com.gup.mongo.model.filter.OfferFilter;
import ua.com.gup.mongo.model.filter.OfferFilterOptions;
import ua.com.gup.mongo.model.other.EntityPage;

public interface OfferRepository extends CommonOfferRepository<Offer,OfferFilter> {

    /**
     * Find offers based on filter options.
     *
     * @param offerFilterOptions - the OfferFilterOptions object contain options from filter.
     * @return - the EntityPage object which contain
     * list of found offers which relevant to filterOption object.
     */
    EntityPage<Offer> findOffersWithOptions(OfferFilterOptions offerFilterOptions);
}


