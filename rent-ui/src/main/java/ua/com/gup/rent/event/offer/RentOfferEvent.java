package ua.com.gup.rent.event.offer;

import ua.com.gup.rent.event.RentEvent;
import ua.com.gup.rent.model.mongo.rent.RentOffer;

public class RentOfferEvent<T extends RentOffer> extends RentEvent<T> {

    public RentOfferEvent(T source) {
        super(source);
    }

    @Override
    public RentOffer getSource() {
        return (RentOffer) super.getSource();
    }
}
