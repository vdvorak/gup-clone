package ua.com.gup.rent.service.rent;

import ua.com.gup.rent.model.mongo.rent.bid.RentOfferBid;
import ua.com.gup.rent.service.dto.rent.bid.RentOfferBidCreateDTO;

public interface RentOfferBidService {
    RentOfferBid create(RentOfferBidCreateDTO dto);
}
