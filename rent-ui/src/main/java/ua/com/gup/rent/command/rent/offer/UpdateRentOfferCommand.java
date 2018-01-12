package ua.com.gup.rent.command.rent.offer;

import ua.com.gup.common.model.mongo.operation.OperationType;
import ua.com.gup.rent.model.mongo.rent.RentOffer;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferUpdateDTO;
import ua.com.gup.rent.service.dto.rent.offer.view.RentOfferViewDetailsDTO;
import ua.com.gup.rent.service.rent.RentOfferService;

public class UpdateRentOfferCommand extends RentOfferCommand {

    private RentOfferUpdateDTO updateDTO;

    public UpdateRentOfferCommand(RentOfferService rentOfferService, RentOfferUpdateDTO updateDTO, String rentOfferId) {
        super(rentOfferService, rentOfferId);
        this.updateDTO = updateDTO;
    }

    @Override
    public RentOffer execute() throws Exception {
        this.rentOffer = rentOfferService.updateAndReturn(rentOfferId, updateDTO);
        return rentOffer;
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.RENT_OFFER_UPDATE;
    }
}
