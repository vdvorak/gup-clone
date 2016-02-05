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
import ua.com.itproekt.gup.util.CreatedObjResponse;
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
    public ResponseEntity<Project> getProjectById(@PathVariable String projectId) {
        Project project = projectService.findProjectAndIncViews(projectId);
        if (project == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @RequestMapping(value = "/project/read/all", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityPage<Project>> listOfAllInvestors(@RequestBody ProjectFilterOptions projectFO,
                                                                  HttpServletRequest request) {
        if(!request.isUserInRole(UserRole.ROLE_ADMIN.toString())){
            projectFO.setModerationStatus(ModerationStatus.COMPLETE);
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
    public ResponseEntity<CreatedObjResponse> createProject(@Valid @RequestBody Project project) {

        String userId = SecurityOperations.getLoggedUserId();
        project.setAuthorId(userId);
        projectService.create(project);

        CreatedObjResponse createdObjResponse = new CreatedObjResponse(project.getId());
        return new ResponseEntity<>(createdObjResponse, HttpStatus.CREATED);
    }

    //------------------------------------------ Update -----------------------------------------------------------------

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/project/edit", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> editProject(@Valid @RequestBody Project project) {

        if (project.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (!projectService.projectExists(project.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String userId = SecurityOperations.getLoggedUserId();
        if (!userId.equals(projectService.findById(project.getId()).getAuthorId())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        projectService.edit(project);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //------------------------------------------ Delete -----------------------------------------------------------------

    @RequestMapping(value = "/project/id/{projectId}/delete", method = RequestMethod.POST)
         public ResponseEntity<Project> deleteProject(@PathVariable String projectId) {
        if (!projectService.projectExists(projectId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        projectService.delete(projectId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
