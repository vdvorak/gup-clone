package ua.com.gup.rent.command.rent.offer.bid;

import ua.com.gup.common.command.Journalable;
import ua.com.gup.common.model.mongo.operation.OperationType;
import ua.com.gup.rent.command.RentCommand;
import ua.com.gup.rent.model.mongo.rent.RentOffer;
import ua.com.gup.rent.model.mongo.rent.bid.RentOfferBid;
import ua.com.gup.rent.service.dto.rent.bid.RentOfferBidCreateDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferCreateDTO;
import ua.com.gup.rent.service.rent.RentOfferBidService;
import ua.com.gup.rent.service.rent.RentOfferService;

public class CreateRentOfferBidCommand extends RentCommand<RentOfferBid> {
    private RentOfferBidCreateDTO dto;
    private RentOfferBid bid;
    private String id;
    private RentOfferBidService rentOfferBidService;

    public CreateRentOfferBidCommand(RentOfferBidService rentOfferBidService, RentOfferBidCreateDTO updateDTO) {
        //super(rentOfferService, "");
        this.dto = updateDTO;
    }

    @Override
    public RentOfferBid execute() throws Exception {
        this.bid = rentOfferBidService.create(dto);
        this.id = bid.getId();
        return bid;
    }


    public String getObjectId() {
        return id;
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.RENT_OFFER_CREATE;
    }

    @Override
    public Journalable getJournalable() {
        return new Journalable() {
            @Override
            public Object getObject() {
                return bid;
            }

            @Override
            public String getObjectId() {
                return id;
            }

            @Override
            public String getObjectType() {
                return null;
            }
        };
    }
}
