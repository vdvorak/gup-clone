package ua.com.itproekt.gup.api.rest.tender;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.com.itproekt.gup.model.activityfeed.Event;
import ua.com.itproekt.gup.model.activityfeed.EventType;
import ua.com.itproekt.gup.model.nace.DepartmentOrNace;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.UserType;
import ua.com.itproekt.gup.model.tender.doer.Doer;
import ua.com.itproekt.gup.model.tender.doer.DoerClient;
import ua.com.itproekt.gup.model.tender.doer.DoerFilterOptions;
import ua.com.itproekt.gup.model.tender.doer.Recall;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.nace.NaceService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.tender.doer.DoerService;
import ua.com.itproekt.gup.util.EntityPage;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/rest/doerService")
public class DoerRestController {
    private Logger log = Logger.getLogger("doerService");
    private static final String LOGGED_TITLE = "- DoerRestController - api/rest/doerService/";

    @Autowired
    DoerService doerService;

    @Autowired
    ProfilesService profileService;

    @Autowired
    NaceService naceService;

    @Autowired
    ActivityFeedService activityFeedService;

    //------------------------------------------ Read -----------------------------------------------------------------

    @RequestMapping(value = "/doer/read/id/{id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Doer> getDoerById(@PathVariable("id") String id, HttpServletRequest req) {
        Doer doer = doerService.findById(id);
        if (doer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // incrementing Visited field
        ArrayList<String> visit = (ArrayList<String>) req.getSession().getAttribute("doerVisit");
        if(visit == null) visit = new ArrayList<>();
        if(!visit.contains(id)) {
            visit.add(id);
            Doer doerForCountVisited = new Doer();
            doerForCountVisited.setId(doer.getId());

            doerForCountVisited.setCountVisit(doer.getCountVisit() + 1);
            doerService.updateDoer(doerForCountVisited);
            req.getSession().setAttribute("tenderVisit", visit);

            //update field visited in current doer
            doer.setCountVisit(doerForCountVisited.getCountVisit());
        }

        return new ResponseEntity<>(doer, HttpStatus.OK);
    }

    @RequestMapping(value = "/doer/read/all/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityPage<Doer>> listOfAllDoer(@RequestBody DoerFilterOptions doerFilterOptions) {
        EntityPage<Doer> doers = doerService.findWihOptions(doerFilterOptions);
        if (doers.getEntities().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(doers, HttpStatus.OK);
    }

    //------------------------------------------ Create -----------------------------------------------------------------
    @RequestMapping(value = "/doer/create/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Doer> createDoer(@RequestBody Doer doer, UriComponentsBuilder ucBuilder) {
        // check type of user. Only LEGAL_ENTITY or ENTREPRENEUR can became an doer;
        UserType userType = profileService.readById(doer.getAuthorId()).getContact().getType();
        if(userType == null || userType == UserType.INDIVIDUAL){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        doerService.createDoer(doer);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/doer/read/id/{id}").buildAndExpand(doer.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/doer/id/{id}/recall/create/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Doer> addRecall(@PathVariable("id") Doer doer, @RequestBody Recall recall) {

        Doer newDoer = new Doer();
        newDoer.setId(doer.getId());
        doer.getRecalls().add(recall);
        newDoer.setRecalls(doer.getRecalls());
        int modifier = 0;
        if(recall.getMark()!=null) {
            modifier = recall.getMark() == Recall.Mark.LIKE ? 1 : recall.getMark() == Recall.Mark.DISLIKE ? -1 : 0;
        }
        newDoer.setRecallCount(doer.getRecallCount() + modifier);

        doerService.updateDoer(newDoer);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/doer/id/{id}/client/create/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Doer> addClient(@PathVariable("id") Doer doer, @RequestParam String clientId) {
        // handling situation when doer add client
        if (getCurrentUserId().equals(doer.getAuthorId())) {
            if (profileService.readById(clientId) == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            // check current client isn't already in list
            if (doer.getClients().stream().map(DoerClient::getId).collect(Collectors.toList()).contains(clientId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            DoerClient client = new DoerClient();
            client.setId(clientId);
            client.setDoerConfirm(true);
            doer.getClients().add(client);
            //create an notice(activityFeed) for user who was just add to list of clients
            activityFeedService.createEvent(new Event(clientId, EventType.USER_ADD_TO_DOER_CLIENT_LIST, doer.getId(), doer.getAuthorId()));

        // handling situation when client adds to doer clientsList
        } else if(clientId == null || getCurrentUserId().equals(clientId)){
            DoerClient client = new DoerClient();
            client.setId(clientId);
            client.setClientConfirm(true);
            doer.getClients().add(client);
            //create an notice(activityFeed)for doer about new client was added.
            activityFeedService.createEvent(new Event(doer.getAuthorId(), EventType.NEW_CLIENT_WANT_CONFIRM, doer.getId(), clientId));

        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        doerService.updateDoer(doer);
        return new ResponseEntity<>(doer, HttpStatus.OK);
    }

    //------------------------------------------ Update -----------------------------------------------------------------

    @RequestMapping(value = "/doer/id/{id}/clientConfirm/{clientId}/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Doer> clientConfirm(@PathVariable("id") Doer doer, @PathVariable String clientId) {

        // check if user has access
        if(!clientId.equals(getCurrentUserId())){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        // check if current client in doers clients list
        if(!doer.getClients().stream().map(DoerClient::getId).collect(Collectors.toList()).contains(clientId)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        for(DoerClient client:doer.getClients()){
            if(client.getId().equals(clientId) && client.isDoerConfirm()){
                client.setClientConfirm(true);
                doerService.updateDoer(doer);
                return new ResponseEntity<>(doer, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "/doer/id/{id}/doerConfirm/{clientId}/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Doer> doerConfirm(@PathVariable("id") Doer doer, @PathVariable String clientId) {

        // check if user has access
        if(!clientId.equals(doer.getAuthorId())){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        // check if current client in doers clients list
        if(!doer.getClients().stream().map(DoerClient::getId).collect(Collectors.toList()).contains(clientId)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        for(DoerClient client:doer.getClients()){
            if(client.getId().equals(clientId) && client.isClientConfirm()){
                client.setDoerConfirm(true);
                doerService.updateDoer(doer);
                return new ResponseEntity<>(doer, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "/doer/id/{id}/update/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Doer> updateDoer(@RequestBody Doer newDoer, UriComponentsBuilder ucBuilder) {

        if (newDoer.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (!doerService.doerExists(newDoer.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/doer/read/id/{id}").buildAndExpand(newDoer.getId()).toUri());
        newDoer.setDateOfUpdate(System.currentTimeMillis());
        doerService.updateDoer(newDoer);
        return new ResponseEntity<>(doerService.findById(newDoer.getId()), headers, HttpStatus.OK);
    }

    //------------------------------------------ Delete -----------------------------------------------------------------

    @RequestMapping(value = "/doer/delete/{id}",
            method = RequestMethod.POST)
    public ResponseEntity<Doer> deleteDoer(@PathVariable("id") String id) {

        if (!doerService.doerExists(id)) {
            return new ResponseEntity<Doer>(HttpStatus.NOT_FOUND);
        }

        doerService.deleteDoerById(id);
        return new ResponseEntity<Doer>(HttpStatus.NO_CONTENT);
    }


    private String getCurrentUserId(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName(); //get logged in username
        return profileService.findProfileByEmail(email).getId();
    }

    private Profile getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName(); //get logged in username
        return profileService.findProfileByEmail(email);
    }

    //------------------------------------------ Other -----------------------------------------------------------------

    @RequestMapping(value = "/doer/id/{id}/recall/count/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Doer> recallCount(@PathVariable("id") Doer doer) {

        Doer newDoer = new Doer();
        newDoer.setId(doer.getId());
        newDoer.setRecallCount(doerService.countRecallSum(doer));

        doerService.updateDoer(newDoer);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    //------------------------------------------ Helper methods -----------------------------------------------------------------

    @InitBinder
    protected void DoerBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Doer.class,
                new PropertyEditorSupport() {
                    @Override
                    public void setAsText(String id) {
                        Doer doer = null;
                        if (id != null && !id.trim().isEmpty()) {
                            doer = doerService.findById(id);
                        }else {
                            log.log(Level.ERROR, "CAN'T FOUND Doer WITH id = " + id);
                        }
                        setValue(doer);
                    }
                }
        );
    }

    private List<DepartmentOrNace> getCurrentUserNace(){
        Profile user = getCurrentUser();
        List<String> nacesId = user.getContact().getNaceId();
        List<DepartmentOrNace> naces = new ArrayList<>();
        for(String id:nacesId){
            naces.add(naceService.findById(id));
        }
        return naces;
    }

    private List<String> getCurrentUserNaceId() {
        Profile user = getCurrentUser();
        return  user.getContact().getNaceId();
    }
}
