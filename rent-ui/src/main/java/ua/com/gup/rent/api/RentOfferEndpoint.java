package ua.com.gup.rent.api;


import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.rent.filter.RentOfferFilter;
import ua.com.gup.rent.model.enumeration.RentOfferImageSizeType;
import ua.com.gup.rent.model.enumeration.RentOfferStatus;
import ua.com.gup.rent.model.file.RentOfferFileWrapper;
import ua.com.gup.rent.service.dto.rent.RentOfferModerationReportDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferCategoryCountDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferCreateDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferUpdateDTO;
import ua.com.gup.rent.service.dto.rent.offer.statistic.RentOfferStatisticByDateDTO;
import ua.com.gup.rent.service.dto.rent.offer.view.RentOfferViewCoordinatesDTO;
import ua.com.gup.rent.service.dto.rent.offer.view.RentOfferViewDetailsDTO;
import ua.com.gup.rent.service.dto.rent.offer.view.RentOfferViewShortDTO;
import ua.com.gup.rent.service.dto.rent.offer.view.RentOfferViewShortWithModerationReportDTO;
import ua.com.gup.rent.service.rent.RentOfferService;
import ua.com.gup.rent.util.RentHeaderUtil;
import ua.com.gup.rent.util.RentPaginationUtil;
import ua.com.gup.rent.util.RentResponseUtil;
import ua.com.gup.rent.util.security.RentSecurityUtils;
import ua.com.gup.rent.validator.rent.offer.RentOfferDTOValidator;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Rent Offer.
 */
@RestController
@RequestMapping("/api")
public class RentOfferEndpoint {

    private static final String ENTITY_NAME = "rent.offer";
    private final Logger log = LoggerFactory.getLogger(RentOfferEndpoint.class);

    @Autowired
    private RentOfferService offerService;

    @Autowired
    private RentOfferDTOValidator offerDTOValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() != null) {
            final Class<?> clazz = binder.getTarget().getClass();
            if (RentOfferCreateDTO.class.equals(clazz) || RentOfferUpdateDTO.class.equals(clazz)) {
                binder.addValidators(offerDTOValidator);
            }
        }
    }

//-----------------------------------------------------NEW URL FROM----------------------------------------------------------

   /*

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity findAll() {
        List<RentOfferViewShortDTO> rentOfferViewShortDTOS = offerService.findAll();
        return new ResponseEntity(rentOfferViewShortDTOS, HttpStatus.OK);
    }

    @RequestMapping(path = "/{seoUrl}", method = RequestMethod.GET)
    public ResponseEntity findOne(@PathVariable(name = "seoUrl") String seoUrl) {
        List<RentOfferViewShortDTO> rentOfferViewShortDTOS = offerService.findAll();
        return new ResponseEntity("STRIKE!!!!!", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//    @PreAuthorize("hasPermission(#rentObjectId, T(ua.com.gup.rent.model.mongo.rent.RentOffer).CLASS_NAME, 'create')")
    public ResponseEntity createRentObject(RentOfferCreateDTO rentOfferCreateDTO) {
        offerService.create(rentOfferCreateDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(path = "/{id}", method = {RequestMethod.PUT})
    @PreAuthorize("hasPermission(#rentObjectId, T(ua.com.gup.rent.model.mongo.rent.RentOffer).CLASS_NAME, 'edit')")
    public ResponseEntity updateRentOffer(@PathVariable(name = "id") String rentOfferId, RentOfferUpdateDTO rentOfferUpdateDTO) {
        offerService.update(rentOfferUpdateDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
//    @PreAuthorize("hasPermission(#rentObjectId, T(ua.com.gup.rent.model.mongo.rent.RentOffer).CLASS_NAME, 'delete')")
    public ResponseEntity deleteRentObject(@PathVariable(name = "id") String rentOfferId) {
        offerService.deleteById(rentOfferId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

*/

    @ApiOperation(
            value = "Get all the offers by filter",
            notes = "List all offer using paging",
            response = RentOfferViewShortDTO.class,
            responseContainer = "List"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of user detail", response = RentOfferViewShortDTO.class),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported. " +
                            "Not taken into account if the 'query' is specified. Example = 'lastModifiedBy,desc\nprice.amount,desc'")
    })
    /**
     * GET  /offers : get all the offers by filter.
     *
     * @param offerFilter the offer filter
     * @param pageable    the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of offers in body
     */
    @RequestMapping(value = "/offers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page> getAllOffersByFilter(@RequestParam  RentOfferFilter offerFilter, Pageable pageable) {
        log.debug("REST request to get a page of Offers");
        Page<RentOfferViewShortDTO> page = offerService.findAll(offerFilter, pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    /**
     * POST  /offers : Create a new Rent offer.
     *
     * @param offerCreateDTO the OfferCreateDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new offerDTO, or with status 400 (Bad Request) if the offer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @RequestMapping(value = "/offers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentOfferViewDetailsDTO> createOffer(@Valid @RequestBody RentOfferCreateDTO offerCreateDTO) throws URISyntaxException {
        log.debug("REST request to save new Offer : {}", offerCreateDTO);
        RentOfferViewDetailsDTO result = offerService.save(offerCreateDTO);
        return ResponseEntity.created(new URI("/api/offers/" + result.getSeoUrl()))
                .headers(RentHeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /offers : Updates an existing offer.
     *
     * @param offerUpdateDTO the offerDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated offerDTO,
     * or with status 400 (Bad Request) if the offerDTO is not valid,
     * or with status 500 (Internal Server Error) if the offerDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/offers", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission(#offerUpdateDTO.id, 'offer','EDIT')")
    public ResponseEntity<RentOfferViewDetailsDTO> updateOffer(@Valid @RequestBody RentOfferUpdateDTO offerUpdateDTO) throws URISyntaxException {
        log.debug("REST request to update Offer : {}", offerUpdateDTO);
        if (!offerService.exists(offerUpdateDTO.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(RentHeaderUtil.createFailureAlert(ENTITY_NAME, "rentOfferNotFound", "Rent Offer not found")).body(null);
        }
        RentOfferViewDetailsDTO result = offerService.save(offerUpdateDTO);
        return RentResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    /**
     * GET  /offers/:seoUrl : get the "seoUrl" rent offer.
     *
     * @param seoUrl the seoUrl of the RentOfferDetailsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the RentOfferDetailsDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/offers/seo/{seoUrl}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentOfferViewDetailsDTO> getOfferBySeoUrl(@PathVariable String seoUrl) {
        log.debug("REST request to get Rent Offer : {}", seoUrl);
        Optional<RentOfferViewDetailsDTO> offerDetailsDTO = offerService.findOneBySeoUrl(seoUrl);
        return RentResponseUtil.wrapOrNotFound(offerDetailsDTO);
    }

//-------------------- OLDER -RE-FACTORING------------------------------FROM OFFER -------------------------------------



    @PreAuthorize("hasPermission(#id, 'offer','EDIT')")
    @RequestMapping(value = "/offers/edit/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentOfferViewDetailsDTO> getOfferByIdAndAuthorIdForceEdit(@PathVariable String id) {
        String authorId = RentSecurityUtils.getCurrentUserId();
        log.debug("REST request to get Offer by ID : {} and  authorId: {}", id, authorId);
        Optional<RentOfferViewDetailsDTO> offerDetailsDTO = offerService.findOfferByIdAndAuthorId(id, authorId);
        return RentResponseUtil.wrapOrNotFound(offerDetailsDTO);
    }


    @RequestMapping(value = "/offers/view/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentOfferViewDetailsDTO> getOfferById(@PathVariable String id) {
        log.debug("REST request to get Offer by ID : {}", id);
        Optional<RentOfferViewDetailsDTO> offerDetailsDTO = offerService.findOne(id);
        return RentResponseUtil.wrapOrNotFound(offerDetailsDTO);
    }


    @RequestMapping(value = "/offers/seo/statistic/{seoUrl}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RentOfferStatisticByDateDTO>> getOfferStatisticBySeoUrl(@PathVariable String seoUrl,
                                                                                       @RequestParam("dateStart") Long dateStartInMillisWithTimezone,
                                                                                       @RequestParam("dateEnd") Long dateEndInMillisWithTimezone) {

        log.debug("REST request to get Offer viewStatistic by seoUrl : {}", seoUrl);
        LocalDate dateStart = Instant.ofEpochMilli(dateStartInMillisWithTimezone).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dateEnd = Instant.ofEpochMilli(dateEndInMillisWithTimezone).atZone(ZoneId.systemDefault()).toLocalDate();

        Optional<List<RentOfferStatisticByDateDTO>> statistic = offerService.findOfferStatisticBySeoUrlAndDateRange(seoUrl, dateStart, dateEnd);
        return RentResponseUtil.wrapOrNotFound(statistic);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported. " +
                            "Not taken into account if the 'query' is specified. Example = 'lastModifiedBy,desc\nprice.amount,desc'")
    })
    /**
     * GET  /offers/:seoUrl/relevant : get the offers relevant to given seo url.
     *
     * @param seoUrl   the seoUrl
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and with body the OfferDetailsDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/offers/seo/relevant/{seoUrl}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page> getRelevantOffers(@PathVariable String seoUrl, Pageable pageable) {
        log.debug("REST request to get Offer : {}", seoUrl);
        Page<RentOfferViewShortDTO> page = offerService.findRelevantBySeoUrl(seoUrl, pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }


    /**
     * PUT  /offers : Updates an existing offer by moderator.
     *
     * @param offerModerationReportDTO the offerDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated offerDTO,
     * or with status 400 (Bad Request) if the offerDTO is not valid,
     * or with status 500 (Internal Server Error) if the offerDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR','ROLE_ADMIN') and hasPermission(#offerModerationReportDTO.id, 'offer','EDIT')")
    @RequestMapping(value = "/offers/moderator", method = RequestMethod.PUT)
    public ResponseEntity<RentOfferViewDetailsDTO> updateOfferByModerator(@Valid @RequestBody RentOfferModerationReportDTO offerModerationReportDTO) {
        log.debug("REST request to update Offer by moderator : {}", offerModerationReportDTO);
        RentOfferViewDetailsDTO result = offerService.save(offerModerationReportDTO);
        return RentResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    /**
     * PUT  /offers : Updates status  of an existing offer.
     *
     * @param status the status to be updated
     * @return the ResponseEntity with status 200 (OK) and with body the updated offerDTO,
     * or with status 400 (Bad Request) if the offerDTO is not valid,
     * or with status 500 (Internal Server Error) if the offerDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PreAuthorize("hasPermission(#id, 'offer','CHANGE_STATUS')")
    @RequestMapping(value = "/offers/{id}/status/{status}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentOfferViewDetailsDTO> changeStatus(@PathVariable String id, @PathVariable RentOfferStatus status) throws URISyntaxException {
        log.debug("REST request to change Offer's status: id= {}, status = {}", id, status);
        if (!offerService.exists(id)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if (!offerService.isCanUpdateStatus(id, status)) {
            return new ResponseEntity("Author can from (ACTIVE, DEACTIVATED) to (ACTIVE, DEACTIVATED, ARCHIVED)", HttpStatus.BAD_REQUEST);
        }
        Optional<RentOfferViewDetailsDTO> result = offerService.updateStatus(id, status);

        return RentResponseUtil.wrapOrNotFound(result);
    }

    /**
     * DELETE  /offers/:id : delete the "id" offer.
     *
     * @param id the id of the offerDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @PreAuthorize("hasPermission(#id, 'offer', 'DELETE')")
    @RequestMapping(value = "/offers/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteOffer(@PathVariable String id) {
        log.debug("REST request to delete Offer : {}", id);
        offerService.delete(id);
        return ResponseEntity.ok().headers(RentHeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @ApiOperation(
            value = "Get offers coordinates by filter",
            notes = "List all offers coordinates using paging",
            response = RentOfferViewCoordinatesDTO.class
    )
    @RequestMapping(value = "/offers/coordinates", method = RequestMethod.GET)
    public ResponseEntity<List> getOffersCoordinatesByFilter(RentOfferFilter offerFilter, Pageable pageable) {
        log.debug("REST request to get a list of Offers");
        List<RentOfferViewCoordinatesDTO> list = offerService.findCoordinatesByFilter(offerFilter, pageable);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    /**
     * GET  /offers : get all my offers by status.
     *
     * @param status   the offer status
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of offers in body
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/offers/my/{status}", method = RequestMethod.GET)
    public ResponseEntity<Page> getAllMyOffers(@PathVariable RentOfferStatus status, Pageable pageable) {
        log.debug("REST request to get a page of my Offers by status");
        Page<RentOfferViewShortWithModerationReportDTO> page = offerService.findAllByStatusAndUserId(status, RentSecurityUtils.getCurrentUserId(), pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @RequestMapping(value = "/offers/author/{userPublicId}", method = RequestMethod.GET)
    public ResponseEntity<Page> getActiveProfileOffers(@PathVariable String userPublicId, Pageable pageable) {
        log.debug("REST request to get a page of authorId Offers by status.ACTIVE");
        if (StringUtils.isEmpty(userPublicId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(RentHeaderUtil.createFailureAlert(ENTITY_NAME, "userPublicId", "Status required")).body(null);
        }
        Page<RentOfferViewShortWithModerationReportDTO> page = offerService.findAllByStatusAndUserPublicId(RentOfferStatus.ACTIVE, userPublicId, pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }


    /**
     * GET  /offers : get all my offers by status.
     *
     * @param status   the offer status
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of offers in body
     */
    @RequestMapping(value = "/offers/{iserid}/{status}/seourl", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getAllMyOffers(@PathVariable String iserid, @PathVariable RentOfferStatus status, Pageable pageable) {
        log.debug("REST request to get a page of my Offers-seourl by status & iserId");
        if (status == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(RentHeaderUtil.createFailureAlert(ENTITY_NAME, "status", "Status required")).body(null);
        }
        Page<RentOfferViewShortWithModerationReportDTO> page = offerService.findAllByStatusAndUserPublicId(status, iserid, pageable);
        List<String> seoUrls = new ArrayList<>();
        page.forEach(p -> seoUrls.add(p.getSeoUrl()));

        HttpHeaders headers = RentPaginationUtil.generatePaginationHttpHeaders(page, "/api/offers/" + iserid + "/" + status.name() + "/seourl");
        return new ResponseEntity<>(seoUrls, headers, HttpStatus.OK);
    }

    /**
     * GET  /offers : get all my offers by status.
     *
     * @param status   the offer status
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of offers in body
     */
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    @RequestMapping(value = "/offers/moderator/{status}", method = RequestMethod.GET)
    public ResponseEntity<List<RentOfferViewShortWithModerationReportDTO>> getAllModeratorOffers(@PathVariable RentOfferStatus status, Pageable pageable) {
        log.debug("REST request to get a page of moderator Offers by status");
        if (status == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(RentHeaderUtil.createFailureAlert(ENTITY_NAME, "status", "Status required")).body(null);
        }
        Page<RentOfferViewShortWithModerationReportDTO> page = offerService.findAllByStatus(status, pageable);
        HttpHeaders headers = RentPaginationUtil.generatePaginationHttpHeaders(page, "/api/offers/moderator/" + status.name());
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * PUT  /offers : update  offers base price.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of offers in body
     */
    @RequestMapping(value = "/offers/updateBasePrice", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateBasePrice() {
        log.debug("REST request to update base price: {}");
        offerService.updateActiveOffersBasePrice();
        return ResponseEntity.ok().build();
    }

    /**
     * PUT  /offers/{id}/increment/phone-views : increment phone views.
     *
     * @param id the offer id
     * @return the ResponseEntity with status 200 (OK) and the list of offers in body
     */
    @RequestMapping(value = "/offers/{id}/increment/phone-views", method = RequestMethod.PUT)
    public ResponseEntity<List<String>> incrementPhoneViews(@PathVariable String id) {
        log.debug("REST request to increment phone views");
        offerService.incrementPhoneViews(id);
        Collection<String> phoneNumbers = offerService.getOfferContactInfoPhoneNumbersById(id);
        return new ResponseEntity(phoneNumbers, HttpStatus.OK);
    }

    /**
     * GET  /offers/image/{id} : get rent offer image by id.
     *
     * @param id the offer status
     * @return the ResponseEntity with status 200 (OK) and the list of offers in body
     */
    @RequestMapping(value = "/offers/image/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> getImageByIdAndSize(@PathVariable String id,
                                                                   @RequestParam("sizeType") RentOfferImageSizeType sizeType) {
        log.debug("REST request to get offer image by id and size type");

        final RentOfferFileWrapper imageWrapper = offerService.findImageByIdAndSizeType(id, sizeType);
        return ResponseEntity.ok()
                .contentLength(imageWrapper.getLength())
                .contentType(MediaType.parseMediaType(imageWrapper.getContentType()))
                .header("Content-Disposition", "attachment; filename=" + imageWrapper.getFilename())
                .body(new InputStreamResource(imageWrapper.getInputStream()));
    }


    /**
     * GET  /offers/search/category : get offer category by query.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of offers in body
     */
    @RequestMapping(value = "/offers/search/category", method = RequestMethod.GET)
    public ResponseEntity<List<RentOfferCategoryCountDTO>> searchCategoriesByString(@RequestParam String query, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        log.debug("REST request to get offer categories by word string");
        final List<RentOfferCategoryCountDTO> offerCategoryCountDTOS = offerService.searchCategoriesByString(query, page, size);
        return new ResponseEntity<>(offerCategoryCountDTOS, null, HttpStatus.OK);
    }
}