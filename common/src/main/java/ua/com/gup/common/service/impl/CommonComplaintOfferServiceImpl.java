package ua.com.gup.common.service.impl;


import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import ua.com.gup.common.dto.profile.manager.event.ManagerActionDto;
import ua.com.gup.common.model.complaint.*;
import ua.com.gup.common.model.mongo.profile.manager.event.ManagerAction;
import ua.com.gup.common.repository.CommonComplaintOfferRepository;
import ua.com.gup.common.service.CommonComplaintOfferService;
import ua.com.gup.common.service.mapper.CommonComplaintMapper;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ComplaintOffer.
 */

public abstract class CommonComplaintOfferServiceImpl<T extends CommonComplaint>
        implements CommonComplaintOfferService<T> {
    private final Logger log = LoggerFactory.getLogger(CommonComplaintOfferServiceImpl.class);

    @Autowired
    private CommonComplaintOfferRepository complaintRepository;
    @Autowired
    private CommonComplaintMapper complaintMapper;


    protected abstract T createModelInstance();

    @Override
    public void save(T complaintOffer) {
        log.debug("Request to save ComplaintOffer : {}", complaintOffer);
        T newComplaintOffer = createModelInstance();
        newComplaintOffer.setOfferId(complaintOffer.getOfferId());
        newComplaintOffer.setDescription(complaintOffer.getDescription());
        newComplaintOffer.setTypes(complaintOffer.getTypes());
        newComplaintOffer.setInitiator(complaintOffer.getInitiator());
        newComplaintOffer.setStatus(ComplaintOfferStatus.NEW);
        complaintRepository.save(newComplaintOffer);
    }

    @Override
    public boolean exists(String id) {
        log.debug("Request to exists ComplaintOffer : {}", id);
        return complaintRepository.exists(id);
    }

    @Override
    public T findOne(String id) {
        log.debug("Request to get ComplaintOffer : {}", id);
        return (T) complaintRepository.findById(id);
    }

    @Override
    public List<T> findAll() {
        log.debug("Request to get all ComplaintOffers");
        return complaintRepository.findAll();
    }

    @Override
    public List<T> findAllByOfferId(String offerId) {
        log.debug("Request to get all ComplaintOffers by offerId = {}", offerId);
        return complaintRepository.findAllByOfferId(offerId);
    }

    @Override
    public List<T> findAllByInitiatorId(String initiatorId) {
        log.debug("Request to get all ComplaintOffers by initiatorId = {}", initiatorId);
        return complaintRepository.findAllByInitiatorId(initiatorId);
    }

    @Override
    public List<T> findAllByStatus(ComplaintOfferStatus status) {
        log.debug("Request to get all ComplaintOffers by status = {}", status);
        return complaintRepository.findAllByStatus(status);
    }

    @Override
    public void updateType(String id, ComplaintOfferType type) {
        log.debug("Request to update update complaintOffer types : {}", id);

        if(exists(id)) {
            T updateComplaintOffer = findOne(id);
            updateComplaintOffer.addType(type);
            updateComplaintOffer.updateLastModifiedDate();
            complaintRepository.update(updateComplaintOffer);
        }
    }

    @Override
    public void updateTypes(String id, List<ComplaintOfferType> types) {
        log.debug("Request to update update complaintOffer types : {}", id);
        if(exists(id)) {
            T updateComplaintOffer = findOne(id);
            updateComplaintOffer.setTypes(types);
            updateComplaintOffer.updateLastModifiedDate();
            complaintRepository.update(updateComplaintOffer);
        }
    }

    @Override
    public void updateStatus(String id, ComplaintOfferStatus status) {
        log.debug("Request to update update complaintOffer status : {}", id);
        if(exists(id)) {
            T updateComplaintOffer = findOne(id);
            updateComplaintOffer.setStatus(status);
            updateComplaintOffer.updateLastModifiedDate();
            complaintRepository.update(updateComplaintOffer);
        }
    }

    @Override
    public ComplaintInitiator getInitiatorProfile(String authorId) {
        return complaintMapper.getInitiatorProfile(authorId);
    }



    private DateTime toDateTime(final ZonedDateTime zdt) {
        return new DateTime(zdt.toInstant().toEpochMilli(), DateTimeZone.forID(zdt.getOffset().getId()));
    }

    @Override
    public Page<T> findFilter(ComplaintFilter filter, Pageable pageable) {
        complaintRepository.findFilter(filter, pageable);
        long count = complaintRepository.countFilter(filter);
        List<T> list = Collections.EMPTY_LIST;
        if (count > 0) {
            list = complaintRepository.findFilter(filter, pageable);
        }
        return new PageImpl(list, pageable, count);
    }
}
