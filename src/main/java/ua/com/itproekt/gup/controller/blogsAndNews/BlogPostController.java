package ua.com.itproekt.gup.controller.blogsAndNews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.itproekt.gup.exception.ResourceNotFoundException;
import ua.com.itproekt.gup.model.news.Blog;
import ua.com.itproekt.gup.model.news.BlogPost;
import ua.com.itproekt.gup.model.news.BlogPostFilterOptions;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.service.news.BlogPostService;
import ua.com.itproekt.gup.service.news.BlogService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.EntityPage;
import ua.com.itproekt.gup.util.SecurityOperations;

/**
 * Created by RAYANT on 13.01.2016.
 */

@Controller
@RequestMapping("/blog-post")
public class BlogPostController {

    @Autowired
    ProfilesService profilesService;

    @Autowired
    BlogPostService blogPostService;

    @Autowired
    BlogService blogService;

    @RequestMapping("/view/id/{blogPostId}")
    public String blogPostView(Model model, @PathVariable String blogPostId) {
        if (!blogPostService.blogPostExists(blogPostId)) {
            throw new ResourceNotFoundException();
        }

        model.addAttribute("blogPostId", blogPostId);
        return "news/blog-post-view";
    }

    @RequestMapping("/news")
    public String newsView() {
        return "news/blogs-and-news";
    }

    @RequestMapping("/view-all/blogId/{blogId}")
    public String blogPostViewAll(Model model, @PathVariable String blogId) {
        if (!blogService.blogExists(blogId)) {
            throw new ResourceNotFoundException();
        }

        BlogPostFilterOptions blogPostFO = new BlogPostFilterOptions();
        blogPostFO.setBlogId(blogId);

        EntityPage<BlogPost> blogPostPages = blogPostService.findBlogPostsWihOptions(blogPostFO);

        model.addAttribute("blogPostPages", blogPostPages);
        return "";
    }

    @RequestMapping("/create/{blogId}")
    public String blogPostCreate(Model model, @PathVariable String blogId) {
        String loggedProfileId = SecurityOperations.getLoggedUserId();
        Blog blog = blogService.findBlog(blogId);

        if (!(loggedProfileId.equals(blog.getAuthorId()) || blog.getEditorsIds().containsValue(loggedProfileId))) {
            throw new AccessDeniedException("You don't have the appropriate privileges to create posts in this blog.");
        }

        model.addAttribute("profileId", loggedProfileId);
        model.addAttribute("blogId", blogId);
        return "news/blog-post-create";
    }

    @RequestMapping("/edit/{blogPostId}")
    public String blogPostEdit(Model model, @PathVariable String blogPostId) {
        if (!blogPostService.blogPostExists(blogPostId)) {
            throw new ResourceNotFoundException();
        }
        String email = SecurityOperations.getCurrentUserEmail();
        Profile profile = profilesService.findProfileByEmail(email);
        BlogPost blogPost = blogPostService.findById(blogPostId);
        model.addAttribute("blogPost", blogPost);
        model.addAttribute("profileId", profile.getId());
        return "news/blog-post-edit";
    }


}
