package ua.com.gup.web.rest;

import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.repository.filter.OfferFilter;
import ua.com.gup.service.OfferService;
import ua.com.gup.service.dto.OfferCreateDTO;
import ua.com.gup.service.dto.OfferDetailsDTO;
import ua.com.gup.service.dto.OfferShortDTO;
import ua.com.gup.service.dto.OfferUpdateDTO;
import ua.com.gup.web.rest.util.HeaderUtil;
import ua.com.gup.web.rest.util.PaginationUtil;
import ua.com.gup.web.rest.util.ResponseUtil;

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
    @Qualifier(value = "OfferServiceNew")
    private OfferService offerService;

    /**
     * POST  /offers : Create a new offer.
     *
     * @param offerCreateDTO the offerCreateDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new offerDTO, or with status 400 (Bad Request) if the offer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/offers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferDetailsDTO> createOffer(@Valid @RequestBody OfferCreateDTO offerCreateDTO) throws URISyntaxException {
        log.debug("REST request to save Offer : {}", offerCreateDTO);
        OfferDetailsDTO result = offerService.save(offerCreateDTO);
        return ResponseEntity.created(new URI("/api/offers/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * GET  /offers/:id : get the "id" offer.
     *
     * @param id the id of the offerDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the offerDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/offers/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferDetailsDTO> getOffer(@PathVariable String id) {
        log.debug("REST request to get Offer : {}", id);
        OfferDetailsDTO offerDetailsDTO = offerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(offerDetailsDTO));
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
    public ResponseEntity<OfferDetailsDTO> updateOffer(@Valid @RequestBody OfferUpdateDTO offerUpdateDTO) throws URISyntaxException {
        log.debug("REST request to update Offer : {}", offerUpdateDTO);
        if (!offerService.exists(offerUpdateDTO.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "offernotfound", "Offer not found")).body(null);
        }
        if (offerService.hasPermissionForUpdate(offerUpdateDTO.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new offer cannot already have an ID")).body(null);
        }
        OfferDetailsDTO result = offerService.save(offerUpdateDTO);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
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
            response = OfferShortDTO.class,
            responseContainer = "List"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of user detail", response = OfferShortDTO.class),
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
    public ResponseEntity<List<OfferShortDTO>> getAllOffersByFilter(OfferFilter offerFilter, Pageable pageable) {
        log.debug("REST request to get a page of Offers");
        Page<OfferShortDTO> page = offerService.findAll(offerFilter, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/offers/");
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

}
