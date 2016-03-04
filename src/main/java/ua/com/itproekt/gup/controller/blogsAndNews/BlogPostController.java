package ua.com.itproekt.gup.controller.blogsAndNews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.itproekt.gup.exception.ResourceNotFoundException;
import ua.com.itproekt.gup.model.news.BlogPost;
import ua.com.itproekt.gup.model.news.BlogPostFilterOptions;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.service.news.BlogPostService;
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

    @RequestMapping("/view/id/{blogPostId}")
    public String blogPostView(Model model, @PathVariable String blogPostId) {

//        boolean check = false;
//
//        BlogPost blogPost = blogPostService.findBlogPostAndIncViews(id);
//
//        if (SecurityOperations.isUserLoggedIn()) {
//            String userId = SecurityOperations.getLoggedUserId();
//            check = userId.equals(blogPost.getAuthorId());
//            model.addAttribute("check", check);
//        }
//
//        model.addAttribute("check", check);
//        model.addAttribute("blogPost", blogPost);

        if (!blogPostService.blogPostExists(blogPostId)) {
            throw new ResourceNotFoundException();
        }

        model.addAttribute("blogPostId", blogPostId);
        return "news/blog-post-view";
    }

    @RequestMapping("/news")
    public String newsView() {
//    public String newsView(@RequestParam(required = false, defaultValue = "0") int pageNumber, Model model) {
//        BlogPostFilterOptions blogPostFO = new BlogPostFilterOptions();
//        EntityPage<BlogPost> blogPostPages = blogPostService.findBlogPostsWihOptions(blogPostFO);
//        model.addAttribute("news", blogPostPages);
//        model.addAttribute("pageNumber", pageNumber);
        return "news/blogs-and-news";
    }

    @RequestMapping("/view-all/blogId/{blogId}")
    public String blogPostViewAll(Model model, @PathVariable String blogId) {
        BlogPostFilterOptions blogPostFO = new BlogPostFilterOptions();
        blogPostFO.setBlogId(blogId);

        EntityPage<BlogPost> blogPostPages = blogPostService.findBlogPostsWihOptions(blogPostFO);

//        List<BlogPost> filteredPosts = new ArrayList<>();
//        for (BlogPost blogPost : blogPostPages.getEntities()) {
//            if(blogPost.getBlogId().equals(blogId)){
//                filteredPosts.add(blogPost);
//            }
//        }

        model.addAttribute("blogPostPages", blogPostPages);
        return "";
    }

    //ToDo Проверять "А ты ли владелец этого блога, чтобы в неём создовать новость?"
    @RequestMapping("/create/{blogId}")
    public String blogPostCreate(Model model, @PathVariable("blogId") String blogId) {
        Profile profile = profilesService.findById(SecurityOperations.getLoggedUserId());
        model.addAttribute("profileId", profile.getId());
        model.addAttribute("blogId", blogId);
        return "news/blog-post-create";
    }

    @RequestMapping("/edit/{blogPostId}")
    public String blogPostEdit(Model model, @PathVariable("blogPostId") String blogPostId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Profile profile = profilesService.findProfileByEmail(email);
        BlogPost blogPost = blogPostService.findById(blogPostId);
        model.addAttribute("blogPost", blogPost);
        model.addAttribute("profileId", profile.getId());
        return "news/blog-post-edit";
    }


}
