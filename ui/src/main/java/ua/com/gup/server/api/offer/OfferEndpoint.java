package ua.com.gup.server.api.offer;


import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import ua.com.gup.common.dto.offer.CommonCategoryCountDTO;
import ua.com.gup.common.dto.offer.CommonModerationReportDTO;
import ua.com.gup.common.model.enumeration.CommonStatus;
import ua.com.gup.common.service.CommonOfferService;
import ua.com.gup.common.web.api.AbstractImageEndpoint;
import ua.com.gup.dto.offer.OfferCreateDTO;
import ua.com.gup.dto.offer.OfferUpdateDTO;
import ua.com.gup.dto.offer.statistic.OfferStatisticByDateDTO;
import ua.com.gup.dto.offer.view.OfferViewCoordinatesDTO;
import ua.com.gup.dto.offer.view.OfferViewDetailsDTO;
import ua.com.gup.dto.offer.view.OfferViewShortDTO;
import ua.com.gup.dto.offer.view.OfferViewShortWithModerationReportDTO;
import ua.com.gup.mongo.model.filter.OfferFilter;
import ua.com.gup.server.service.OfferService;
import ua.com.gup.server.util.HeaderUtil;
import ua.com.gup.server.util.PaginationUtil;
import ua.com.gup.server.util.ResponseUtil;
import ua.com.gup.server.validator.OfferDTOValidator;
import ua.com.gup.util.security.SecurityUtils;

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
 * REST controller for managing Offer.
 */
@RestController
@RequestMapping("/api")
public class OfferEndpoint extends AbstractImageEndpoint {

    private static final String ENTITY_NAME = "offer";
    private final Logger log = LoggerFactory.getLogger(OfferEndpoint.class);

    @Autowired
    private OfferService offerService;
    @Autowired
    private CommonOfferService commonOfferService;

    @Autowired
    private OfferDTOValidator offerDTOValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() != null) {
            final Class<?> clazz = binder.getTarget().getClass();
            if (OfferCreateDTO.class.equals(clazz) || OfferUpdateDTO.class.equals(clazz)) {
                binder.addValidators(offerDTOValidator);
            }
        }
    }

    /**
     * POST  /offers : Create a new offer.
     *
     * @param offerCreateDTO the offerCreateDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new offerDTO, or with status 400 (Bad Request) if the offer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @CrossOrigin
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @RequestMapping(value = "/offers", method = RequestMethod.POST)
    public ResponseEntity<OfferViewDetailsDTO> createOffer(@Valid @RequestBody OfferCreateDTO offerCreateDTO
    ) throws URISyntaxException {
        log.debug("REST request to save new Offer : {}", offerCreateDTO);

        OfferViewDetailsDTO result = offerService.save(offerCreateDTO);
        return ResponseEntity.created(new URI("/api/offers/" + result.getSeoUrl()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * GET  /offers/:seoUrl : get the "seoUrl" offer.
     *
     * @param seoUrl the seoUrl of the OfferDetailsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the OfferDetailsDTO, or with status 404 (Not Found)
     */
    @CrossOrigin
    @RequestMapping(value = "/offers/seo/{seoUrl}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferViewDetailsDTO> getOfferBySeoUrl(@PathVariable String seoUrl) {
        log.debug("REST request to get Offer : {}", seoUrl);
        Optional<OfferViewDetailsDTO> offerDetailsDTO = offerService.findOneBySeoUrl(seoUrl);
        return ResponseUtil.wrapOrNotFound(offerDetailsDTO);
    }

    @CrossOrigin
    @PreAuthorize("hasPermission(#id, 'offer','EDIT')")
    @RequestMapping(value = "/offers/edit/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferViewDetailsDTO> getOfferByIdAndAuthorIdForceEdit(@PathVariable String id) {
        String authorId = SecurityUtils.getCurrentUserId();
        log.debug("REST request to get Offer by ID : {} and  authorId: {}", id, authorId);
        Optional<OfferViewDetailsDTO> offerDetailsDTO = offerService.findOfferByIdAndAuthorId(id, authorId);
        return ResponseUtil.wrapOrNotFound(offerDetailsDTO);
    }


    @CrossOrigin
    @RequestMapping(value = "/offers/view/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferViewDetailsDTO> getOfferById(@PathVariable String id) {
        log.debug("REST request to get Offer by ID : {}", id);
        Optional<OfferViewDetailsDTO> offerDetailsDTO = offerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(offerDetailsDTO);
    }


    @CrossOrigin
    @RequestMapping(value = "/offers/seo/statistic/{seoUrl}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OfferStatisticByDateDTO>> getOfferStatisticBySeoUrl(@PathVariable String seoUrl,
                                                                                   @RequestParam("dateStart") Long dateStartInMillisWithTimezone,
                                                                                   @RequestParam("dateEnd") Long dateEndInMillisWithTimezone) {

        log.debug("REST request to get Offer viewStatistic by seoUrl : {}", seoUrl);
        LocalDate dateStart = Instant.ofEpochMilli(dateStartInMillisWithTimezone).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dateEnd = Instant.ofEpochMilli(dateEndInMillisWithTimezone).atZone(ZoneId.systemDefault()).toLocalDate();

        Optional<List<OfferStatisticByDateDTO>> statistic = offerService.findOfferStatisticBySeoUrlAndDateRange(seoUrl, dateStart, dateEnd);
        return ResponseUtil.wrapOrNotFound(statistic);
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
    @CrossOrigin
    @RequestMapping(value = "/offers/seo/relevant/{seoUrl}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page> getRelevantOffers(@PathVariable String seoUrl, Pageable pageable) {
        log.debug("REST request to get Offer : {}", seoUrl);
        Page<OfferViewShortDTO> page = offerService.findRelevantBySeoUrl(seoUrl, pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
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
    @CrossOrigin
    @RequestMapping(value = "/offers", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission(#offerUpdateDTO.id, 'offer','EDIT')")
    public ResponseEntity<OfferViewDetailsDTO> updateOffer(@Valid @RequestBody OfferUpdateDTO offerUpdateDTO) throws URISyntaxException {
        log.debug("REST request to update Offer : {}", offerUpdateDTO);
        if (!offerService.exists(offerUpdateDTO.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "offernotfound", "Offer not found")).body(null);
        }
        OfferViewDetailsDTO result = offerService.save(offerUpdateDTO);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
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
    @CrossOrigin
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR','ROLE_ADMIN') and hasPermission(#offerModerationReportDTO.id, 'offer','EDIT')")
    @RequestMapping(value = "/offers/moderator", method = RequestMethod.PUT)
    public ResponseEntity<OfferViewDetailsDTO> updateOfferByModerator(@Valid @RequestBody CommonModerationReportDTO offerModerationReportDTO) {
        log.debug("REST request to update Offer by moderator : {}", offerModerationReportDTO);
        OfferViewDetailsDTO result = offerService.save(offerModerationReportDTO);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
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
    @CrossOrigin
    @PreAuthorize("hasPermission(#id, 'offer','CHANGE_STATUS')")
    @RequestMapping(value = "/offers/{id}/status/{status}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferViewDetailsDTO> changeStatus(@PathVariable String id, @PathVariable CommonStatus status) throws URISyntaxException {
        log.debug("REST request to change Offer's status: id= {}, status = {}", id, status);
        if (!offerService.exists(id)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if (!offerService.isCanUpdateStatus(id, status)) {
            return new ResponseEntity("Author can from (ACTIVE, DEACTIVATED) to (ACTIVE, DEACTIVATED, ARCHIVED)", HttpStatus.BAD_REQUEST);
        }
        Optional<OfferViewDetailsDTO> result = offerService.updateStatus(id, status);

        return ResponseUtil.wrapOrNotFound(result);
    }

    /**
     * DELETE  /offers/:id : delete the "id" offer.
     *
     * @param id the id of the offerDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @CrossOrigin
    @PreAuthorize("hasPermission(#id, 'offer', 'DELETE')")
    @RequestMapping(value = "/offers/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteOffer(@PathVariable String id) {
        log.debug("REST request to delete Offer : {}", id);
        offerService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @ApiOperation(
            value = "Get all the offers by filter",
            notes = "List all offer using paging",
            response = OfferViewShortDTO.class,
            responseContainer = "List"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of user detail", response = OfferViewShortDTO.class),
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
    @CrossOrigin
    @RequestMapping(value = "/offers/", method = RequestMethod.GET)
    public ResponseEntity<Page> getAllOffersByFilter(OfferFilter offerFilter, Pageable pageable) {
        log.debug("REST request to get a page of Offers");
        Page<OfferViewShortDTO> page = offerService.findAll(offerFilter, pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get offers coordinates by filter",
            notes = "List all offers coordinates using paging",
            response = OfferViewCoordinatesDTO.class
    )
    @CrossOrigin
    @RequestMapping(value = "/offers/coordinates", method = RequestMethod.GET)
    public ResponseEntity<List> getOffersCoordinatesByFilter(OfferFilter offerFilter, Pageable pageable) {
        log.debug("REST request to get a list of Offers");
        List<OfferViewCoordinatesDTO> list = offerService.findCoordinatesByFilter(offerFilter, pageable);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    /**
     * GET  /offers : get all my offers by status.
     *
     * @param status   the offer status
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of offers in body
     */
    @CrossOrigin
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/offers/my/{status}", method = RequestMethod.GET)
    public ResponseEntity<Page> getAllMyOffers(@PathVariable CommonStatus status, Pageable pageable) {
        log.debug("REST request to get a page of my Offers by status");
        Page<OfferViewShortWithModerationReportDTO> page = offerService.findAllByStatusAndUserId(status, SecurityUtils.getCurrentUserId(), pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/offers/author/{userPublicId}", method = RequestMethod.GET)
    public ResponseEntity<Page> getActiveProfileOffers(@PathVariable String userPublicId, Pageable pageable) {
        log.debug("REST request to get a page of authorId Offers by status.ACTIVE");
        if (StringUtils.isEmpty(userPublicId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "userPublicId", "Status required")).body(null);
        }
        Page<OfferViewShortWithModerationReportDTO> page = offerService.findAllByStatusAndUserPublicId(CommonStatus.ACTIVE, userPublicId, pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }


    /**
     * GET  /offers : get all my offers by status.
     *
     * @param status   the offer status
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of offers in body
     */
    @CrossOrigin
    @RequestMapping(value = "/offers/{iserid}/{status}/seourl", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getAllMyOffers(@PathVariable String iserid, @PathVariable CommonStatus status, Pageable pageable) {
        log.debug("REST request to get a page of my Offers-seourl by status & iserId");
        if (status == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "status", "Status required")).body(null);
        }
        Page<OfferViewShortWithModerationReportDTO> page = offerService.findAllByStatusAndUserPublicId(status, iserid, pageable);
        List<String> seoUrls = new ArrayList<>();
        page.forEach(p -> seoUrls.add(p.getSeoUrl()));

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/offers/" + iserid + "/" + status.name() + "/seourl");
        return new ResponseEntity<>(seoUrls, headers, HttpStatus.OK);
    }

    /**
     * GET  /offers : get all my offers by status.
     *
     * @param status   the offer status
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of offers in body
     */
    @CrossOrigin
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    @RequestMapping(value = "/offers/moderator/{status}", method = RequestMethod.GET)
    public ResponseEntity getAllModeratorOffers(@PathVariable CommonStatus status, Pageable pageable) {
        log.debug("REST request to get a page of moderator Offers by status");
        if (status == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "status", "Status required")).body(null);
        }
        Page<OfferViewShortWithModerationReportDTO> page = offerService.findAllByStatus(status, pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    /**
     * PUT  /offers/{id}/increment/phone-views : increment phone views.
     *
     * @param id the offer id
     * @return the ResponseEntity with status 200 (OK) and the list of offers in body
     */
    @CrossOrigin
    @RequestMapping(value = "/offers/{id}/increment/phone-views", method = RequestMethod.PUT)
    public ResponseEntity<List<String>> incrementPhoneViews(@PathVariable String id) {
        log.debug("REST request to increment phone views");
        offerService.incrementPhoneViews(id);
        Collection<String> phoneNumbers = offerService.getOfferContactInfoPhoneNumbersById(id);
        return new ResponseEntity(phoneNumbers, HttpStatus.OK);
    }

    /**
     * GET  /offers/search/category : get offer category by query.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of offers in body
     */
    @CrossOrigin
    @RequestMapping(value = "/offers/search/category", method = RequestMethod.GET)
    public ResponseEntity<List<CommonCategoryCountDTO>> searchCategoriesByString(@RequestParam String query, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        log.debug("REST request to get offer categories by word string");
        final List<CommonCategoryCountDTO> offerCategoryCountDTOS = commonOfferService.searchCategoriesByString(query, page, size);
        return new ResponseEntity<>(offerCategoryCountDTOS, null, HttpStatus.OK);
    }
}
