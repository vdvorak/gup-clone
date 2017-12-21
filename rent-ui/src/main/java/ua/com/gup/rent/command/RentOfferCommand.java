package ua.com.gup.rent.command;

import ua.com.gup.common.command.Journalable;
import ua.com.gup.common.model.object.ObjectType;
import ua.com.gup.rent.service.dto.rent.offer.view.RentOfferViewBaseDTO;
import ua.com.gup.rent.service.rent.RentOfferService;

public abstract class RentOfferCommand<T extends RentOfferViewBaseDTO> extends RentCommand<T> {
    protected RentOfferService rentOfferService;
    protected String rentOfferId;

    public RentOfferCommand(RentOfferService rentOfferService, String rentOfferId) {
        this.rentOfferService = rentOfferService;
        this.rentOfferId = rentOfferId;
    }

    @Override
    public Journalable getJournalable() {
        return new Journalable() {
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
