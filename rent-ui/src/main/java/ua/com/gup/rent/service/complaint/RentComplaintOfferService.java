package ua.com.gup.rent.service.complaint;


import ua.com.gup.rent.model.mongo.complaint.RentComplaintOffer;
import ua.com.gup.rent.model.mongo.complaint.RentComplaintOfferStatus;
import ua.com.gup.rent.model.mongo.complaint.RentComplaintOfferType;
import ua.com.gup.rent.model.rent.complaint.RentComplaintInitiator;

import java.util.List;

public interface RentComplaintOfferService {

    /**
     * Save a ComplaintOffer.
     *
     * @param complaintOfferCreate the entity to save
     */
    void save(RentComplaintOffer complaintOfferCreate);


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
    RentComplaintOffer findOne(String id);

    /**
     * Get all the complaintOffers.
     *
     * @return the list of entities
     */
    List<RentComplaintOffer> findAll();


    /**
     * Get one ComplaintOffer by offerId.
     *
     * @param offerId the offerId of the pageable
     * @return the entities
     */
    List<RentComplaintOffer> findAllByOfferId(String offerId);


    /**
     * Get one ComplaintOffer by offerId.
     *
     * @param initiatorId the initiatorId of the pageable
     * @return the entities
     */
    List<RentComplaintOffer> findAllByInitiatorId(String initiatorId);


    /**
     * Get one ComplaintOffer by offerId.
     *
     * @param status the status of the pageable
     * @return the entities
     */
    List<RentComplaintOffer> findAllByStatus(RentComplaintOfferStatus status);

    /**
     * Update ComplaintOffer's status.
     *
     * @param id     the id of the entity
     * @param type the description to be updated
     * @return the entity
     */
    void updateType(String id, RentComplaintOfferType type);

    /**
     * Update ComplaintOffer's status.
     *
     * @param id     the id of the entity
     * @param types the descriptions to be updated
     * @return the entity
     */
    void updateTypes(String id, List<RentComplaintOfferType> types);


    /**
     * Update ComplaintOffer's status.
     *
     * @param id     the id of the entity
     * @param status the status to be updated
     * @return the entity
     */
    void updateStatus(String id, RentComplaintOfferStatus status);

    RentComplaintInitiator getInitiatorProfile(String authorId);

}
