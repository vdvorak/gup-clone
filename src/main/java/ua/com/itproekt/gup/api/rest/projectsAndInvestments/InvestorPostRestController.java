package ua.com.itproekt.gup.api.rest.projectsAndInvestments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.model.projectsAndInvestments.investment.ApplicationForInvestment;
import ua.com.itproekt.gup.model.projectsAndInvestments.investment.InvestorPost;
import ua.com.itproekt.gup.model.projectsAndInvestments.investment.InvestorPostFilterOptions;
import ua.com.itproekt.gup.service.projectsAndInvestments.investment.InvestorService;
import ua.com.itproekt.gup.service.projectsAndInvestments.project.ProjectService;
import ua.com.itproekt.gup.util.CreatedObjResp;
import ua.com.itproekt.gup.util.EntityPage;
import ua.com.itproekt.gup.util.SecurityOperations;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/rest/projectsAndInvestmentsService")
public class InvestorPostRestController {

    @Autowired
    InvestorService investorService;

    @Autowired
    ProjectService projectService;

    //------------------------------------------ Read -----------------------------------------------------------------

    @RequestMapping(value = "/investorPost/id/{investorPostId}/read", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InvestorPost> getInvestorById(@PathVariable String investorPostId) {
        InvestorPost investorPost = investorService.findById(investorPostId);
        if (investorPost == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(investorPost, HttpStatus.OK);
    }

    @RequestMapping(value = "/investorPost/read/all", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityPage<InvestorPost>> listOfAllInvestors(@RequestBody InvestorPostFilterOptions investorFO) {
        EntityPage<InvestorPost> investorPages = investorService.findInvestorPostsWihOptions(investorFO);
        if(investorPages.getEntities().isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(investorPages, HttpStatus.OK);
    }

    //------------------------------------------ Create -----------------------------------------------------------------

    @RequestMapping(value = "/investorPost/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatedObjResp> createInvestorPost(@Valid @RequestBody InvestorPost investorPost) {

        String userId = SecurityOperations.getLoggedUserId();

        investorPost.setuId(userId);
        investorService.create(investorPost);

        CreatedObjResp createdObjResp = new CreatedObjResp(investorPost.getId());
        return new ResponseEntity<>(createdObjResp, HttpStatus.CREATED);
    }

    //------------------------------------------ Update -----------------------------------------------------------------

    @RequestMapping(value = "/investorPost/edit", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InvestorPost> editInvestorPost(@Valid @RequestBody InvestorPost investorPost ) {

        if (investorPost.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (!investorService.investorPostExist(investorPost.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        InvestorPost newInvestorPost = investorService.edit(investorPost);

        return new ResponseEntity<>(newInvestorPost, HttpStatus.OK);
    }

    @RequestMapping(value = "/investorPost/id/{investorPostId}/apply", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> investById(@PathVariable String investorPostId,
                                           @RequestBody ApplicationForInvestment applicationForInvestment) {
        if (!investorService.investorPostExist(investorPostId) || !projectService.projectExists(applicationForInvestment.getProjectId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String userId = SecurityOperations.getLoggedUserId();
        applicationForInvestment.setuId(userId);

        // ***
        // ***  проверка на владельца проекта
        // ***
        investorService.addApplication(investorPostId, applicationForInvestment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //------------------------------------------ Delete -----------------------------------------------------------------

    @RequestMapping(value = "/investorPost/id/{investorPostId}/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteInvestorPost(@PathVariable String investorPostId) {
        if (!investorService.investorPostExist(investorPostId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        investorService.delete(investorPostId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
