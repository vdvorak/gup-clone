package ua.com.gup.server.api.complaint;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.domain.complaint.ComplaintOffer;
import ua.com.gup.domain.complaint.ComplaintOfferStatus;
import ua.com.gup.domain.complaint.ComplaintOfferType;
import ua.com.gup.model.enumeration.UserRole;
import ua.com.gup.server.util.HeaderUtil;
import ua.com.gup.service.complaint.ComplaintOfferService;
import ua.com.gup.util.security.SecurityUtils;

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
public class ComplaintOfferResource {

    private static final String ENTITY_NAME = "complaint";
    private final Logger log = LoggerFactory.getLogger(ComplaintOfferResource.class);

    @Autowired
    private ComplaintOfferService complaintOfferService;

    /**
     * POST  /complaints : Create a new complaintOffer.
     *
     * @param complaintOffer the complaintOffer to create
     * @return the ResponseEntity with status 201 (Created) and with body the new complaintOffer, or with status 400 (Bad Request) if the offer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @CrossOrigin
    @RequestMapping(value = "/complaints",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createComplaintOffer(@Valid @RequestBody ComplaintOffer complaintOffer)
            throws URISyntaxException {
        log.debug("REST request to save new ComplaintOffer : {}", complaintOffer);
        if (!SecurityUtils.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "unauthorized", "Need authorization")).body(null);
        }
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_USER)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_USER'")).body(null);
        }

        complaintOffer.setInitiatorId(SecurityUtils.getLoggedUser().getProfileId());
        complaintOfferService.save(complaintOffer);

        return new ResponseEntity(HttpStatus.CREATED);
    }


    /**
     * PUT  /complaints : Update a staus complaintOffer.
     *
     * @param complaintOffer the complaintOffer to update
     * @return the ResponseEntity with status 200 (Ok) and with body the new complaintOffer, or with status 400 (Bad Request) if the offer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @CrossOrigin
    @RequestMapping(value = "/complaints/{id}/status", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateComplaintOfferStaus(@Valid @RequestBody ComplaintOffer complaintOffer, @PathVariable("id") String id)
            throws URISyntaxException {
        log.debug("REST request to update ComplaintOffer : {}", complaintOffer);
        if (!SecurityUtils.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "unauthorized", "Need authorization")).body(null);
        }
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_USER)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_USER'")).body(null);
        }

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
    @CrossOrigin
    @RequestMapping(value = "/complaints/{id}/type", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateComplaintOfferDescription(@RequestBody ComplaintOfferType type, @PathVariable("id") String id)
            throws URISyntaxException {
        log.debug("REST request to update ComplaintOffer : {}", type);
        if (!SecurityUtils.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "unauthorized", "Need authorization")).body(null);
        }
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_USER)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_USER'")).body(null);
        }

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
    @CrossOrigin
    @RequestMapping(value = "/complaints/{id}/types", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateComplaintOfferTypes(@RequestBody ComplaintOffer complaintOffer, @PathVariable("id") String id)
            throws URISyntaxException {
        log.debug("REST request to update ComplaintOffer : {}", complaintOffer);
        if (!SecurityUtils.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "unauthorized", "Need authorization")).body(null);
        }
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_USER)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_USER'")).body(null);
        }

        complaintOfferService.updateTypes(id, complaintOffer.getTypes());

        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * GET  /types : get types complaintOffer.
     *
     * @return the ResponseEntity with status 200 (Ok) and with body the new complaintOffer
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @CrossOrigin
    @RequestMapping(value = "/complaints/types",
            method = RequestMethod.GET,
            produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> getComplaintOfferTypes() throws URISyntaxException {
        log.info("REST request to get Types");
        if (!SecurityUtils.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "unauthorized", "Need authorization"))
                    .body(null);
        }


        /*final Map<String, String> types =
                Arrays.stream(ComplaintOfferType.values())
                        .collect(Collectors.toMap(ComplaintOfferType::name, ComplaintOfferType::toString));*/
        final List<ComplaintOfferType> types =
                Arrays.stream(ComplaintOfferType.values())
                        .collect(Collectors.toList());

        return new ResponseEntity(types, HttpStatus.OK);
    }

    /**
     * GET  /statuses : get types complaintOffer.
     *
     * @return the ResponseEntity with status 200 (Ok) and with body the new complaintOffer
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @CrossOrigin
    @RequestMapping(value = "/complaints/statuses",
            method = RequestMethod.GET,
            produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> getComplaintOfferStatuses()
            throws URISyntaxException {
        log.debug("REST request to get Statuses");
        if (!SecurityUtils.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "unauthorized", "Need authorization")).body(null);
        }

        final Map<String, String> statuses =
                Arrays.stream(ComplaintOfferStatus.values())
                        .collect(Collectors.toMap(ComplaintOfferStatus::name, ComplaintOfferStatus::toString));
        return new ResponseEntity(statuses, HttpStatus.OK);
    }


    /**
     * GET  /complaints : Get a staus complaintOffer.
     *
     * @param id the complaintOffer to gte
     * @return the ResponseEntity with status 200 (Ok) and with body the new complaintOffer, or with status 400 (Bad Request) if the offer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @CrossOrigin
    @RequestMapping(value = "/complaints/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComplaintOffer> getComplaintOfferById(@PathVariable("id") String id)
            throws URISyntaxException {
        log.debug("REST request to get ComplaintOffer : {}", id);
        if (!SecurityUtils.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "unauthorized", "Need authorization")).body(null);
        }
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_USER)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_USER'")).body(null);
        }

        return new ResponseEntity(complaintOfferService.findOne(id), HttpStatus.OK);
    }


    /**
     * GET  /complaints : get all complaintOffer.
     *
     * @return the ResponseEntity with status 200 (Ok) and with body the new complaintOffer, or with status 400 (Bad Request) if the offer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @CrossOrigin
    @RequestMapping(value = "/complaints", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComplaintOffer>> getComplaintOfferAll()
            throws URISyntaxException {
        log.debug("REST request to get ComplaintOffer's");
        if (!SecurityUtils.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "unauthorized", "Need authorization")).body(null);
        }
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_USER)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_USER'")).body(null);
        }

        return new ResponseEntity(complaintOfferService.findAll(), HttpStatus.OK);
    }


    /**
     * GET  /complaints : get a offerId complaintOffer.
     *
     * @return the ResponseEntity with status 200 (Ok) and with body the new complaintOffer, or with status 400 (Bad Request) if the offer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @CrossOrigin
    @RequestMapping(value = "/complaints/offer/{offerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComplaintOffer>> getComplaintOfferByOfferId(@PathVariable("offerId") String offerId)
            throws URISyntaxException {
        log.debug("REST request to get ComplaintOffer's");
        if (!SecurityUtils.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "unauthorized", "Need authorization")).body(null);
        }
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_USER)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_USER'")).body(null);
        }

        return new ResponseEntity(complaintOfferService.findAllByOfferId(offerId), HttpStatus.OK);
    }


    /**
     * GET  /complaints : get a initiatorId complaintOffer.
     *
     * @return the ResponseEntity with status 200 (Ok) and with body the new complaintOffer, or with status 400 (Bad Request) if the offer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @CrossOrigin
    @RequestMapping(value = "/complaints/initiator/{initiatorId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComplaintOffer>> getComplaintOfferByInitiatorId(@PathVariable("initiatorId") String initiatorId)
            throws URISyntaxException {
        log.debug("REST request to get ComplaintOffer's");
        if (!SecurityUtils.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "unauthorized", "Need authorization")).body(null);
        }
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_USER)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_USER'")).body(null);
        }

//        return new ResponseEntity(complaintOfferService.findAllByInitiatorId(initiatorId), HttpStatus.OK);
        List<ComplaintOffer> complaints = complaintOfferService.findAllByInitiatorId(initiatorId);
        if (complaints.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(complaints, HttpStatus.OK);
    }


    /**
     * GET  /complaints : get a status complaintOffer.
     *
     * @return the ResponseEntity with status 200 (Ok) and with body the new complaintOffer, or with status 400 (Bad Request) if the offer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @CrossOrigin
    @RequestMapping(value = "/complaints/status/{status}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<ComplaintOffer>> getComplaintOfferByStatus(@PathVariable("status") ComplaintOfferStatus status)
    public ResponseEntity<List<ComplaintOffer>> getComplaintOfferByStatus(@PathVariable("status") String status)
            throws URISyntaxException {
        log.debug("REST request to get ComplaintOffer's");
        if (!SecurityUtils.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "unauthorized", "Need authorization")).body(null);
        }
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_USER)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_USER'")).body(null);
        }

        final Map<String, String> statuses =
                Arrays.stream(ComplaintOfferStatus.values())
                        .collect(Collectors.toMap(ComplaintOfferStatus::name, ComplaintOfferStatus::toString));
        if (statuses.containsKey(status)) {
            return new ResponseEntity(complaintOfferService.findAllByStatus(ComplaintOfferStatus.valueOf(status)), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
