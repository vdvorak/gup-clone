package ua.com.gup.rent.event.offer.bid;

import ua.com.gup.rent.event.RentEvent;
import ua.com.gup.rent.model.mongo.rent.bid.RentOfferBid;

public class RentOfferBidEvent<T extends RentOfferBid> extends RentEvent<T> {

    public RentOfferBidEvent(T source) {
        super(source);
    }

    @Override
    public RentOfferBid getSource() {
        return (RentOfferBid) super.getSource();
    }
}
