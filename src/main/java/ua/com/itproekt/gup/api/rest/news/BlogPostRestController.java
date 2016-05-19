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
import ua.com.itproekt.gup.model.seosequence.SeoSequence;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.news.BlogPostService;
import ua.com.itproekt.gup.service.news.BlogService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.seosequence.SeoSequenceService;
import ua.com.itproekt.gup.util.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/api/rest/newsService")
public class BlogPostRestController {

    @Autowired
    BlogPostService blogPostService;

    @Autowired
    BlogService blogService;

    @Autowired
    ProfilesService profilesService;

    @Autowired
    ActivityFeedService activityFeedService;

    @Autowired
    SeoSequenceService seoSequenceService;

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

        if (blogPages.getEntities().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogPages, HttpStatus.OK);
    }

    //------------------------------------------ Create -----------------------------------------------------------------

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/blogPost/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatedObjResp> createBlogPost(@Valid @RequestBody BlogPost blogPost) {

        Blog blog = blogService.findBlog(blogPost.getBlogId());

        if (blog == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String userId = SecurityOperations.getLoggedUserId();

        if (!userId.equals(blog.getAuthorId())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }


        long longValueOfSeoKey = seoSequenceService.getNextSequenceId();

        SeoUtils.makeSeoFieldsForBlogPost(blogPost, longValueOfSeoKey);
        
        blogPost.setAuthorId(userId);
        blogPostService.create(blogPost);
        return new ResponseEntity<>(new CreatedObjResp(blogPost.getId()), HttpStatus.CREATED);
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
        if (!blogPostService.findById(blogPost.getId()).getAuthorId().equals(userId)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        BlogPost oldBlogPost = blogPostService.findById(blogPost.getId());
        String newTransiltTitle = Translit.makeTransliteration(blogPost.getTitle());
        String newSeoUrl = newTransiltTitle + "-" + oldBlogPost.getSeoKey();
        blogPost.setSeoUrl(newSeoUrl);

        blogPostService.edit(blogPost);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //------------------------------------------ like/dislike -----------------------------------------------------------------

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/blogPost/id/{blogPostId}/like", method = RequestMethod.POST)
    public ResponseEntity<BlogPost> likeBlogPost(@PathVariable String blogPostId) {

        if (!blogPostService.blogPostExists(blogPostId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String userId = SecurityOperations.getLoggedUserId();
        BlogPost blogPost = blogPostService.findById(blogPostId);

        if (blogPost.getLikedIds().contains(userId)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        blogPostService.likeBlogPost(blogPostId, userId);
        activityFeedService.createEvent(new Event(blogPost.getAuthorId(), EventType.BLOG_POST_LIKE, blogPostId, userId));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/blogPost/id/{blogPostId}/dislike", method = RequestMethod.POST)
    public ResponseEntity<BlogPost> dislikeBlogPost(@PathVariable String blogPostId) {

        if (!blogPostService.blogPostExists(blogPostId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        BlogPost blogPost = blogPostService.findById(blogPostId);
        String userId = SecurityOperations.getLoggedUserId();
        if (blogPost.getDislikedIds().contains(userId)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        blogPostService.dislikeBlogPost(blogPostId, userId);
        activityFeedService.createEvent(new Event(blogPost.getAuthorId(), EventType.BLOG_POST_DISLIKE, blogPostId, userId));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //------------------------------------------ Delete -----------------------------------------------------------------

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/blogPost/id/{blogPostId}/delete", method = RequestMethod.POST)
    public ResponseEntity<Blog> deleteBlogPost(@PathVariable String blogPostId) {
        if (!blogPostService.blogPostExists(blogPostId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        blogPostService.delete(blogPostId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
