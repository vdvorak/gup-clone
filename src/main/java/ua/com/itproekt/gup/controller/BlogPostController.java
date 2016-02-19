package ua.com.itproekt.gup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.itproekt.gup.exception.ResourceNotFoundException;
import ua.com.itproekt.gup.model.news.BlogPost;
import ua.com.itproekt.gup.model.news.BlogPostFilterOptions;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.service.news.BlogPostService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.EntityPage;

import java.util.ArrayList;
import java.util.List;

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
    public String newsView(@RequestParam int pageNumber, Model model) {
//        BlogPostFilterOptions blogPostFO = new BlogPostFilterOptions();
//        EntityPage<BlogPost> blogPostPages = blogPostService.findBlogPostsWihOptions(blogPostFO);
//        model.addAttribute("news", blogPostPages);
        model.addAttribute("pageNumber", pageNumber);
        return "news";
    }

    @RequestMapping("/view-all/{blogId}")
    public String blogPostViewAll(Model model, @PathVariable("blogId") String blogId) {
        BlogPostFilterOptions blogPostFO = new BlogPostFilterOptions();
        blogPostFO.setBlogId(blogId);

        EntityPage<BlogPost> blogPostPages = blogPostService.findBlogPostsWihOptions(blogPostFO);
        //TODO убрать когда заработает фильтр по ид блога
        List<BlogPost> filteredPosts = new ArrayList<>();
        for (BlogPost blogPost : blogPostPages.getEntities()) {
            if(blogPost.getBlogId().equals(blogId)){
                filteredPosts.add(blogPost);
            }
        }

        model.addAttribute("blogPostPages", filteredPosts);
        return "blog-post-view-all";
    }

    //ToDo Проверять "А ты ли владелец этого блога, чтобы в неём создовать новость?"
    @RequestMapping("/create/{blogId}")
    public String blogPostCreate(Model model, @PathVariable("blogId") String blogId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Profile profile = profilesService.findProfileByEmail(email);
        model.addAttribute("profileId", profile.getId());
        model.addAttribute("blogId", blogId);
        return "news/blog-post-create-OLD";
    }

    @RequestMapping("/edit/{blogPostId}")
    public String blogPostEdit(Model model, @PathVariable("blogPostId") String blogPostId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Profile profile = profilesService.findProfileByEmail(email);
        BlogPost blogPost = blogPostService.findById(blogPostId);
        model.addAttribute("blogPost", blogPost);
        model.addAttribute("profileId", profile.getId());
        return "blog-post-edit";
    }


}
