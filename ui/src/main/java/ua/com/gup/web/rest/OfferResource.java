package ua.com.gup.web.rest;

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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.domain.enumeration.OfferStatus;
import ua.com.gup.domain.filter.OfferFilter;
import ua.com.gup.repository.file.FileWrapper;
import ua.com.gup.service.OfferService;
import ua.com.gup.service.dto.offer.enumeration.OfferImageSizeType;
import ua.com.gup.service.dto.offer.OfferCreateDTO;
import ua.com.gup.service.dto.offer.OfferModeratorDTO;
import ua.com.gup.service.dto.offer.OfferUpdateDTO;
import ua.com.gup.service.dto.offer.view.OfferViewDetailsDTO;
import ua.com.gup.service.dto.offer.view.OfferViewShortDTO;
import ua.com.gup.service.security.SecurityUtils;
import ua.com.gup.web.rest.util.HeaderUtil;
import ua.com.gup.web.rest.util.PaginationUtil;
import ua.com.gup.web.rest.util.ResponseUtil;
import ua.com.gup.web.rest.validator.OfferDTOValidator;
import ua.com.itproekt.gup.model.profiles.UserRole;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Offer.
 */
@RestController
@RequestMapping("/api")
public class OfferResource {

    private static final String ENTITY_NAME = "offer";
    private final Logger log = LoggerFactory.getLogger(OfferResource.class);

    @Autowired
    private OfferService offerService;

    @Autowired
    private OfferDTOValidator offerDTOValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(offerDTOValidator);
    }

    /**
     * POST  /offers : Create a new offer.
     *
     * @param offerCreateDTO the offerCreateDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new offerDTO, or with status 400 (Bad Request) if the offer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/offers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferViewDetailsDTO> createOffer(@Valid @RequestBody OfferCreateDTO offerCreateDTO) throws URISyntaxException {
        log.debug("REST request to save new Offer : {}", offerCreateDTO);
        if (!SecurityUtils.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "unauthorized", "Need authorization")).body(null);
        }
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_USER)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_USER'")).body(null);
        }
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
    @RequestMapping(value = "/offers/{seoUrl}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferViewDetailsDTO> getOffer(@PathVariable String seoUrl) {
        log.debug("REST request to get Offer : {}", seoUrl);
        Optional<OfferViewDetailsDTO> offerDetailsDTO = offerService.findOneBySeoUrl(seoUrl);
        return ResponseUtil.wrapOrNotFound(offerDetailsDTO);
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
    public ResponseEntity<OfferViewDetailsDTO> updateOffer(@Valid @RequestBody OfferUpdateDTO offerUpdateDTO) throws URISyntaxException {
        log.debug("REST request to update Offer : {}", offerUpdateDTO);
        if (!offerService.exists(offerUpdateDTO.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "offernotfound", "Offer not found")).body(null);
        }
        if (offerService.hasPermissionForUpdate(offerUpdateDTO.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User hasn't permission for update")).body(null);
        }
        OfferViewDetailsDTO result = offerService.save(offerUpdateDTO);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    /**
     * PUT  /offers : Updates an existing offer by moderator.
     *
     * @param offerModeratorDTO the offerDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated offerDTO,
     * or with status 400 (Bad Request) if the offerDTO is not valid,
     * or with status 500 (Internal Server Error) if the offerDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/offers/moderator", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferViewDetailsDTO> updateOfferByModerator(@Valid @RequestBody OfferModeratorDTO offerModeratorDTO) throws URISyntaxException {
        log.debug("REST request to update Offer by moderator : {}", offerModeratorDTO);
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_MODERATOR)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in 'ROLE_MODERATOR'")).body(null);
        }
        OfferViewDetailsDTO result = offerService.save(offerModeratorDTO);
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
    @RequestMapping(value = "/offers/{id}/status/{status}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferViewDetailsDTO> changeStatus(@RequestParam String id, @RequestParam OfferStatus status) throws URISyntaxException {
        log.debug("REST request to change Offer's status: id= {}, status = {}", id, status);
        Optional<OfferViewDetailsDTO> result = offerService.updateStatus(id, status);
        if (!result.isPresent()) {
            if (!offerService.exists(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "offernotfound", "Offer not found")).body(null);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "Author can from (ACTIVE, DEACTIVATED) to (ACTIVE, DEACTIVATED, ARCHIVED)")).body(null);
            }
        }
        return ResponseUtil.wrapOrNotFound(result);
    }

    /**
     * DELETE  /offers/:id : delete the "id" offer.
     *
     * @param id the id of the offerDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
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
                            "Not taken into account if the 'query' is specified.")
    })
    /**
     * GET  /offers : get all the offers by filter.
     *
     * @param offerFilter the offer filter
     * @param pageable    the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of offers in body
     */
    @RequestMapping(value = "/offers/", method = RequestMethod.GET)
    public ResponseEntity<List<OfferViewShortDTO>> getAllOffersByFilter(OfferFilter offerFilter, Pageable pageable) {
        log.debug("REST request to get a page of Offers");
        Page<OfferViewShortDTO> page = offerService.findAll(offerFilter, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/offers/");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /offers : get all my offers by status.
     *
     * @param status   the offer status
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of offers in body
     */
    @RequestMapping(value = "/offers/my/{status}", method = RequestMethod.GET)
    public ResponseEntity<List<OfferViewShortDTO>> getAllMyOffers(@PathVariable OfferStatus status, Pageable pageable) {
        log.debug("REST request to get a page of my Offers by status");
        if (!SecurityUtils.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User isn't authenticated")).body(null);
        }
        if (status == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "status", "Status required")).body(null);
        }
        Page<OfferViewShortDTO> page = offerService.findAllByStatusAndAuthorId(status, SecurityUtils.getCurrentUserId(), pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/offers/my/" + status.name());
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /offers : get all my offers by status.
     *
     * @param status   the offer status
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of offers in body
     */
    @RequestMapping(value = "/offers/moderator/{status}", method = RequestMethod.GET)
    public ResponseEntity<List<OfferViewShortDTO>> getAllModeratorOffers(@PathVariable OfferStatus status, Pageable pageable) {
        log.debug("REST request to get a page of moderator Offers by status");
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_MODERATOR)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_MODERATOR'")).body(null);
        }
        if (status == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "status", "Status required")).body(null);
        }
        Page<OfferViewShortDTO> page = offerService.findAllByStatusAndAuthorId(status, SecurityUtils.getCurrentUserId(), pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/offers/moderator/" + status.name());
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
    public ResponseEntity<Void> incrementPhoneViews(@PathVariable String id) {
        log.debug("REST request to increment phone views");
        offerService.incrementPhoneViews(id);
        return ResponseEntity.ok().build();
    }

    /**
     * PUT  /offers/{id}/increment/phone-views : increment phone views statistic.
     *
     * @param id the offer id
     * @return the ResponseEntity with status 200 (OK) and Void
     */
    @RequestMapping(value = "/offers/{id}/increment/favorites", method = RequestMethod.PUT)
    public ResponseEntity<Void> incrementFavorites(@PathVariable String id) {
        log.debug("REST request to increment favorites");
        offerService.incrementFavorites(id);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /offers/image/{id} : get offer image by id.
     *
     * @param id the offer status
     * @return the ResponseEntity with status 200 (OK) and the list of offers in body
     */
    @RequestMapping(value = "/offers/image/{id}/{sizeType}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> getImageByIdAndSize(@PathVariable String id, @PathVariable OfferImageSizeType sizeType) {
        log.debug("REST request to get offer image by id and size type");

        final FileWrapper imageWrapper = offerService.findImageByIdAndSizeType(id, sizeType);
        return ResponseEntity.ok()
                .contentLength(imageWrapper.getLength())
                .contentType(MediaType.parseMediaType(imageWrapper.getContentType()))
                .header("Content-Disposition", "attachment; filename=" + imageWrapper.getFilename())
                .body(new InputStreamResource(imageWrapper.getInputStream()));
    }
}
