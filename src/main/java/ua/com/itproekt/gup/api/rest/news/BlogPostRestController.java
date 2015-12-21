package ua.com.itproekt.gup.api.rest.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.model.activityfeed.Event;
import ua.com.itproekt.gup.model.activityfeed.EventType;
import ua.com.itproekt.gup.model.news.Blog;
import ua.com.itproekt.gup.model.news.BlogPost;
import ua.com.itproekt.gup.model.news.BlogPostFilterOptions;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.news.BlogPostService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.CreatedObjResponse;
import ua.com.itproekt.gup.util.EntityPage;
import ua.com.itproekt.gup.util.SecurityOperations;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/rest/newsService")
public class BlogPostRestController {

    @Autowired
    BlogPostService blogPostService;

    @Autowired
    ProfilesService profilesService;

    @Autowired
    ActivityFeedService activityFeedService;

    //------------------------------------------ Read -----------------------------------------------------------------

    @RequestMapping(value = "/blogPost/id/{blogPostId}/read", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BlogPost> getBlogPostById(@PathVariable String blogPostId) {
        BlogPost blogPost = blogPostService.findBlogPostAndIncViews(blogPostId);

        if (blogPost == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blogPost, HttpStatus.OK);
    }

    @RequestMapping(value = "/blogPost/read/all", method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityPage<BlogPost>> listOfAllBlogPost(@RequestBody BlogPostFilterOptions blogPostFO) {
        EntityPage<BlogPost> blogPages = blogPostService.findBlogPostsWihOptions(blogPostFO);

        if(blogPages.getEntities().isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogPages, HttpStatus.OK);
    }

    //------------------------------------------ Create -----------------------------------------------------------------

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/blogPost/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatedObjResponse> createBlogPost(@Valid @RequestBody BlogPost blogPost) {

        // Проверка на права создавать посты от блога{userId, blogId} !!!!!!!!
        // .........

        String userId = SecurityOperations.getLoggedUserId();
        blogPost.setAuthorId(userId);

        blogPostService.create(blogPost);

        CreatedObjResponse createdObjResponse = new CreatedObjResponse(blogPost.getId());
        return new ResponseEntity<>(createdObjResponse, HttpStatus.CREATED);
    }

    //------------------------------------------ Update -----------------------------------------------------------------

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/blogPost/edit", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BlogPost> editBlogPost(@Valid @RequestBody BlogPost blogPost) {

        if (blogPost.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (!blogPostService.blogPostExists(blogPost.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // editor = authorId
        String userId = SecurityOperations.getLoggedUserId();
        if (userId.equals(blogPostService.findById(blogPost.getId()).getAuthorId())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        BlogPost newBlogPost = blogPostService.edit(blogPost);
        return new ResponseEntity<>(newBlogPost, HttpStatus.OK);
    }

    //------------------------------------------ like/dislike -----------------------------------------------------------------

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/blogPost/id/{blogPostId}/like", method = RequestMethod.POST)
    public ResponseEntity<BlogPost> likeBlogPost(@PathVariable String blogPostId) {

        if (!blogPostService.blogPostExists(blogPostId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String userId = SecurityOperations.getLoggedUserId();
        String authorId = blogPostService.findById(blogPostId).getAuthorId();

        blogPostService.likeBlogPost(blogPostId, userId);

        activityFeedService.createEvent(new Event(authorId, EventType.BLOG_POST_LIKE, blogPostId, userId));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/blogPost/id/{blogPostId}/dislike", method = RequestMethod.POST)
    public ResponseEntity<BlogPost> dislikeBlogPost(@PathVariable String blogPostId) {

        if (!blogPostService.blogPostExists(blogPostId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String authorId = blogPostService.findById(blogPostId).getAuthorId();
        String userId = SecurityOperations.getLoggedUserId();

        blogPostService.dislikeBlogPost(blogPostId, userId);
        activityFeedService.createEvent(new Event(authorId, EventType.BLOG_POST_DISLIKE, blogPostId, userId));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //------------------------------------------ Delete -----------------------------------------------------------------

    @RequestMapping(value = "/blogPost/id/{blogPostId}/delete", method = RequestMethod.POST)
    public ResponseEntity<Blog> deleteBlogPost(@PathVariable String blogPostId) {
        if (!blogPostService.blogPostExists(blogPostId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        blogPostService.delete(blogPostId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
