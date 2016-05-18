package ua.com.itproekt.gup.api.rest.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.model.news.Blog;
import ua.com.itproekt.gup.model.news.BlogFilterOptions;
import ua.com.itproekt.gup.service.news.BlogService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.seosequence.SeoSequenceService;
import ua.com.itproekt.gup.util.CreatedObjResp;
import ua.com.itproekt.gup.util.EntityPage;
import ua.com.itproekt.gup.util.SecurityOperations;
import ua.com.itproekt.gup.util.SeoUtils;

import javax.validation.Valid;

@RestController
@RequestMapping("api/rest/newsService")
public class BlogRestController {

    @Autowired
    BlogService blogService;

    @Autowired
    ProfilesService profilesService;

    @Autowired
    SeoSequenceService seoSequenceService;

    //------------------------------------------ Read -----------------------------------------------------------------

    @RequestMapping(value = "/blog/id/{id}/read", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Blog> getBlogById(@PathVariable String id) {
        Blog oldBlog = blogService.findBlog(id);
        if (oldBlog == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(oldBlog, HttpStatus.OK);
    }

    @RequestMapping(value = "/blog/read/all", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityPage<Blog>> listOfAllBlogs(@RequestBody BlogFilterOptions blogFO) {
        EntityPage<Blog> blogs = blogService.findBlogWihOptions(blogFO);

        if(blogs.getEntities().isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    //------------------------------------------ Create -----------------------------------------------------------------

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/blog/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatedObjResp> createBlog(@Valid @RequestBody Blog blog) {

        String userId = SecurityOperations.getLoggedUserId();
        blog.setAuthorId(userId);

        long longValueOfSeoKey = seoSequenceService.getNextSequenceId();

        SeoUtils.makeSeoFieldsForBlog(blog, longValueOfSeoKey);

        blogService.createBlog(blog);

        CreatedObjResp createdObjResp = new CreatedObjResp(blog.getId());
        return new ResponseEntity<>(createdObjResp, HttpStatus.CREATED);
    }

    //------------------------------------------ Update -----------------------------------------------------------------

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/blog/edit", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Blog> editBlog(@Valid @RequestBody Blog blog) {

        if (blog.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (!blogService.blogExists(blog.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        blogService.findBlogAndUpdate(blog);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //------------------------------------------ Delete -----------------------------------------------------------------

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/blog/id/{id}/delete", method = RequestMethod.POST)
    public ResponseEntity<Blog> deleteBlog(@PathVariable String id) {
        if (!blogService.blogExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        blogService.deleteBlog(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
