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
import ua.com.itproekt.gup.model.nace.DepartmentOrNace;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.UserType;
import ua.com.itproekt.gup.model.tender.*;
import ua.com.itproekt.gup.service.filestorage.StorageService;
import ua.com.itproekt.gup.service.nace.NaceService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.tender.TenderService;
import ua.com.itproekt.gup.util.EntityPage;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


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

    //------------------------------------------ Read -----------------------------------------------------------------

    @RequestMapping(value = "/tender/read/id/{id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tender> getTenderById(@PathVariable("id") String id, HttpServletRequest req) {
        Tender tender = tenderService.findById(id);
        if (tender == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //make propose visible according to hidden settings
        if (!tender.getAuthorId().equals(getCurrentUserId())) {
            if (tender.isHidePropose()) {
                tender.setProposes(null);
            } else {
                tender.getProposes().stream().filter(p -> p.getHidden()).forEach(p -> {
                    tender.getProposes().remove(p);
                });
            }
        }


        // incrementing Visited field
        ArrayList<String> visit = (ArrayList<String>) req.getSession().getAttribute("tenderVisit");
        if (visit == null) visit = new ArrayList<>();
        if (!visit.contains(id)) {
            Tender tenderForCountVisited = new Tender();
            tenderForCountVisited.setId(tender.getId());

            tenderForCountVisited.setVisited(tender.getVisited() + 1);
            tenderService.updateTender(tenderForCountVisited);
            visit.add(id);
            req.getSession().setAttribute("tenderVisit", visit);

            //update field visited in current tender
            tender.setVisited(tenderForCountVisited.getVisited());
        }

        return new ResponseEntity<>(tender, HttpStatus.OK);
    }

    @RequestMapping(value = "/tender/read/all/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityPage<Tender>> listOfAllTender(@RequestBody TenderFilterOptions tenderFilterOptions) {
        if(tenderFilterOptions.isJustUsersNace()){
            tenderFilterOptions.setNaceIdIn(getCurrentUserNaceId());
        }

        Profile profile;
        profile = getCurrentUser();

        if(profile == null){
            tenderFilterOptions.setType(TenderType.OPEN);
            profile = new Profile();
        }

        EntityPage<Tender> tenders = tenderService.findWihOptions(tenderFilterOptions, profile);

        // Propose can see just users with type UserType.ENTREPRENEUR or UserType.LEGAL_ENTITY
        if (getCurrentUser() != null && getCurrentUser().getContact() != null && getCurrentUser().getContact().getType() != null) {
            System.out.println("in IF");
            UserType userType = getCurrentUser().getContact().getType();
            if (userType != UserType.ENTREPRENEUR || userType != UserType.LEGAL_ENTITY) {
                tenders.getEntities().stream().forEach(t -> t.setProposes(null));
            } else {
                // hide all propose in tenders which create not by current logged user
                // and have settings hidePropose = true assigned by tender author
                tenders.getEntities().stream().filter(t -> !t.getAuthorId().equals(getCurrentUserId()) && t.isHidePropose())
                        .forEach(t -> t.setProposes(null));

                // hide all propose in tenders which create not by current logged user
                // and have settings hidePropose = true assigned by propose creator
                tenders.getEntities().stream().forEach(t -> t.getProposes().stream().filter(p -> p.getHidden()).forEach(p -> {
                    t.getProposes().remove(p);
                }));
            }
        }

        if (tenders.getEntities().isEmpty()) {
            System.out.println("isEmpty");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tenders, HttpStatus.OK);
    }

    @RequestMapping(value = "/tender/read/allmynace/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityPage<Tender>> listOfAllTenderUsersNace(@RequestBody TenderFilterOptions tenderFilterOptions) {
        tenderFilterOptions.setNaceIdIn(getCurrentUserNaceId());
        System.out.println("getCurrentUserNaceId() = " + getCurrentUserNaceId());
        System.out.println("tenderFilterOptions.getNaceIdIn() = " + tenderFilterOptions.getNaceIdIn());
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
    public ResponseEntity<Tender> createTender(@RequestBody Tender tender, UriComponentsBuilder ucBuilder) {

        tenderService.createTender(tender);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/tender/read/id/{id}").buildAndExpand(tender.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/tender/id/{id}/propose/create/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tender> addPropose(@PathVariable("id") Tender tender, @RequestBody Propose propose) {

        Tender newTender = new Tender();
        newTender.setId(tender.getId());
        tender.getProposes().add(propose);
        newTender.setProposes(tender.getProposes());

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
        UserType userType = profileService.findById(member.getId()).getContact().getType();
        if (userType == null || userType == UserType.INDIVIDUAL) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if (!tender.getMembers().contains(member)) {
            tender.getMembers().add(member);
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
        Set files = tender.getUploadFilesIds();
        if (!newTender.getUploadFilesIds().isEmpty()) {
            newTender.getUploadFilesIds().addAll(files);
        }
        tenderService.updateTender(newTender);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/tender/read/id/{id}").buildAndExpand(newTender.getId()).toUri());
        return new ResponseEntity<>(newTender, headers, HttpStatus.OK);
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

        if (tender.getUploadFilesIds().contains(fileId)) {
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

    private String getCurrentUserId() {
        Profile user = getCurrentUser();
        if(user == null || user.getId() == null) return null;
        return user.getId();
    }

    private Profile getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        if(context == null) return null;
        Authentication authentication = context.getAuthentication();
        if(authentication == null) return null;
        String email = authentication.getName(); //get logged in username
        if(email == null) return null;
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

    private List<DepartmentOrNace> getCurrentUserNace() {
        Profile user = getCurrentUser();
        List<String> nacesId = user.getContact().getNaceId();
        List<DepartmentOrNace> naces = new ArrayList<>();
        for (String id : nacesId) {
            naces.add(naceService.findById(id));
        }
        return naces;
    }

    private List<String> getCurrentUserNaceId() {
        Profile user = getCurrentUser();
        return user.getContact().getNaceId();
    }
}