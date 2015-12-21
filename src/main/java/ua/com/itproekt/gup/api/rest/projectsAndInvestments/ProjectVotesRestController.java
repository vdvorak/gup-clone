package ua.com.itproekt.gup.api.rest.projectsAndInvestments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.com.itproekt.gup.service.projectsAndInvestments.project.ProjectService;
import ua.com.itproekt.gup.util.SecurityOperations;

@RestController
@RequestMapping("/api/rest/projectsAndInvestmentsService")
public class ProjectVotesRestController {

    @Autowired
    ProjectService projectService;

    @RequestMapping(value = "/project/id/{projectId}/vote/{score}", method = RequestMethod.POST)
    public ResponseEntity<Void> scoreProject(@PathVariable String projectId,
                                             @PathVariable int score) {
        if (!projectService.projectExists(projectId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String userId = SecurityOperations.getLoggedUserId();
        projectService.vote(projectId, userId, score);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
