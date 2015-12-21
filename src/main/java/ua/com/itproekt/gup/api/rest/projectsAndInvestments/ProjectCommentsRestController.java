package ua.com.itproekt.gup.api.rest.projectsAndInvestments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.model.activityfeed.Event;
import ua.com.itproekt.gup.model.activityfeed.EventType;
import ua.com.itproekt.gup.model.projectsAndInvestments.project.Comment;
import ua.com.itproekt.gup.model.projectsAndInvestments.project.Project;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.projectsAndInvestments.project.ProjectService;
import ua.com.itproekt.gup.util.CreatedObjResponse;
import ua.com.itproekt.gup.util.SecurityOperations;

@RestController
@RequestMapping("/api/rest/projectsAndInvestmentsService")
public class ProjectCommentsRestController {

    @Autowired
    ProjectService projectService;

    @Autowired
    ActivityFeedService activityFeedService;

    @RequestMapping(value = "/project/id/{projectId}/comment/id/{commentId}/read", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Project> getCommentById(@PathVariable String projectId,
                                                  @PathVariable String commentId) {
        Project project = projectService.findComment(projectId, commentId);
        if (project == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/project/id/{projectId}/comment/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatedObjResponse> createComment(@PathVariable String projectId,
                                                            @RequestBody Comment comment) {

        if (!projectService.projectExists(projectId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String userId = SecurityOperations.getLoggedUserId();

        if (!projectService.userHasVoted(projectId, userId)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        comment.setFromId(userId);
        projectService.addComment(projectId, comment);

        String toId = comment.getToId();

        if( projectId.equals(toId)) {
            String authorId = projectService.findById(toId).getAuthorId();
            activityFeedService.createEvent(new Event(authorId, EventType.PROJECT_COMMENT, comment.getcId(), userId));
        } else {
            activityFeedService.createEvent(new Event(toId, EventType.PROJECT_COMMENT_REPLY, comment.getcId(), userId));
        }


        CreatedObjResponse createdObjResponse = new CreatedObjResponse(comment.getcId());
        return new ResponseEntity<>(createdObjResponse, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/project/id/{projectId}/comment/id/{commentId}/delete", method = RequestMethod.POST)
    public ResponseEntity<Project> deleteComment(@PathVariable String projectId,
                                                 @PathVariable String commentId) {
        if (!projectService.commentExists(projectId, commentId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        projectService.deleteComment(projectId, commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
