package ua.com.itproekt.gup.api.rest.news;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.com.itproekt.gup.model.activityfeed.Event;
import ua.com.itproekt.gup.model.activityfeed.EventType;
import ua.com.itproekt.gup.model.news.newsfeed.BlogSubscription;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.news.BlogService;
import ua.com.itproekt.gup.service.news.newsfeed.BlogNewsFeedService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.SecurityOperations;

@RestController
@RequestMapping("/api/rest/newsService")
public class BlogSubscriptionRestController {

    @Autowired
    ActivityFeedService activityFeedService;

    @Autowired
    BlogNewsFeedService blogNewsFeedService;

    @Autowired
    ProfilesService profilesService;

    @Autowired
    BlogService blogService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/blogSubscription/read", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BlogSubscription> getBlogSubscription() {

        String userId = SecurityOperations.getLoggedUserId();

        BlogSubscription blogSubscription = blogNewsFeedService.findBlogSubscriptionByUserId(userId);
        if (blogSubscription == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(blogSubscription, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/blogSubscription/blogId/{blogId}/update/subscription/add", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addSubscription(@PathVariable String blogId) {

        String userId = SecurityOperations.getLoggedUserId();
        String authorId = blogService.findBlog(blogId).getAuthorId();

        activityFeedService.createEvent(new Event(authorId, EventType.BLOG_SUBSCRIPTION, blogId, userId));
        blogNewsFeedService.addSubscription(userId, blogId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/blogSubscription/blogId/{blogId}/update/subscription/delete", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteSubscription(@PathVariable String blogId) {

        String userId = SecurityOperations.getLoggedUserId();

        if (!blogNewsFeedService.subscriptionExist(userId, blogId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        blogNewsFeedService.deleteSubscription(userId, blogId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
