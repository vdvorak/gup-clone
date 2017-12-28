package ua.com.gup.rent.command.rent.offer;

import ua.com.gup.common.model.mongo.operation.OperationType;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferCreateDTO;
import ua.com.gup.rent.service.dto.rent.offer.view.RentOfferViewDetailsDTO;
import ua.com.gup.rent.service.rent.RentOfferService;

public class CreateRentOfferCommand extends RentOfferCommand<RentOfferViewDetailsDTO> {
    private RentOfferCreateDTO createDTO;

    public CreateRentOfferCommand(RentOfferService rentOfferService, RentOfferCreateDTO updateDTO) {
        super(rentOfferService, "");
        this.createDTO = updateDTO;
    }

    @Override
    public RentOfferViewDetailsDTO execute() throws Exception {
        RentOfferViewDetailsDTO viewDetailsDTO = rentOfferService.save(createDTO);
        rentOfferId = viewDetailsDTO.getId();
        this.rentOffer = rentOfferService.findById(rentOfferId);
        return viewDetailsDTO;
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.RENT_OFFER_CREATE;
    }
}
