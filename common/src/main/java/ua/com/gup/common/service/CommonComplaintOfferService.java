package ua.com.gup.common.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.gup.common.model.complaint.*;

import java.util.List;

public interface CommonComplaintOfferService<T extends CommonComplaint> {

    /**
     * Save a ComplaintOffer.
     *
     * @param complaintOfferCreate the entity to save
     */
    void save(T complaintOfferCreate);


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
    T findOne(String id);

    /**
     * Get all the complaintOffers.
     *
     * @return the list of entities
     */
    List<T> findAll();


    /**
     * Get one ComplaintOffer by offerId.
     *
     * @param offerId the offerId of the pageable
     * @return the entities
     */
    List<T> findAllByOfferId(String offerId);


    /**
     * Get one ComplaintOffer by offerId.
     *
     * @param initiatorId the initiatorId of the pageable
     * @return the entities
     */
    List<T> findAllByInitiatorId(String initiatorId);


    /**
     * Get one ComplaintOffer by offerId.
     *
     * @param status the status of the pageable
     * @return the entities
     */
    List<T> findAllByStatus(ComplaintOfferStatus status);

    /**
     * Update ComplaintOffer's status.
     *
     * @param id     the id of the entity
     * @param type the description to be updated
     * @return the entity
     */
    void updateType(String id, ComplaintOfferType type);

    /**
     * Update ComplaintOffer's status.
     *
     * @param id     the id of the entity
     * @param types the descriptions to be updated
     * @return the entity
     */
    void updateTypes(String id, List<ComplaintOfferType> types);


    /**
     * Update ComplaintOffer's status.
     *
     * @param id     the id of the entity
     * @param status the status to be updated
     * @return the entity
     */
    void updateStatus(String id, ComplaintOfferStatus status);

    ComplaintInitiator getInitiatorProfile(String authorId);

    Page<T> findFilter(ComplaintFilter filter, Pageable pageable);

}
