package ua.com.gup.rent.service.rent.impl;

import org.springframework.stereotype.Service;
import ua.com.gup.rent.model.mongo.rent.bid.RentOfferBid;
import ua.com.gup.rent.repository.rent.RentOfferBidRepository;
import ua.com.gup.rent.service.abstracted.RentOfferGenericServiceImpl;
import ua.com.gup.rent.service.dto.rent.bid.RentOfferBidCreateDTO;
import ua.com.gup.rent.service.rent.RentOfferBidService;

@Service
public class RentOfferBidServiceImpl extends RentOfferGenericServiceImpl<RentOfferBid, String> implements RentOfferBidService {

    public RentOfferBidServiceImpl(RentOfferBidRepository rentOfferBidRepository) {
        super(rentOfferBidRepository);
    }

    @Override
    public RentOfferBid create(RentOfferBidCreateDTO dto) {
        //see RentOfferBidStatus status;
        return null;
    }

    @Override
    protected RentOfferBidRepository getRepository() {
        return (RentOfferBidRepository) super.getRepository();
    }
}
