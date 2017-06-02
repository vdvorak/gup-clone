package ua.com.gup.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.domain.complaint.ComplaintOffer;
import ua.com.gup.domain.complaint.ComplaintOfferDescription;
import ua.com.gup.domain.complaint.ComplaintOfferStatus;
import ua.com.gup.domain.complaint.ComplaintOfferType;
import ua.com.gup.repository.ComplaintOfferRepository;
import ua.com.gup.service.ComplaintOfferService;

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
        newComplaintOffer.setTypes(complaintOffer.getTypes());
        newComplaintOffer.setInitiatorId(complaintOffer.getInitiatorId());
        newComplaintOffer.setStatus(ComplaintOfferStatus.NEW);

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

//    @Override
//    public void updateDescription(String id, ComplaintOfferDescription description) {
//        log.debug("Request to update update complaintOffer descriptions : {}", id);
//
//        if(exists(id)) {
//            ComplaintOffer updateComplaintOffer = findOne(id);
//            updateComplaintOffer.addDescriptions(description);
//            updateComplaintOffer.updateLastModifiedDate();
//            complaintRepository.update(updateComplaintOffer);
//        }
//    }

    @Override
    public void updateType(String id, ComplaintOfferType type) {
        log.debug("Request to update update complaintOffer types : {}", id);

        if(exists(id)) {
            ComplaintOffer updateComplaintOffer = findOne(id);
            updateComplaintOffer.addType(type);
            updateComplaintOffer.updateLastModifiedDate();
            complaintRepository.update(updateComplaintOffer);
        }
    }

//    @Override
//    public void updateDescriptions(String id, List<ComplaintOfferDescription> descriptions) {
//        log.debug("Request to update update complaintOffer descriptions : {}", id);
//
//        if(exists(id)) {
//            ComplaintOffer updateComplaintOffer = findOne(id);
//            updateComplaintOffer.setDescriptions(descriptions);
//            updateComplaintOffer.updateLastModifiedDate();
//            complaintRepository.update(updateComplaintOffer);
//        }
//    }
    @Override
    public void updateTypes(String id, List<ComplaintOfferType> types) {
        log.debug("Request to update update complaintOffer types : {}", id);

        if(exists(id)) {
            ComplaintOffer updateComplaintOffer = findOne(id);
            updateComplaintOffer.setTypes(types);
            updateComplaintOffer.updateLastModifiedDate();
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
            complaintRepository.update(updateComplaintOffer);
        }
    }
}
