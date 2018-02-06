package ua.com.gup.rent.event.offer;

import ua.com.gup.rent.model.mongo.rent.RentOffer;

public class RentOfferModeratedEvent extends RentOfferEvent<RentOffer> {

    public RentOfferModeratedEvent(RentOffer source) {
        super(source);
    }
}
