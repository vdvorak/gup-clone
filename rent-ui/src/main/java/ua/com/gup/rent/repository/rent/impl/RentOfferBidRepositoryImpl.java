package ua.com.gup.rent.repository.rent.impl;

import org.springframework.stereotype.Repository;
import ua.com.gup.rent.model.mongo.rent.bid.RentOfferBid;
import ua.com.gup.rent.repository.abstracted.RentOfferGenericRepositoryImpl;
import ua.com.gup.rent.repository.rent.RentOfferBidRepository;

@Repository
public class RentOfferBidRepositoryImpl extends RentOfferGenericRepositoryImpl<RentOfferBid, String> implements RentOfferBidRepository {

    public RentOfferBidRepositoryImpl() {
        super(RentOfferBid.class);
    }
}
