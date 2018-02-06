package ua.com.gup.rent.mapper.bid;

import org.springframework.stereotype.Component;
import ua.com.gup.rent.model.mongo.rent.bid.RentOfferBid;
import ua.com.gup.rent.service.dto.rent.bid.RentOfferBidCreateDTO;

@Component
public class RentOfferBidMapper {

    public RentOfferBidCreateDTO convertToDTO(RentOfferBid bid){
        RentOfferBidCreateDTO dto = new RentOfferBidCreateDTO();
        dto.setComment(bid.getComment());
        dto.setDtEnd(bid.getDtEnd());
        dto.setDtStart(bid.getDtStart());
        return dto;
    }

    public RentOfferBid convertFromDTO(RentOfferBidCreateDTO dto, String seoUrl){
        RentOfferBid bid = new RentOfferBid();

        return bid;
    }
}
