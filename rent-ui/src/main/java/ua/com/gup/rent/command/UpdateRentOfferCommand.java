package ua.com.gup.rent.command;

import ua.com.gup.common.model.mongo.operation.OperationType;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferUpdateDTO;
import ua.com.gup.rent.service.dto.rent.offer.view.RentOfferViewDetailsDTO;
import ua.com.gup.rent.service.rent.RentOfferService;

public class UpdateRentOfferCommand extends RentOfferCommand<RentOfferViewDetailsDTO> {

    private RentOfferUpdateDTO updateDTO;

    public UpdateRentOfferCommand(RentOfferService rentOfferService, RentOfferUpdateDTO updateDTO, String rentOfferId) {
        super(rentOfferService, rentOfferId);
        this.updateDTO = updateDTO;
    }

    @Override
    public RentOfferViewDetailsDTO execute() throws Exception {
        return rentOfferService.update(rentOfferId, updateDTO);
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.RENT_OFFER_UPDATE;
    }
}
