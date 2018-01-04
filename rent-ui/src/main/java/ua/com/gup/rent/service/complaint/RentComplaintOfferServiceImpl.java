package ua.com.gup.rent.service.complaint;


import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.rent.mapper.RentComplaintMapper;
import ua.com.gup.rent.model.mongo.complaint.RentComplaintOffer;
import ua.com.gup.rent.model.mongo.complaint.RentComplaintOfferStatus;
import ua.com.gup.rent.model.mongo.complaint.RentComplaintOfferType;
import ua.com.gup.rent.model.rent.complaint.RentComplaintInitiator;
import ua.com.gup.rent.repository.complaint.RentComplaintOfferRepository;

import java.time.ZonedDateTime;
import java.util.List;


/**
 * Service Implementation for managing ComplaintOffer.
 */
@Service
public class RentComplaintOfferServiceImpl implements RentComplaintOfferService {
    private final Logger log = LoggerFactory.getLogger(RentComplaintOfferServiceImpl.class);

    @Autowired
    private RentComplaintOfferRepository complaintRepository;
    @Autowired
    private RentComplaintMapper complaintMapper;


    @Override
    public void save(RentComplaintOffer complaintOffer) {
        log.debug("Request to save ComplaintOffer : {}", complaintOffer);
        RentComplaintOffer newComplaintOffer = new RentComplaintOffer();
        newComplaintOffer.setOfferId(complaintOffer.getOfferId());
        newComplaintOffer.setDescription(complaintOffer.getDescription());
        newComplaintOffer.setTypes(complaintOffer.getTypes());
        newComplaintOffer.setInitiator(complaintOffer.getInitiator());
        newComplaintOffer.setStatus(RentComplaintOfferStatus.NEW);
        complaintRepository.save(newComplaintOffer);
    }

    @Override
    public boolean exists(String id) {
        log.debug("Request to exists ComplaintOffer : {}", id);
        return complaintRepository.exists(id);
    }

    @Override
    public RentComplaintOffer findOne(String id) {
        log.debug("Request to get ComplaintOffer : {}", id);
        return complaintRepository.findById(id);
    }

    @Override
    public List<RentComplaintOffer> findAll() {
        log.debug("Request to get all ComplaintOffers");
        return complaintRepository.findAll();
    }

    @Override
    public List<RentComplaintOffer> findAllByOfferId(String offerId) {
        log.debug("Request to get all ComplaintOffers by offerId = {}", offerId);
        return complaintRepository.findAllByOfferId(offerId);
    }

    @Override
    public List<RentComplaintOffer> findAllByInitiatorId(String initiatorId) {
        log.debug("Request to get all ComplaintOffers by initiatorId = {}", initiatorId);
        return complaintRepository.findAllByInitiatorId(initiatorId);
    }

    @Override
    public List<RentComplaintOffer> findAllByStatus(RentComplaintOfferStatus status) {
        log.debug("Request to get all ComplaintOffers by status = {}", status);
        return complaintRepository.findAllByStatus(status);
    }

    @Override
    public void updateType(String id, RentComplaintOfferType type) {
        log.debug("Request to update update complaintOffer types : {}", id);

        if(exists(id)) {
            RentComplaintOffer updateComplaintOffer = findOne(id);
            updateComplaintOffer.addType(type);
            updateComplaintOffer.updateLastModifiedDate();
            complaintRepository.update(updateComplaintOffer);
        }
    }

    @Override
    public void updateTypes(String id, List<RentComplaintOfferType> types) {
        log.debug("Request to update update complaintOffer types : {}", id);
        if(exists(id)) {
            RentComplaintOffer updateComplaintOffer = findOne(id);
            updateComplaintOffer.setTypes(types);
            updateComplaintOffer.updateLastModifiedDate();
            complaintRepository.update(updateComplaintOffer);
        }
    }

    @Override
    public void updateStatus(String id, RentComplaintOfferStatus status) {
        log.debug("Request to update update complaintOffer status : {}", id);
        if(exists(id)) {
            RentComplaintOffer updateComplaintOffer = findOne(id);
            updateComplaintOffer.setStatus(status);
            updateComplaintOffer.updateLastModifiedDate();
            complaintRepository.update(updateComplaintOffer);
        }
    }

    @Override
    public RentComplaintInitiator getInitiatorProfile(String authorId) {
        return complaintMapper.getInitiatorProfile(authorId);
    }

    private DateTime toDateTime(final ZonedDateTime zdt) {
        return new DateTime(zdt.toInstant().toEpochMilli(), DateTimeZone.forID(zdt.getOffset().getId()));
    }
}
