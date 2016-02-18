package ua.com.itproekt.gup.api.rest.projectsAndInvestments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.model.profiles.UserRole;
import ua.com.itproekt.gup.model.projectsAndInvestments.project.ModerationStatus;
import ua.com.itproekt.gup.model.projectsAndInvestments.project.Project;
import ua.com.itproekt.gup.model.projectsAndInvestments.project.ProjectFilterOptions;
import ua.com.itproekt.gup.service.projectsAndInvestments.project.ProjectService;
import ua.com.itproekt.gup.util.CreatedObjResp;
import ua.com.itproekt.gup.util.EntityPage;
import ua.com.itproekt.gup.util.SecurityOperations;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/rest/projectsAndInvestmentsService")
public class ProjectsRestController {

    @Autowired
    ProjectService projectService;

    //------------------------------------------ Read -----------------------------------------------------------------

    @RequestMapping(value = "/project/id/{projectId}/read", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Project> getProjectById(@PathVariable String projectId, HttpServletRequest request) {
        Project project = projectService.findProjectAndIncViews(projectId);
        if (project == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (project.getModerationStatus().equals(ModerationStatus.COMPLETE) ||
            project.getAuthorId().equals(SecurityOperations.getLoggedUserId()) ||
            request.isUserInRole(UserRole.ROLE_ADMIN.toString())) {

            return new ResponseEntity<>(project, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/project/read/all", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityPage<Project>> listOfAllProjects(@RequestBody ProjectFilterOptions projectFO,
                                                                 HttpServletRequest request) {
        if(!request.isUserInRole(UserRole.ROLE_ADMIN.toString())){
            projectFO.setSimpleUserRestrictions(SecurityOperations.getLoggedUserId());
        }

        EntityPage<Project> projectPages = projectService.findProjectsWihOptions(projectFO);
        if(projectPages.getEntities().isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(projectPages, HttpStatus.OK);
    }

    //------------------------------------------ Create -----------------------------------------------------------------

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/project/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatedObjResp> createProject(@Valid @RequestBody Project project) {

        String userId = SecurityOperations.getLoggedUserId();
        project.setAuthorId(userId);
        projectService.create(project);

        CreatedObjResp createdObjResp = new CreatedObjResp(project.getId());
        return new ResponseEntity<>(createdObjResp, HttpStatus.CREATED);
    }

    //------------------------------------------ Update -----------------------------------------------------------------

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/project/edit", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> editProject(@Valid @RequestBody Project project, HttpServletRequest request) {

        if (project.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (!projectService.projectExists(project.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String userId = SecurityOperations.getLoggedUserId();
        if (projectService.findById(project.getId()).getAuthorId().equals(userId) || request.isUserInRole(UserRole.ROLE_ADMIN.toString())) {
            projectService.edit(project);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    //------------------------------------------ Delete -----------------------------------------------------------------

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/project/id/{projectId}/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteProject(@PathVariable String projectId, HttpServletRequest request) {

        if (!projectService.projectExists(projectId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Project project = projectService.findById(projectId);
        if(project.getAuthorId().equals(SecurityOperations.getLoggedUserId()) ||
                request.isUserInRole(UserRole.ROLE_ADMIN.toString())){

            projectService.delete(projectId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
