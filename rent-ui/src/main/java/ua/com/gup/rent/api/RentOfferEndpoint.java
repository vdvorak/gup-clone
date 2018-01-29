package ua.com.gup.rent.api;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.common.command.CommandException;
import ua.com.gup.common.dto.CommonCreateDTO;
import ua.com.gup.common.model.enumeration.CommonStatus;
import ua.com.gup.common.model.filter.OfferModeratorFilter;
import ua.com.gup.common.web.api.AbstractImageEndpoint;
import ua.com.gup.rent.command.rent.offer.CreateRentOfferCommand;
import ua.com.gup.rent.command.rent.offer.UpdateRentOfferCommand;
import ua.com.gup.rent.component.executor.RentCommandExecutor;
import ua.com.gup.rent.filter.RentOfferFilter;
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
import ua.com.gup.rent.util.RentResponseUtil;
import ua.com.gup.rent.util.security.RentSecurityUtils;
import ua.com.gup.rent.validator.rent.offer.RentOfferDTOValidator;

import javax.validation.Valid;
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
public class RentOfferEndpoint extends AbstractImageEndpoint {

    private final Logger log = LoggerFactory.getLogger(RentOfferEndpoint.class);

    @Autowired
    private RentOfferService offerService;

    @Autowired
    private RentOfferDTOValidator offerDTOValidator;

    @Autowired
    private RentCommandExecutor executor;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() != null) {
            final Class<?> clazz = binder.getTarget().getClass();
            if (RentOfferCreateDTO.class.equals(clazz) || RentOfferUpdateDTO.class.equals(clazz)) {
                binder.addValidators(offerDTOValidator);
            }
        }
    }

    @ApiOperation(
            value = "Get all the offers by filter",
            notes = "List all offer using paging",
            response = RentOfferViewShortDTO.class,
            responseContainer = "List"
    )

    @PreAuthorize("hasAuthority('CREATE_OFFER')")
    @RequestMapping(value = "/offers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createOffer(@Valid @RequestBody RentOfferCreateDTO offerCreateDTO) throws CommandException {
        log.debug("REST request to save new Offer : {}", offerCreateDTO);
        CreateRentOfferCommand createRentOfferCommand = new CreateRentOfferCommand(offerService, offerCreateDTO);
        executor.doCommand(createRentOfferCommand);
        return new ResponseEntity<>(new CommonCreateDTO(createRentOfferCommand.getObjectId()), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/offers/seo/{seoUrl}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission(#seoUrl, 'offer','EDIT')")
    public ResponseEntity<RentOfferViewDetailsDTO> updateOffer(@PathVariable(name = "seoUrl") String seoUrl,
                                                               @Valid @RequestBody RentOfferUpdateDTO offerUpdateDTO) throws CommandException {
        log.debug("REST request to update Offer : {}", offerUpdateDTO);
        if (!offerService.existsBySeoUrl(seoUrl)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UpdateRentOfferCommand updateRentOfferCommand = new UpdateRentOfferCommand(offerService, offerUpdateDTO, offerService.getRentOfferIdBySeoUrl(seoUrl));
        executor.doCommand(updateRentOfferCommand);
        return new ResponseEntity<>(HttpStatus.OK);
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
        Optional<RentOfferViewDetailsDTO> offerDetailsDTO = offerService.findOneBySeoUrlWithIncrementViewsCount(seoUrl);
        return RentResponseUtil.wrapOrNotFound(offerDetailsDTO);
    }

    @RequestMapping(value = "/offers/seo/statistic/{seoUrl}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RentOfferStatisticByDateDTO>> getOfferStatisticBySeoUrl(@PathVariable String seoUrl,
                                                                                       @RequestParam("dateStart") Long dateStartInMillisWithTimezone,
                                                                                       @RequestParam("dateEnd") Long dateEndInMillisWithTimezone) {

        log.debug("REST request to get Rent Offer viewStatistic by seoUrl : {}", seoUrl);
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
     * GET  /offers : get all my offers by status.
     *
     * @param status   the offer status
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of offers in body
     */
    @PreAuthorize("hasAuthority('READ_MY_OFFERS_BY_STATUS')")
    @RequestMapping(value = "/offers/my/{status}", method = RequestMethod.GET)
    public ResponseEntity<Page> getAllMyOffers(@PathVariable CommonStatus status, Pageable pageable) {
        log.debug("REST request to get a page of my Offers by status");
        Page<RentOfferViewShortWithModerationReportDTO> page = offerService.findAllByStatusAndUserId(status, RentSecurityUtils.getCurrentUserId(), pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('READ_OFFER') and hasPermission(#seoUrl, 'rent.offer' ,'VIEW')")
    @GetMapping(value = "/offers/my/seo/{seoUrl}")
    public ResponseEntity getMyOfferBySeoUrl(@PathVariable String seoUrl) {
        log.debug("REST request to get a my rent offer by seo url");
        Optional<RentOfferViewDetailsDTO> offerDetailsDTO = offerService.findOneBySeoUrlWithoutIncrementViewsCount(seoUrl);
        return RentResponseUtil.wrapOrNotFound(offerDetailsDTO);

    }

    /**
     * DELETE  /offers/:id : delete the "id" offer.
     *
     * @param id the id of the offerDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @PreAuthorize("hasAuthority('DELETE_OFFER') and hasPermission(#id, 'offer', 'DELETE')")
    @RequestMapping(value = "/offers/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteOffer(@PathVariable String id) {
        log.debug("REST request to delete Offer : {}", id);
        offerService.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/offers/author/{userPublicId}", method = RequestMethod.GET)
    public ResponseEntity<Page> getActiveProfileOffers(@PathVariable String userPublicId, Pageable pageable) {
        log.debug("REST request to get a page of authorId Offers by status.ACTIVE");
        if (StringUtils.isEmpty(userPublicId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Page<RentOfferViewShortWithModerationReportDTO> page = offerService.findAllByStatusAndUserPublicId(CommonStatus.ACTIVE, userPublicId, pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
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

    @ApiOperation(
            value = "Get offers coordinates by filter",
            notes = "List all offers coordinates using paging",
            response = RentOfferViewCoordinatesDTO.class
    )
    @RequestMapping(value = "/offers/coordinates", method = RequestMethod.GET)
    public ResponseEntity<List> getOffersCoordinatesByFilter(RentOfferFilter offerFilter, Pageable pageable) {
        log.debug("REST request to get a list of Rent Offers");
        List<RentOfferViewCoordinatesDTO> list = offerService.findCoordinatesByFilter(offerFilter, pageable);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * GET  /offers/search/category : get offer category by query.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of offers in body
     */
    @RequestMapping(value = "/offers/search/category", method = RequestMethod.GET)
    public ResponseEntity<List<RentOfferCategoryCountDTO>> searchCategoriesByString(@RequestParam String query, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        log.debug("REST request to get offer categories by word string");
        final List offerCategoryCountDTOS = offerService.searchCategoriesByString(query, page, size);
        return new ResponseEntity<>(offerCategoryCountDTOS, HttpStatus.OK);
    }
//-------------------- OLDER -RE-FACTORING------------------------------FROM OFFER -------------------------------------


    /**
     * PUT  /offers : Updates status  of an existing offer.
     *
     * @param status the status to be updated
     * @return the ResponseEntity with status 200 (OK) and with body the updated offerDTO,
     * or with status 400 (Bad Request) if the offerDTO is not valid,
     * or with status 500 (Internal Server Error) if the offerDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PreAuthorize("hasAuthority('UPDATE_OFFER_STATUS') and hasPermission(#id, 'offer','CHANGE_STATUS')")
    @RequestMapping(value = "/offers/{id}/status/{status}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentOfferViewDetailsDTO> changeStatus(@PathVariable String id, @PathVariable CommonStatus status) throws URISyntaxException {
        log.debug("REST request to change Rent Offer's status: id= {}, status = {}", id, status);
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
     * PUT  /offers : Updates an existing offer by moderator.
     *
     * @param offerModerationReportDTO the offerDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated offerDTO,
     * or with status 400 (Bad Request) if the offerDTO is not valid,
     * or with status 500 (Internal Server Error) if the offerDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PreAuthorize("hasAuthority('UPDATE_OFFER_MODERATOR') and hasPermission(#offerModerationReportDTO.id, 'offer','EDIT')")
    @RequestMapping(value = "/offers/moderator", method = RequestMethod.PUT)
    public ResponseEntity<RentOfferViewDetailsDTO> updateOfferByModerator(@Valid @RequestBody RentOfferModerationReportDTO offerModerationReportDTO) {
        log.debug("REST request to update Offer by moderator : {}", offerModerationReportDTO);
        RentOfferViewDetailsDTO result = offerService.save(offerModerationReportDTO);
        return RentResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    /**
     * GET  /offers : get all my offers by status.
     *
     * @param status   the offer status
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of offers in body
     */
    @RequestMapping(value = "/offers/{iserid}/{status}/seourl", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getAllMyOffers(@PathVariable String iserid, @PathVariable CommonStatus status, Pageable pageable) {
        log.debug("REST request to get a page of my Offers-seourl by status & iserId");
        if (status == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Page<RentOfferViewShortWithModerationReportDTO> page = offerService.findAllByStatusAndUserPublicId(status, iserid, pageable);
        List<String> seoUrls = new ArrayList<>();
        page.forEach(p -> seoUrls.add(p.getSeoUrl()));
        return new ResponseEntity<>(seoUrls, HttpStatus.OK);

    }

    @PreAuthorize("hasAuthority('READ_MODERATOR_OFFERS_BY_STATUS')")
    @RequestMapping(value = "/offers/moderator/{status}", method = RequestMethod.GET)
    public ResponseEntity getAllModeratorOffers(@PathVariable CommonStatus status, Pageable pageable) {
        log.debug("REST request to get a page of moderator Offers by status");
        if (status == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Page<RentOfferViewShortWithModerationReportDTO> page = offerService.findAllByStatus(status, pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('READ_MODERATOR_OFFERS_BY_FILTER')")
    @RequestMapping(value = "/offers/moderator", method = RequestMethod.GET)
    public ResponseEntity getFilteredModeratorOffers(OfferModeratorFilter filter, Pageable pageable) {

        Page<RentOfferViewShortWithModerationReportDTO> page = offerService.findByModeratorFilter(filter, pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('READ_OFFERS_VIEW')")
    @RequestMapping(value = "/offers/view/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentOfferViewDetailsDTO> getOfferById(@PathVariable String id) {
        log.debug("REST request to get Offer by ID : {}", id);
        Optional<RentOfferViewDetailsDTO> offerDetailsDTO = offerService.findOne(id);
        if (offerDetailsDTO.isPresent()) {
            return new ResponseEntity(offerDetailsDTO.get(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);

    }

}