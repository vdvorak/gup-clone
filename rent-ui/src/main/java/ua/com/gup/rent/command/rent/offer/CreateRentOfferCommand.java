package ua.com.gup.rent.command.rent.offer;

import ua.com.gup.common.model.mongo.operation.OperationType;
import ua.com.gup.rent.model.mongo.rent.RentOffer;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferCreateDTO;
import ua.com.gup.rent.service.rent.RentOfferService;

public class CreateRentOfferCommand extends RentOfferCommand {
    private RentOfferCreateDTO createDTO;

    public CreateRentOfferCommand(RentOfferService rentOfferService, RentOfferCreateDTO updateDTO) {
        super(rentOfferService, "");
        this.createDTO = updateDTO;
    }

    @Override
    public RentOffer execute() throws Exception {
        this.rentOffer = rentOfferService.saveAndReturn(createDTO);
        this.rentOfferId = rentOffer.getId();
        return rentOffer;
    }


    public String getObjectId() {
        return rentOfferId;
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.RENT_OFFER_CREATE;
    }
}
