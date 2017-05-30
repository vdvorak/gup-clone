package ua.com.gup.service;


import ua.com.gup.domain.complaint.ComplaintOffer;
import ua.com.gup.domain.complaint.ComplaintOfferDescription;
import ua.com.gup.domain.complaint.ComplaintOfferStatus;

import java.util.List;

public interface ComplaintOfferService {

    /**
     * Save a ComplaintOffer.
     *
     * @param complaintOfferCreate the entity to save
     */
    void save(ComplaintOffer complaintOfferCreate);


    /**
     * Returns whether an entity with the given id exists.
     *
     * @param id must not be {@literal null}.
     * @return true if an entity with the given id exists, {@literal false} otherwise
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    boolean exists(String id);


    /**
     * Get the "id" ComplaintOffer.
     *
     * @param id the id of the entity
     * @return the entity
     */
    ComplaintOffer findOne(String id);


    /**
     * Get all the complaintOffers.
     *
     * @return the list of entities
     */
    List<ComplaintOffer> findAll();


    /**
     * Get one ComplaintOffer by offerId.
     *
     * @param offerId the offerId of the pageable
     * @return the entities
     */
    List<ComplaintOffer> findAllByOfferId(String offerId);


    /**
     * Get one ComplaintOffer by offerId.
     *
     * @param initiatorId the initiatorId of the pageable
     * @return the entities
     */
    List<ComplaintOffer> findAllByInitiatorId(String initiatorId);


    /**
     * Get one ComplaintOffer by offerId.
     *
     * @param status the status of the pageable
     * @return the entities
     */
    List<ComplaintOffer> findAllByStatus(ComplaintOfferStatus status);


    /**
     * Update ComplaintOffer's status.
     *
     * @param id     the id of the entity
     * @param description the description to be updated
     * @return the entity
     */
    void updateDescription(String id, ComplaintOfferDescription description);


    /**
     * Update ComplaintOffer's status.
     *
     * @param id     the id of the entity
     * @param descriptions the descriptions to be updated
     * @return the entity
     */
    void updateDescriptions(String id, List<ComplaintOfferDescription> descriptions);


    /**
     * Update ComplaintOffer's status.
     *
     * @param id     the id of the entity
     * @param status the status to be updated
     * @return the entity
     */
    void updateStatus(String id, ComplaintOfferStatus status);

}
