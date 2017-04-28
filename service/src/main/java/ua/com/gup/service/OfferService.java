package ua.com.gup.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.gup.service.dto.OfferCreateDTO;
import ua.com.gup.service.dto.OfferDetailsDTO;
import ua.com.gup.service.dto.OfferShortDTO;
import ua.com.gup.service.dto.OfferUpdateDTO;
import ua.com.gup.repository.filter.OfferFilter;

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
     *  Get all the offers.
     *
     *  @param offerFilter the offer filter
     *  @param pageable the offer filter
     *  @return the list of entities
     */
    Page<OfferShortDTO> findAll(OfferFilter offerFilter, Pageable pageable);

    /**
     *  Get the "id" offer.
     *
     *  @param id the id of the entity
     *  @return the entity
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
     *  Delete the "id" offer.
     *
     *  @param id the id of the entity
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
}
