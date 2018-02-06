package ua.com.gup.rent.event.offer.bid;

import ua.com.gup.rent.model.mongo.rent.bid.RentOfferBid;

public class RentOfferBidCreatedEvent extends RentOfferBidEvent<RentOfferBid> {

    public RentOfferBidCreatedEvent(RentOfferBid source) {
        super(source);
    }
}
