package ua.com.itproekt.gup.api.rest.tender;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.com.itproekt.gup.model.activityfeed.Event;
import ua.com.itproekt.gup.model.activityfeed.EventType;
import ua.com.itproekt.gup.model.nace.NACE;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.UserType;
import ua.com.itproekt.gup.model.tender.*;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.filestorage.StorageService;
import ua.com.itproekt.gup.service.nace.NaceService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.tender.TenderService;
import ua.com.itproekt.gup.util.CreatedObjResp;
import ua.com.itproekt.gup.util.EntityPage;
import ua.com.itproekt.gup.util.SecurityOperations;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;


@RestController
@RequestMapping("/api/rest/tenderService")
public class TenderRestController {
    private static final String SERVICE_NAME = "tenderService";
    private Logger log = Logger.getLogger(SERVICE_NAME);
    private static final String LOGGED_TITLE = "- TenderRestController - api/rest/tenderService/";

    @Autowired
    TenderService tenderService;

    @Autowired
    ProfilesService profileService;

    @Autowired
    NaceService naceService;

    @Autowired
    StorageService storageService;

    @Autowired
    ActivityFeedService activityFeedService;
    //------------------------------------------ Read -----------------------------------------------------------------

    @RequestMapping(value = "/tender/read/id/{id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tender> getTenderById(@PathVariable("id") String id, HttpServletRequest req) {

        Tender tender = tenderService.findById(id);

        if (tender == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Profile currentUser = getCurrentUser();

        if (currentUser == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        TenderType tenderType = tender.getType();
        UserType userType = currentUser.getContact().getType();
        boolean isUserMemberOfTender = isUserMemberOfTender(tender, currentUser);
        boolean isUserAuthorOrModerator = isUserModeratorOrAuthor(currentUser, tender.getAuthorId());

        if (tenderType == TenderType.OPEN && userType == UserType.INDIVIDUAL && !isUserAuthorOrModerator && !isUserMemberOfTender) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        if (tenderType == TenderType.OPEN || (tenderType == TenderType.CLOSE && isUserMemberOfTender) || isUserAuthorOrModerator) {

            makeTenderViewedBeforeResponse(tender, id, req);
            return new ResponseEntity<>(tender, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/tender/read/all/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityPage<Tender>> listOfAllTender(@RequestBody TenderFilterOptions tenderFilterOptions) {
        if (tenderFilterOptions.isJustUsersNace()) {
            tenderFilterOptions.setNaceIdIn(getCurrentUserNaceId());
        }

        Profile profile;
        profile = getCurrentUser();

        if (profile == null) {
            tenderFilterOptions.setType(TenderType.OPEN);
            profile = new Profile();
        }

        EntityPage<Tender> tenders = tenderService.findWihOptions(tenderFilterOptions, profile);

        if (!tenders.getEntities().isEmpty()) {
            tenders.getEntities().stream().forEach(t -> {
                t = tenderService.setVision(t, getCurrentUser());
                tenderService.completeMembers(t);
            });
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tenders, HttpStatus.OK);
    }

    @RequestMapping(value = "/tender/read/allmynace/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityPage<Tender>> listOfAllTenderUsersNace(@RequestBody TenderFilterOptions tenderFilterOptions) {
        tenderFilterOptions.setNaceIdIn(getCurrentUserNaceId());
        EntityPage<Tender> tender = tenderService.findWihOptions(tenderFilterOptions, getCurrentUser());
        if (tender.getEntities().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tender, HttpStatus.OK);
    }

    //------------------------------------------ Create -----------------------------------------------------------------
    @RequestMapping(value = "/tender/create/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatedObjResp> createTender(@RequestBody Tender tender) {

        if (!SecurityOperations.isUserLoggedIn()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        tender.setAuthorId(SecurityOperations.getLoggedUserId());

        tender.setHideContact(false);
        tenderService.createTender(tender);

        if (tender.getType() == TenderType.CLOSE) {
            sendActivityFeedToMembers(tender);
        }

        CreatedObjResp createdObjResp = new CreatedObjResp(tender.getId());
        return new ResponseEntity<>(createdObjResp, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/tender/id/{id}/propose/create/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tender> addPropose(@PathVariable("id") Tender tender, @RequestBody Propose propose) {

        propose.setAuthorId(SecurityOperations.getLoggedUserId());
        Tender newTender = Tender.getEmpty();
        newTender.setId(tender.getId());
        tender.getProposes().add(propose);
        tender.getMembers().add(new Member(propose.getAuthorId()));
        newTender.setMembers(tender.getMembers());
        newTender.setProposes(tender.getProposes());

        activityFeedService.createEvent(new Event(tender.getAuthorId(), EventType.NEW_PROPOSE_IN_YOUR_TENDER, tender.getId(), propose.getAuthorId()));

        tenderService.updateTender(newTender);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/tender/id/{id}/member/create/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addMember(@PathVariable("id") Tender tender, @RequestBody Member member) {

        Tender newTender = new Tender();
        newTender.setId(tender.getId());
        if (member.getId() == null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        // check type of user. Only LEGAL_ENTITY or ENTREPRENEUR can became an member;
        UserType userType = profileService.findWholeProfileById(member.getId()).getContact().getType();
        if (userType == null || userType == UserType.INDIVIDUAL) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if (tender.getMembers().add(member) && tender.getType() == TenderType.CLOSE) {
            activityFeedService.createEvent(new Event(member.getId(), EventType.YOU_HAVE_BEEN_ADDED_TO_CLOSE_TENDER, tender.getId(), tender.getAuthorId()));
        }
        newTender.setMembers(tender.getMembers());

        tenderService.updateTender(newTender);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
    //------------------------------------------ Update -----------------------------------------------------------------

    @RequestMapping(value = "/tender/id/{id}/update/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tender> updateTender(@RequestBody Tender newTender, @PathVariable String id, UriComponentsBuilder ucBuilder) {

        if (newTender.getId() == null) {
            newTender.setId(id);
        } else if (!tenderService.tenderExists(newTender.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }



        Tender tender = tenderService.findById(id);
//        Map<String, String> files = tender.getUploadFilesIds();
//        if (!newTender.getUploadFilesIds().isEmpty()) {
//            newTender.getUploadFilesIds().putAll(files);
//        }
        if (newTender.getWinnerId() != null && newTender.getWinnerId().length() > 0) {
            activityFeedService.createEvent(new Event(newTender.getWinnerId(), EventType.YOU_WON_IN_TENDER, tender.getId(), null, tender.getAuthorId()));
            tender.setEnd(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
        }


        if (newTender.getType() == TenderType.CLOSE) {
            if (!tender.getMembers().isEmpty() && !newTender.getMembers().isEmpty()) {
                sendActivityFeedToMembersAfterUpdate(tender, newTender);
            } else {
                if (!newTender.getMembers().isEmpty()) {
                    sendActivityFeedToMembers(newTender);
                }
            }
        }


        //ToDo Delete if new method will work
//        if(tender.getType() == TenderType.CLOSE && !newTender.getMembers().isEmpty()){
//            newTender.getMembers().stream().forEach(m -> activityFeedService.createEvent(new Event(m.getId(), EventType.YOU_HAVE_BEEN_ADDED_TO_CLOSE_TENDER, tender.getId(), null, tender.getAuthorId())));
//        }


        tenderService.updateTender(newTender);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/tender/read/id/{id}").buildAndExpand(newTender.getId()).toUri());
        return new ResponseEntity<>(newTender, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/tender/chooseWinner",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tender> winnerChoose(@RequestBody Tender tender) {
        String id = tenderService.findById(tender.getId()).getAuthorId();
        if (SecurityOperations.getLoggedUserId() == null || !SecurityOperations.getLoggedUserId().equals(id)) {
            return new ResponseEntity<Tender>(HttpStatus.FORBIDDEN);
        }
        tender.setEnd(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
        activityFeedService.createEvent(new Event(tender.getWinnerId(), EventType.YOU_WON_IN_TENDER, tender.getId(), tender.getAuthorId()));
        return new ResponseEntity<>(tenderService.updateTender(tender), HttpStatus.OK);
    }

    //------------------------------------------ Delete -----------------------------------------------------------------

    @RequestMapping(value = "/tender/{id}/delete",
            method = RequestMethod.POST)
    public ResponseEntity<Tender> deleteTender(@PathVariable("id") String id) {

        if (!tenderService.tenderExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        tenderService.deleteTenderById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/tender/{id}/file/{fileId}/delete",
            method = RequestMethod.POST)
    public ResponseEntity<Tender> deleteFile(@PathVariable("id") Tender tender, @PathVariable("fileId") String fileId) {

        if (tender.getUploadFilesIds().containsKey(fileId)) {
            tender.getUploadFilesIds().remove(fileId);
            storageService.delete(SERVICE_NAME, fileId);
            Tender newTender = new Tender();
            newTender.setId(tender.getId());
            newTender.setUploadFilesIds(tender.getUploadFilesIds());
            tenderService.updateTender(newTender);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @RequestMapping(value = "/tender/userislegal/{email}",
            method = RequestMethod.POST)
    public Boolean isLegal(@PathVariable("email") String email) {
        // check type of user. Only LEGAL_ENTITY or ENTREPRENEUR can became an member;
        UserType userType = profileService.findProfileByEmail(email).getContact().getType();
        if (userType == null || userType == UserType.INDIVIDUAL) {
            return false;
        }
        return true;
    }


    // check type of user. Only LEGAL_ENTITY or ENTREPRENEUR can became an member, but author of tender also can see proposes;
    @RequestMapping(value = "/tender/{id}/user-check",
            method = RequestMethod.POST)
    public Boolean canBeMember(@PathVariable("id") String id) {

        Tender tender = tenderService.findById(id);

        Profile profile = profileService.findById(SecurityOperations.getLoggedUserId());


        if (profile == null || profile.getContact() == null) {
            return false;
        }

        if (profile.getId().equals(tender.getAuthorId())) {
            return true;
        }

        UserType userType = profile.getContact().getType();
        if (userType == null || userType == UserType.INDIVIDUAL) {
            return false;
        }
        // TODO должен быть проверенным
//        if(!p.isConfirmModerator()){
//            return false;
//        }
        // TODO сравнивать кведы
//        if (p.getContact().getNaceId() == null || p.getContact().getNaceId().isEmpty()) {
//            return false;
//        }
//        Tender t = tenderService.findById(id);
//        for(String s : t.getNaceIds()){
//            if (p.getContact().getNaceId().contains(s)){
//                return true;
//            }
//        }
//        return false;
        return true;
    }


    private String getCurrentUserId() {
        Profile user = getCurrentUser();
        if (user == null || user.getId() == null) return null;
        return user.getId();
    }

    private Profile getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) return null;
        Authentication authentication = context.getAuthentication();
        if (authentication == null) return null;
        String email = authentication.getName(); //get logged in username
        if (email == null) return null;
        return profileService.findProfileByEmail(email);
    }

    @InitBinder
    protected void tenderBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Tender.class,
                new PropertyEditorSupport() {
                    @Override
                    public void setAsText(String id) {
                        Tender tender = null;
                        if (id != null && !id.trim().isEmpty()) {
                            tender = tenderService.findById(id);
                        } else {
                            log.log(Level.ERROR, "CAN'T FOUND TENDER WITH id = " + id);
                        }
                        setValue(tender);
                    }
                }
        );
    }

    private List<NACE> getCurrentUserNace() {
        Profile user = getCurrentUser();
        if (user == null || user.getContact() == null || user.getContact().getNaceId() == null) return null;
        List<String> nacesId = user.getContact().getNaceId();
        List<NACE> naces = new ArrayList<>();
        for (String id : nacesId) {
            NACE nace = naceService.findById(id);
            if (nace != null) {
                naces.add(naceService.findById(id));
            }
        }
        return naces;
    }

    private List<String> getCurrentUserNaceId() {
        Profile user = getCurrentUser();
        if (user == null | user.getContact() == null) return null;
        return user.getContact().getNaceId();
    }

    private void sendActivityFeedToMembers(Tender tender) {
        for (Member m : tender.getMembers()) {
            activityFeedService.createEvent(new Event(m.getId(), EventType.YOU_HAVE_BEEN_ADDED_TO_CLOSE_TENDER, tender.getId(), tender.getAuthorId()));
        }
    }

    private void sendActivityFeedToMembersAfterUpdate(Tender oldTender, Tender newTender) {
        Set<String> oldMembers = null, newMembers = null, resultList = null;
        for (Member m : oldTender.getMembers()) {
            oldMembers.add(m.getId());
        }
        for (Member m : newTender.getMembers()) {
            newMembers.add(m.getId());
        }

        newMembers.removeAll(oldMembers);

        for (String newMemberId : newMembers) {
            activityFeedService.createEvent(new Event(newMemberId, EventType.YOU_HAVE_BEEN_ADDED_TO_CLOSE_TENDER, newTender.getId(), newTender.getAuthorId()));
        }
    }

    private boolean isUserMemberOfTender(Tender tender, Profile profile) {
        String userId = profile.getId();

        Set<Member> members = tender.getMembers();

        for (Member member : members) {
            if (userId.equals(member.getId())) return true;
        }
        return false;
    }

    private void makeTenderViewedBeforeResponse(Tender tender, String id, HttpServletRequest req) {
        // incrementing Visited field
        HashSet<String> visit = (HashSet<String>) req.getSession().getAttribute("tenderVisit");
        if (visit == null) visit = new HashSet<>();
        if (!visit.contains(id)) {
            Tender tenderForCountVisited = Tender.getEmpty();
            tenderForCountVisited.setId(tender.getId());

            tenderForCountVisited.setVisited(tender.getVisited() + 1);
            tenderService.updateTender(tenderForCountVisited);
            visit.add(id);
            req.getSession().setAttribute("tenderVisit", visit);

            //update field visited in current tender
            tender.setVisited(tenderForCountVisited.getVisited());
        }

        //make propose visible according to hidden settings
        tender = tenderService.setVision(tender, getCurrentUser());

        //complete Member name and picId
        tenderService.completeMembers(tender);
    }

    private boolean isUserModeratorOrAuthor(Profile user, String tenderAuthorId) {
        return user.getId().equals(tenderAuthorId) || profileService.isUserModerator(user);
    }


}