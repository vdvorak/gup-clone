package ua.com.gup.service.complaint;


import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.mongo.composition.domain.complaint.ComplaintOffer;
import ua.com.gup.mongo.model.enumeration.ComplaintOfferStatus;
import ua.com.gup.mongo.model.enumeration.ComplaintOfferType;
import ua.com.gup.repository.complaint.ComplaintOfferRepository;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Service Implementation for managing ComplaintOffer.
 */
@Service
public class ComplaintOfferServiceImpl implements ComplaintOfferService {


    private static final String OFFER_SEQUENCE_ID = "complaint_sequence";

    private final Logger log = LoggerFactory.getLogger(ComplaintOfferServiceImpl.class);

    @Autowired
    private ComplaintOfferRepository complaintRepository;


    @Override
    public void save(ComplaintOffer complaintOffer) {
        log.debug("Request to save ComplaintOffer : {}", complaintOffer);

        ComplaintOffer newComplaintOffer = new ComplaintOffer();
        newComplaintOffer.setOfferId(complaintOffer.getOfferId());
//        newComplaintOffer.setDescriptions(complaintOffer.getDescriptions());
        newComplaintOffer.setDescription(complaintOffer.getDescription());
        newComplaintOffer.setTypes(complaintOffer.getTypes());
        newComplaintOffer.setInitiatorId(complaintOffer.getInitiatorId());
        newComplaintOffer.setStatus(ComplaintOfferStatus.NEW);
//        newComplaintOffer.setCreatedDateLong( toDateTime(newComplaintOffer.getCreatedDate()).getMillis() );
        complaintRepository.save(newComplaintOffer);
    }

    @Override
    public boolean exists(String id) {
        log.debug("Request to exists ComplaintOffer : {}", id);
        return complaintRepository.exists(id);
    }

    @Override
    public ComplaintOffer findOne(String id) {
        log.debug("Request to get ComplaintOffer : {}", id);
        return complaintRepository.findById(id);
    }

    @Override
    public List<ComplaintOffer> findAll() {
        log.debug("Request to get all ComplaintOffers");
        return complaintRepository.findAll();
    }

    @Override
    public List<ComplaintOffer> findAllByOfferId(String offerId) {
        log.debug("Request to get all ComplaintOffers by offerId = {}", offerId);
        return complaintRepository.findAllByOfferId(offerId);
    }

    @Override
    public List<ComplaintOffer> findAllByInitiatorId(String initiatorId) {
        log.debug("Request to get all ComplaintOffers by initiatorId = {}", initiatorId);
        return complaintRepository.findAllByInitiatorId(initiatorId);
    }

    @Override
    public List<ComplaintOffer> findAllByStatus(ComplaintOfferStatus status) {
        log.debug("Request to get all ComplaintOffers by status = {}", status);
        return complaintRepository.findAllByStatus(status);
    }

    @Override
    public void updateType(String id, ComplaintOfferType type) {
        log.debug("Request to update update complaintOffer types : {}", id);

        if(exists(id)) {
            ComplaintOffer updateComplaintOffer = findOne(id);
            updateComplaintOffer.addType(type);
            updateComplaintOffer.updateLastModifiedDate();
//            updateComplaintOffer.setLastModifiedDateLong(toDateTime(updateComplaintOffer.getLastModifiedDate()).getMillis());
            complaintRepository.update(updateComplaintOffer);
        }
    }

    @Override
    public void updateTypes(String id, List<ComplaintOfferType> types) {
        log.debug("Request to update update complaintOffer types : {}", id);

        if(exists(id)) {
            ComplaintOffer updateComplaintOffer = findOne(id);
            updateComplaintOffer.setTypes(types);
            updateComplaintOffer.updateLastModifiedDate();
//            updateComplaintOffer.setLastModifiedDateLong(toDateTime(updateComplaintOffer.getLastModifiedDate()).getMillis());
            complaintRepository.update(updateComplaintOffer);
        }
    }

    @Override
    public void updateStatus(String id, ComplaintOfferStatus status) {
        log.debug("Request to update update complaintOffer status : {}", id);

        if(exists(id)) {
            ComplaintOffer updateComplaintOffer = findOne(id);
            updateComplaintOffer.setStatus(status);
            updateComplaintOffer.updateLastModifiedDate();
//            updateComplaintOffer.setLastModifiedDateLong(toDateTime(updateComplaintOffer.getLastModifiedDate()).getMillis());
            complaintRepository.update(updateComplaintOffer);
        }
    }

    private DateTime toDateTime(final ZonedDateTime zdt) {
        return new DateTime(zdt.toInstant().toEpochMilli(), DateTimeZone.forID(zdt.getOffset().getId()));
    }
}
