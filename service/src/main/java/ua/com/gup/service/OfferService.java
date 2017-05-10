package ua.com.gup.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.gup.domain.enumeration.OfferStatus;
import ua.com.gup.domain.filter.OfferFilter;
import ua.com.gup.repository.file.FileWrapper;
import ua.com.gup.service.dto.*;
import ua.com.gup.service.dto.enumeration.OfferImageSizeType;

import java.util.Optional;

/**
 * Service Interface for managing Offer.
 */
public interface OfferService {

    /**
     * Save a offer.
     *
     * @param offerCreateDTO the entity to save
     * @return the persisted entity
     */
    OfferDetailsDTO save(OfferCreateDTO offerCreateDTO);

    /**
     * Save a offer after update.
     *
     * @param offerUpdateDTO the entity to save
     * @return the persisted entity
     */
    OfferDetailsDTO save(OfferUpdateDTO offerUpdateDTO);

    /**
     * Save a offer modified by moderator.
     *
     * @param offerModeratorDTO the entity to save
     * @return the persisted entity
     */
    OfferDetailsDTO save(OfferModeratorDTO offerModeratorDTO);

    /**
     * Get all the offers.
     *
     * @param offerFilter the offer filter
     * @param pageable    the offer filter
     * @return the list of entities
     */
    Page<OfferShortDTO> findAll(OfferFilter offerFilter, Pageable pageable);

    /**
     * Get all the offers by status and author id.
     *
     * @param status   the offer status
     * @param authorId the offer authorId
     * @param pageable the offer filter
     * @return the list of entities
     */
    Page<OfferShortDTO> findAllByStatusAndAuthorId(OfferStatus status, String authorId, Pageable pageable);

    /**
     * Get all the offers by status.
     *
     * @param status   the offer status
     * @param pageable the offer filter
     * @return the list of entities
     */
    Page<OfferShortDTO> findAllByStatus(OfferStatus status, Pageable pageable);


    /**
     * Get the "id" offer.
     *
     * @param id the id of the entity
     * @return the entity
     */
    OfferDetailsDTO findOne(String id);

    /**
     * Get one OfferDetailsDTO by seoUrl.
     *
     * @param seoUrl the seoUrl of the entity
     * @return the entity
     */
    Optional<OfferDetailsDTO> findOneBySeoUrl(String seoUrl);

    /**
     * Increment statistic phone views by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    void incrementPhoneViews(String id);

    /**
     * Increment statistic favorites by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    void incrementFavorites(String id);

    /**
     * Delete the "id" offer.
     *
     * @param id the id of the entity
     */
    void delete(String id);

    /**
     * Returns whether an entity with the given id exists.
     *
     * @param id must not be {@literal null}.
     * @return true if an entity with the given id exists, {@literal false} otherwise
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    boolean exists(String id);

    /**
     * Returns whether an entity can be updated by current user.
     *
     * @param id must not be {@literal null}.
     * @return true if an user has permission for update, {@literal false} otherwise
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    boolean hasPermissionForUpdate(String id);

    /**
     * Update active offers base price by current exchange rate.
     *
     * @return void
     */
    void updateActiveOffersBasePrice();

    /**
     * Update offer's status.
     *
     * @param id     the id of the entity
     * @param status the status to be updated
     * @return the entity
     */
    Optional<OfferDetailsDTO> updateStatus(String id, OfferStatus status);

    /**
     * Get offer image by id and size type.
     *
     * @param id the id of the entity
     * @param sizeType the size type of image
     * @return the entity
     */
    FileWrapper findImageByIdAndSizeType(String id, OfferImageSizeType sizeType);
}
