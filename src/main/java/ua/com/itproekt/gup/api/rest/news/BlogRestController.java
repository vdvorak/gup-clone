package ua.com.itproekt.gup.api.rest.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.model.news.Blog;
import ua.com.itproekt.gup.service.news.BlogService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.CreatedObjResponse;
import ua.com.itproekt.gup.util.SecurityOperations;

import javax.validation.Valid;

@RestController
@RequestMapping("api/rest/newsService")
public class BlogRestController {

    @Autowired
    BlogService blogService;

    @Autowired
    ProfilesService profilesService;

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

    //------------------------------------------ Create -----------------------------------------------------------------

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/blog/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatedObjResponse> createBlog(@Valid @RequestBody Blog blog) {

        String userId = SecurityOperations.getLoggedUserId();
        blog.setAuthorId(userId);
        blogService.createBlog(blog);

        CreatedObjResponse createdObjResponse = new CreatedObjResponse(blog.getId());
        return new ResponseEntity<>(createdObjResponse, HttpStatus.CREATED);
    }

    //------------------------------------------ Update -----------------------------------------------------------------

    @RequestMapping(value = "/blog/edit", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Blog> editBlog(@Valid @RequestBody Blog blog) {

        if (blog.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (!blogService.blogExists(blog.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Blog updatedBlog = blogService.findBlogAndUpdate(blog);

        return new ResponseEntity<>(updatedBlog, HttpStatus.OK);
    }

    //------------------------------------------ Delete -----------------------------------------------------------------

    @RequestMapping(value = "/blog/id/{id}/delete", method = RequestMethod.POST)
    public ResponseEntity<Blog> deleteBlog(@PathVariable String id) {
        if (!blogService.blogExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        blogService.deleteBlog(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
