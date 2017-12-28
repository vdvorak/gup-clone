package ua.com.gup.rent.command.rent.offer;

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
        RentOfferViewDetailsDTO detailsDTO = rentOfferService.update(rentOfferId, updateDTO);
        this.rentOffer = rentOfferService.findById(rentOfferId);
        return detailsDTO;
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.RENT_OFFER_UPDATE;
    }
}
