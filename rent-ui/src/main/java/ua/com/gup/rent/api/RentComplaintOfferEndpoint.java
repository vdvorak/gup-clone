package ua.com.gup.rent.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.common.model.complaint.ComplaintFilter;
import ua.com.gup.common.model.complaint.ComplaintOfferStatus;
import ua.com.gup.common.model.complaint.ComplaintOfferType;
import ua.com.gup.rent.model.mongo.complaint.RentComplaintOffer;
import ua.com.gup.rent.service.complaint.RentComplaintOfferService;
import ua.com.gup.rent.util.security.RentSecurityUtils;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * REST controller for managing ComplaintOffer.
 */
@RestController
@RequestMapping("/api")
public class RentComplaintOfferEndpoint {

    private final Logger log = LoggerFactory.getLogger(RentComplaintOfferEndpoint.class);

    @Autowired
    private RentComplaintOfferService complaintOfferService;

    /**
     * POST  /complaints : Create a new complaintOffer.
     *
     * @param complaintOffer the complaintOffer to create
     * @return the ResponseEntity with status 201 (Created) and with body the new complaintOffer, or with status 400 (Bad Request) if the offer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PreAuthorize("hasAuthority('CREATE_COMPLAINT')")
    @RequestMapping(value = "/complaints", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createComplaintOffer(@Valid @RequestBody RentComplaintOffer complaintOffer) throws URISyntaxException {
        log.debug("REST request to save new ComplaintOffer : {}", complaintOffer);
        complaintOffer.setInitiator(complaintOfferService.getInitiatorProfile(RentSecurityUtils.getCurrentUserId()));
        complaintOfferService.save(complaintOffer);
        return new ResponseEntity(complaintOffer, HttpStatus.CREATED);
    }


    /**
     * PUT  /complaints : Update a staus complaintOffer.
     *
     * @param complaintOffer the complaintOffer to update
     * @return the ResponseEntity with status 200 (Ok) and with body the new complaintOffer, or with status 400 (Bad Request) if the offer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */

    @PreAuthorize("hasAuthority('UPDATE_COMPLAINT_STATUS')")
    @RequestMapping(value = "/complaints/{id}/status", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateComplaintOfferStaus(@Valid @RequestBody RentComplaintOffer complaintOffer, @PathVariable("id") String id) throws URISyntaxException {
        log.debug("REST request to update ComplaintOffer : {}", complaintOffer);
        complaintOfferService.updateStatus(id, complaintOffer.getStatus());
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * PUT  /complaints : Update a staus complaintOffer.
     *
     * @param type the complaintOffer to update
     * @return the ResponseEntity with status 200 (Ok) and with body the new complaintOffer, or with status 400 (Bad Request) if the offer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */

    @PreAuthorize("hasAuthority('UPDATE_COMPLAINT_TYPE')")
    @RequestMapping(value = "/complaints/{id}/type", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateComplaintOfferDescription(@RequestBody ComplaintOfferType type, @PathVariable("id") String id) throws URISyntaxException {
        log.debug("REST request to update ComplaintOffer : {}", type);
        complaintOfferService.updateType(id, type);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * PUT  /complaints : Update a staus complaintOffer.
     *
     * @param complaintOffer the complaintOffer to update
     * @return the ResponseEntity with status 200 (Ok) and with body the new complaintOffer, or with status 400 (Bad Request) if the offer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PreAuthorize("hasAuthority('UPDATE_COMPLAINT_TYPES')")
    @RequestMapping(value = "/complaints/{id}/types", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateComplaintOfferTypes(@RequestBody RentComplaintOffer complaintOffer, @PathVariable("id") String id) throws URISyntaxException {
        log.debug("REST request to update ComplaintOffer : {}", complaintOffer);
        complaintOfferService.updateTypes(id, complaintOffer.getTypes());
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * GET  /types : get types complaintOffer.
     *
     * @return the ResponseEntity with status 200 (Ok) and with body the new complaintOffer
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */

    @RequestMapping(value = "/complaints/types", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> getComplaintOfferTypes() throws URISyntaxException {
        log.info("REST request to get Types");
        final List<ComplaintOfferType> types = Arrays.stream(ComplaintOfferType.values()).collect(Collectors.toList());
        return new ResponseEntity(types, HttpStatus.OK);
    }

    /**
     * GET  /statuses : get types complaintOffer.
     *
     * @return the ResponseEntity with status 200 (Ok) and with body the new complaintOffer
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PreAuthorize("hasAuthority('READ_COMPLAINT_STATUSES')")
    @RequestMapping(value = "/complaints/statuses", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> getComplaintOfferStatuses() throws URISyntaxException {
        log.debug("REST request to get Statuses");
        final Map<String, String> statuses = Arrays.stream(ComplaintOfferStatus.values()).collect(Collectors.toMap(ComplaintOfferStatus::name, ComplaintOfferStatus::toString));
        return new ResponseEntity(statuses, HttpStatus.OK);
    }


    /**
     * GET  /complaints : Get a staus complaintOffer.
     *
     * @param id the complaintOffer to gte
     * @return the ResponseEntity with status 200 (Ok) and with body the new complaintOffer, or with status 400 (Bad Request) if the offer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PreAuthorize("hasAuthority('READ_COMPLAINT')")
    @RequestMapping(value = "/complaints/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentComplaintOffer> getComplaintOfferById(@PathVariable("id") String id) throws URISyntaxException {
        log.debug("REST request to get ComplaintOffer : {}", id);
        return new ResponseEntity(complaintOfferService.findOne(id), HttpStatus.OK);
    }


    /**
     * GET  /complaints : get a offerId complaintOffer.
     *
     * @return the ResponseEntity with status 200 (Ok) and with body the new complaintOffer, or with status 400 (Bad Request) if the offer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PreAuthorize("hasAuthority('READ_COMPLAINTS_BY_OFFER')")
    @RequestMapping(value = "/complaints/offer/{offerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RentComplaintOffer>> getComplaintOfferByOfferId(@PathVariable("offerId") String offerId) throws URISyntaxException {
        log.debug("REST request to get ComplaintOffer's");
        return new ResponseEntity(complaintOfferService.findAllByOfferId(offerId), HttpStatus.OK);
    }


    /**
     * GET  /complaints : get a initiatorId complaintOffer.
     *
     * @return the ResponseEntity with status 200 (Ok) and with body the new complaintOffer, or with status 400 (Bad Request) if the offer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PreAuthorize("hasAuthority('SEARCH_COMPLAINTS_BY_INITIATOR')")
    @RequestMapping(value = "/complaints/initiator/{initiatorId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RentComplaintOffer>> getComplaintOfferByInitiatorId(@PathVariable("initiatorId") String initiatorId) throws URISyntaxException {
        log.debug("REST request to get ComplaintOffer's");
        List<RentComplaintOffer> complaints = complaintOfferService.findAllByInitiatorId(initiatorId);
        if (complaints.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(complaints, HttpStatus.OK);
    }


    /**
     * GET  /complaints/status/{status} : get a status complaintOffer.
     *
     * @return the ResponseEntity with status 200 (Ok) and with body the new complaintOffer, or with status 400 (Bad Request) if the offer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PreAuthorize("hasAuthority('SEARCH_COMPLAINTS_BY_STATUS')")
    @RequestMapping(value = "/complaints/status/{status}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RentComplaintOffer>> getComplaintOfferByStatus(@PathVariable("status") String status) throws URISyntaxException {
        log.debug("REST request to get ComplaintOffer's");
        final Map<String, String> statuses = Arrays.stream(ComplaintOfferStatus.values()).collect(Collectors.toMap(ComplaintOfferStatus::name, ComplaintOfferStatus::toString));
        if (statuses.containsKey(status)) {
            return new ResponseEntity(complaintOfferService.findAllByStatus(ComplaintOfferStatus.valueOf(status)), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAuthority('SEARCH_COMPLAINTS_BY_FILTER')")
    @RequestMapping(value = "/complaints", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<RentComplaintOffer>> findComplaints(ComplaintFilter filter, Pageable pageable) throws URISyntaxException {
        log.debug("REST request to get ComplaintOffer's");

        Page<RentComplaintOffer> complaints = complaintOfferService.findFilter(filter, pageable);

        return new ResponseEntity(complaints, HttpStatus.OK);
    }

    /**
     * GET  /complaints/is-complaint-available/{offerId}/{userPublicId} : check if exists complaint on this offer by this user .
     *
     * @return the ResponseEntity with status 200 (Ok) and with body the status, or with status 400 (Bad Request) if the offer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PreAuthorize("hasAuthority('READ_COMPLAINT')")
    @RequestMapping(value = "/complaints/is-complaint-available/{offerId}/{userPublicId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> isComplaintAvailable(@PathVariable("offerId") String offerId, @PathVariable("userPublicId") String userPublicId) throws URISyntaxException {
        log.debug("REST request to check if exists complaint on this offerId {} by this userPublicId {}", offerId, userPublicId);
        StringBuilder isComplaintAvailable = new StringBuilder();
        isComplaintAvailable.append("{")
                                        .append("\"status\"")
                                        .append(":")
                                        .append(complaintOfferService.isComplaintAvailableByOfferIdAndUserPublicId(offerId, userPublicId))
                            .append("}");
        return new ResponseEntity(isComplaintAvailable, HttpStatus.OK);
    }
}