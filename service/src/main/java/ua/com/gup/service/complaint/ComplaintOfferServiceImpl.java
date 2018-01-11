package ua.com.gup.service.complaint;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.com.gup.common.service.impl.CommonComplaintOfferServiceImpl;
import ua.com.gup.mongo.composition.domain.complaint.ComplaintOffer;

/**
 * Service Implementation for managing ComplaintOffer.
 */
@Service
public class ComplaintOfferServiceImpl
        extends CommonComplaintOfferServiceImpl<ComplaintOffer>
        implements ComplaintOfferService {
    private final Logger log = LoggerFactory.getLogger(ComplaintOfferServiceImpl.class);

    @Override
    protected ComplaintOffer createModelInstance() {
        return new ComplaintOffer();
    }
}
