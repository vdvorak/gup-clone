package ua.com.itproekt.gup.api.rest.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import ua.com.itproekt.gup.model.news.newsfeed.BlogNewsFeed;
import ua.com.itproekt.gup.model.news.newsfeed.BlogSubscription;
import ua.com.itproekt.gup.service.news.BlogService;
import ua.com.itproekt.gup.service.news.newsfeed.BlogNewsFeedService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.SecurityOperations;


@RestController
@RequestMapping("/api/rest/newsService")
public class BlogNewsFeedRestController {

    @Autowired
    BlogNewsFeedService blogNewsFeedService;

    @Autowired
    BlogService blogService;

    @Autowired
    ProfilesService profilesService;

    //------------------------------------------ Read -----------------------------------------------------------------

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/blogNewsFeed/read/{skip}/{limit}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BlogNewsFeed> getBlogNewsFeed(@PathVariable int skip,
                                                        @PathVariable int limit) {

        String userId = SecurityOperations.getLoggedUserId();
        BlogNewsFeed blogNewsFeed = blogNewsFeedService.findBlogNewsFeedByUserId(userId, skip, limit);
        if (blogNewsFeed == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(blogNewsFeed, HttpStatus.OK);
    }

    //------------------------------------------ Create -----------------------------------------------------------------

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/blogNewsFeed/create/", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createBlogNewsFeed(UriComponentsBuilder ucBuilder) {

        String userId = SecurityOperations.getLoggedUserId();

        if (blogNewsFeedService.blogSubscriptionExistsForUser(userId)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            createBlogSubscriptionsForUser(userId);
        }

        if (blogNewsFeedService.blogNewsFeedExistsForUser(userId)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            createBlogNewsFeedForUser(userId);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/blogNewsFeed/read/{skip}/{limit}").buildAndExpand(0,20).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    //------------------------------------------ Update -----------------------------------------------------------------

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/blogNewsFeed/update/", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createComment(UriComponentsBuilder ucBuilder) {

        String userId = SecurityOperations.getLoggedUserId();

        if (!blogNewsFeedService.blogSubscriptionExistsForUser(userId) || !blogNewsFeedService.blogNewsFeedExistsForUser(userId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        blogNewsFeedService.updateBlogNewsFeed(userId);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/blogNewsFeed/read/{skip}/{limit}").buildAndExpand(0, 20).toUri());
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    //---------------------------------------------------------------------------------------------------------------

    public void createBlogNewsFeedForUser(String userId) {
        if (!blogNewsFeedService.blogNewsFeedExistsForUser(userId)) {
            BlogNewsFeed blogNewsFeed = new BlogNewsFeed();
            blogNewsFeed.setUserId(userId);
            blogNewsFeedService.createBlogNewsFeed(blogNewsFeed);
        }
    }

    public void createBlogSubscriptionsForUser(String userId) {
        if (!blogNewsFeedService.blogSubscriptionExistsForUser(userId)) {
            BlogSubscription blogSubscription = new BlogSubscription();
            blogSubscription.setUserId(userId);
            blogNewsFeedService.createBlogSubscription(blogSubscription);
        }
    }
}
