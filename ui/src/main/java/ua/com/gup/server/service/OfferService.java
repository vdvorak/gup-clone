package ua.com.gup.server.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.gup.common.dto.offer.CommonModerationReportDTO;
import ua.com.gup.common.model.enumeration.CommonStatus;
import ua.com.gup.common.service.CommonOfferService;
import ua.com.gup.dto.offer.OfferCreateDTO;
import ua.com.gup.dto.offer.OfferUpdateDTO;
import ua.com.gup.dto.offer.statistic.OfferStatisticByDateDTO;
import ua.com.gup.dto.offer.view.OfferViewCoordinatesDTO;
import ua.com.gup.dto.offer.view.OfferViewDetailsDTO;
import ua.com.gup.dto.offer.view.OfferViewShortDTO;
import ua.com.gup.dto.offer.view.OfferViewShortWithModerationReportDTO;
import ua.com.gup.mongo.composition.domain.offer.Offer;
import ua.com.gup.mongo.model.filter.OfferFilter;
import ua.com.gup.mongo.model.filter.OfferFilterOptions;
import ua.com.gup.mongo.model.other.EntityPage;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Offer.
 */
public interface OfferService extends CommonOfferService {

    /**
     * Save a offer.
     *
     * @param offerCreateDTO the entity to save
     * @return the persisted entity
     */
    OfferViewDetailsDTO save(OfferCreateDTO offerCreateDTO);

    /**
     * Save a offer after update.
     *
     * @param offerUpdateDTO the entity to save
     * @return the persisted entity
     */
    OfferViewDetailsDTO save(OfferUpdateDTO offerUpdateDTO);

    /**
     * Save a offer modified by moderator.
     *
     * @param offerModerationReportDTO the entity to save
     * @return the persisted entity
     */
    OfferViewDetailsDTO save(CommonModerationReportDTO offerModerationReportDTO);

    /**
     * Get all the offers.
     *
     * @param offerFilter the offer filter
     * @param pageable    the offer filter
     * @return the list of entities
     */
    Page<OfferViewShortDTO> findAll(OfferFilter offerFilter, Pageable pageable);

    List<OfferViewCoordinatesDTO> findCoordinatesByFilter(OfferFilter offerFilter, Pageable pageable);

    /**
     * Get all the offers by status and author id.
     *
     * @param status   the offer status
     * @param authorId the offer authorId
     * @param pageable the offer filter
     * @return the list of entities
     */
    Page<OfferViewShortWithModerationReportDTO> findAllByStatusAndUserId(CommonStatus status, String authorId, Pageable pageable);


    /**
     * Get all the offers by status and author id.
     *
     * @param status       the offer status
     * @param userPublicId the user public id
     * @param pageable     the offer filter
     * @return the list of entities
     */
    Page<OfferViewShortWithModerationReportDTO> findAllByStatusAndUserPublicId(CommonStatus status, String userPublicId, Pageable pageable);

    /**
     * Get all the offers by status.
     *
     * @param status   the offer status
     * @param pageable the offer filter
     * @return the list of entities
     */
    Page<OfferViewShortWithModerationReportDTO> findAllByStatus(CommonStatus status, Pageable pageable);


    Optional<OfferViewDetailsDTO> findOne(String id);


    /**
     * Get one OfferDetailsDTO by seoUrl.
     *
     * @param seoUrl the seoUrl of the entity
     * @return the entity
     */
    Optional<OfferViewDetailsDTO> findOneBySeoUrl(String seoUrl);

    /**
     * Get one OfferViewShortDTOs by seoUrl.
     *
     * @param seoUrl the seoUrl of the entity
     * @return the list of entities
     */
    Page<OfferViewShortDTO> findRelevantBySeoUrl(String seoUrl, Pageable pageable);

    /**
     * Increment viewStatistic phone views by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    void incrementPhoneViews(String id);


    /**
     * Delete the "id" offer.
     *
     * @param id the id of the entity
     */
    void delete(String id);

    /**
     * Returns whether an entity can be updated by current user.
     *
     * @param offerId not be {@literal null}.
     * @return true if an user has permission for update, {@literal false} otherwise
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    @Deprecated
    boolean hasPermissionForUpdate(String offerId, String authrorId);


    /**
     * Update offer's status.
     *
     * @param id     the id of the entity
     * @param status the status to be updated
     * @return the entity
     */
    Optional<OfferViewDetailsDTO> updateStatus(String id, CommonStatus status);

    Boolean isCanUpdateStatus(String id, CommonStatus status);

    Optional<List<OfferStatisticByDateDTO>> findOfferStatisticBySeoUrlAndDateRange(String seoUrl, LocalDate dateStart, LocalDate dateEnd);

    Optional<OfferViewDetailsDTO> findOfferByIdAndAuthorId(String offerId, String authorId);


    //------------------------------------------- OLD SERVICE FUNCTION ----------------------------------------------//





    /**
     * Return EntityPage of Offers that received with offer filter options.
     *
     * @param offerFilterOptions - the OfferFilterOptions object.
     * @return - the EntityPage of Offers.
     */
    EntityPage<Offer> findOffersWihOptions(OfferFilterOptions offerFilterOptions);

    /**
     * Return id if main offer image
     *
     * @return - the ID of the main image.
     */
    String getMainOfferImage(Offer offer);

    Collection<String> getOfferContactInfoPhoneNumbersById(String offerId);

    boolean existsByIdAndStatus(String id, CommonStatus status);

    public Page<OfferViewShortDTO> findByManagerAndPublicIdAndStatus(CommonStatus status, String userPublicId, Pageable pageable);
}
