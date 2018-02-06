package ua.com.gup.rent.service.rent;

import ua.com.gup.rent.model.mongo.rent.bid.RentOfferBid;
import ua.com.gup.rent.service.abstracted.generic.RentOfferGenericService;
import ua.com.gup.rent.service.dto.rent.bid.RentOfferBidCreateDTO;

public interface RentOfferBidService extends RentOfferGenericService<RentOfferBid, String> {
    RentOfferBid create(RentOfferBidCreateDTO dto);
}
