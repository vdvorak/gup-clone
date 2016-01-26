package ua.com.itproekt.gup.api.rest.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.model.activityfeed.Event;
import ua.com.itproekt.gup.model.activityfeed.EventType;
import ua.com.itproekt.gup.model.news.BlogPost;
import ua.com.itproekt.gup.model.news.Comment;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.news.BlogPostService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.CreatedObjResponse;
import ua.com.itproekt.gup.util.SecurityOperations;

@RestController
@RequestMapping("/api/rest/newsService")
public class BlogPostCommentRestController {

    @Autowired
    ActivityFeedService activityFeedService;

    @Autowired
    ProfilesService profilesService;

    @Autowired
    BlogPostService blogPostService;

    @RequestMapping(value = "/blogPost/id/{blogPostId}/comment/{commentId}/read", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BlogPost> getCommentById(@PathVariable String blogPostId,
                                                   @PathVariable String commentId) {
        BlogPost blogPost = blogPostService.findComment(blogPostId, commentId);
        if (blogPost == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(blogPost, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/blogPost/id/{blogPostId}/comment/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatedObjResponse> createComment(@PathVariable String blogPostId,
                                                            @RequestBody Comment comment) {
        if (comment.getComment().trim().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String userId = SecurityOperations.getLoggedUserId();
        comment.setFromId(userId);
        blogPostService.createComment(blogPostId, comment);

        String toId = comment.getToId();

        if( blogPostId.equals(toId)) {
            String authorId = blogPostService.findById(toId).getAuthorId();
            activityFeedService.createEvent(new Event(authorId, EventType.BLOG_POST_COMMENT, comment.getcId(), userId));
        } else {
            activityFeedService.createEvent(new Event(toId, EventType.BLOG_POST_COMMENT_REPLY, comment.getcId(), userId));
        }

        CreatedObjResponse createdObjResponse = new CreatedObjResponse(comment.getcId());
        return new ResponseEntity<>(createdObjResponse, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/blogPost/id/{blogPostId}/comment/id/{commentId}/delete", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteComment(@PathVariable String blogPostId,
                                                  @PathVariable String commentId) {

        if (!blogPostService.commentExists(blogPostId, commentId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        blogPostService.deleteComment(blogPostId, commentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/blogPost/id/{blogPostId}/comment/id/{commentId}/like", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> likeComment(@PathVariable String blogPostId,
                                                @PathVariable String commentId) {

        if (!blogPostService.commentExists(blogPostId, commentId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String authorId = blogPostService.findComment(blogPostId, commentId).getComments().iterator().next().getFromId();
        String userId = SecurityOperations.getLoggedUserId();

        blogPostService.likeComment(blogPostId, commentId, userId);
        activityFeedService.createEvent(new Event(authorId, EventType.BLOG_POST_COMMENT_LIKE, blogPostId, userId));

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
