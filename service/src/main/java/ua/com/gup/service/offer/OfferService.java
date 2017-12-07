package ua.com.gup.service.offer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ua.com.gup.dto.offer.OfferCategoryCountDTO;
import ua.com.gup.dto.offer.OfferCreateDTO;
import ua.com.gup.dto.offer.OfferModerationReportDTO;
import ua.com.gup.dto.offer.OfferUpdateDTO;
import ua.com.gup.dto.offer.enumeration.OfferImageSizeType;
import ua.com.gup.dto.offer.statistic.OfferStatisticByDateDTO;
import ua.com.gup.dto.offer.view.OfferViewCoordinatesDTO;
import ua.com.gup.dto.offer.view.OfferViewDetailsDTO;
import ua.com.gup.dto.offer.view.OfferViewShortDTO;
import ua.com.gup.dto.offer.view.OfferViewShortWithModerationReportDTO;
import ua.com.gup.mongo.composition.domain.offer.Offer;
import ua.com.gup.mongo.model.enumeration.OfferStatus;
import ua.com.gup.mongo.model.file.FileWrapper;
import ua.com.gup.mongo.model.filter.OfferFilter;
import ua.com.gup.mongo.model.filter.OfferFilterOptions;
import ua.com.gup.mongo.model.offer.OfferRegistration;
import ua.com.gup.mongo.model.other.EntityPage;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    OfferViewDetailsDTO save(OfferModerationReportDTO offerModerationReportDTO);

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
    Page<OfferViewShortWithModerationReportDTO> findAllByStatusAndUserId(OfferStatus status, String authorId, Pageable pageable);


    /**
     * Get all the offers by status and author id.
     *
     * @param status       the offer status
     * @param userPublicId the user public id
     * @param pageable     the offer filter
     * @return the list of entities
     */
    Page<OfferViewShortWithModerationReportDTO> findAllByStatusAndUserPublicId(OfferStatus status, String userPublicId, Pageable pageable);

    /**
     * Get all the offers by status.
     *
     * @param status   the offer status
     * @param pageable the offer filter
     * @return the list of entities
     */
    Page<OfferViewShortWithModerationReportDTO> findAllByStatus(OfferStatus status, Pageable pageable);


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
     * @param offerId not be {@literal null}.
     * @return true if an user has permission for update, {@literal false} otherwise
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    @Deprecated
    boolean hasPermissionForUpdate(String offerId, String authrorId);

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
    Optional<OfferViewDetailsDTO> updateStatus(String id, OfferStatus status);

    Boolean isCanUpdateStatus(String id, OfferStatus status);


    /**
     * Get offer image by id and size type.
     *
     * @param id       the id of the entity
     * @param sizeType the size type of image
     * @return the entity
     */
    FileWrapper findImageByIdAndSizeType(String id, OfferImageSizeType sizeType);

    /**
     * Get one offer categories by search word.
     *
     * @param string the string
     * @param page   the page
     * @param size   the size
     * @return the list of entities
     */
    List<OfferCategoryCountDTO> searchCategoriesByString(String string, int page, int size);

    Optional<List<OfferStatisticByDateDTO>> findOfferStatisticBySeoUrlAndDateRange(String seoUrl, LocalDate dateStart, LocalDate dateEnd);

    Optional<OfferViewDetailsDTO> findOfferByIdAndAuthorId(String offerId, String authorId);


    //------------------------------------------- OLD SERVICE FUNCTION ----------------------------------------------//

    /**
     * Create one offer and return it.
     *
     * @param offer - the offer object.
     */
    void create(Offer offer);


    /**
     * Return one offer by it's id.
     *
     * @param offerId - the offer id
     * @return - the offer object
     */
    Offer findById(String offerId);

    /**
     * Return one offer by it's seo Key.
     *
     * @param seoKey - the seo key of the offer.
     * @return - the offer.
     */
    Offer findBySeoKey(String seoKey);


    /**
     * Return true if offer exist.
     *
     * @param id - the offer id.
     * @return - the true or false.
     */
    boolean offerExists(String id);

    /**
     * Return EntityPage of Offers that received with offer filter options.
     *
     * @param offerFilterOptions - the OfferFilterOptions object.
     * @return - the EntityPage of Offers.
     */
    EntityPage<Offer> findOffersWihOptions(OfferFilterOptions offerFilterOptions);

    /**
     * Edit offer and return new updated one.
     *
     * @param oldOffer - the Offer which we need to update from.
     * @return - the new Offer.
     */
    Offer edit(Offer oldOffer);

    /**
     * This method edit offer and previously check specific fields for update and can change moderator status if some
     * of the field were updated.
     *
     * @param offerRegistration - the OfferRegistration object.
     * @param files             - the array fo MultiPartFile - images from client side.
     * @return - the ResponseEntity object for the REST controller.
     */
    ResponseEntity<String> editByUser(OfferRegistration offerRegistration, MultipartFile[] files);


    /**
     * Change the active status of the offer.
     *
     * @param offerId  - the offer ID.
     * @param isActive - the true or false.
     */
    void setActive(String offerId, boolean isActive);


    /**
     * Return id if main offer image
     *
     * @return - the ID of the main image.
     */
    String getMainOfferImage(Offer offer);

    Collection<String> getOfferContactInfoPhoneNumbersById(String offerId);

    boolean existsByIdAndStatus(String id, OfferStatus status);
}
