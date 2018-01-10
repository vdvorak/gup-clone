package ua.com.gup.rent.service.complaint;


import org.springframework.stereotype.Service;
import ua.com.gup.common.service.impl.CommonComplaintOfferServiceImpl;
import ua.com.gup.rent.model.mongo.complaint.RentComplaintOffer;


/**
 * Service Implementation for managing ComplaintOffer.
 */
@Service
public class RentComplaintOfferServiceImpl
        extends CommonComplaintOfferServiceImpl<RentComplaintOffer>
        implements RentComplaintOfferService {

    @Override
    protected RentComplaintOffer createModelInstance() {
        return new RentComplaintOffer();
    }
}
