package ua.com.gup.rent.command.rent.offer;

import ua.com.gup.common.command.Journalable;
import ua.com.gup.common.model.object.ObjectType;
import ua.com.gup.rent.command.RentCommand;
import ua.com.gup.rent.model.mongo.rent.RentOffer;
import ua.com.gup.rent.service.dto.rent.offer.view.RentOfferViewBaseDTO;
import ua.com.gup.rent.service.rent.RentOfferService;

public abstract class RentOfferCommand<T extends RentOfferViewBaseDTO> extends RentCommand<T> {
    protected RentOfferService rentOfferService;
    protected RentOffer rentOffer;
    protected String rentOfferId;

    public RentOfferCommand(RentOfferService rentOfferService, String rentOfferId) {
        this.rentOfferService = rentOfferService;
        this.rentOfferId = rentOfferId;
    }

    protected final RentOffer getObjectAfterOperation() {
        return this.rentOffer;
    }
    @Override
    public Journalable getJournalable() {
        return new Journalable() {
            @Override
            public Object getObject() {
                return getObjectAfterOperation();
            }
            @Override
            public String getObjectId() {
                return rentOfferId;
            }

            @Override
            public String getObjectType() {
                return ObjectType.RENT_OFFER;
            }
        };
    }
}
