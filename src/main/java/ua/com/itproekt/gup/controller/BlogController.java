package ua.com.itproekt.gup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.itproekt.gup.model.news.Blog;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.service.news.BlogService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.SecurityOperations;

/**
 * Created by Optical Illusion on 13.01.2016.
 */
@Controller
public class BlogController {

    @Autowired
    BlogService blogService;

    @Autowired
    ProfilesService profilesService;


    @RequestMapping("/blog-create")
    public String getBlogCreatePage() {
        return "blog-create";
    }

    @RequestMapping(value = "/blog/{id}/edit", method = RequestMethod.GET)
    public String getBlogEditPage(Model model, @PathVariable String id) {

        String userId = SecurityOperations.getLoggedUserId();

        Blog blog = blogService.findBlog(id);

        if (blog.getAuthorId().equals(userId)) {
            model.addAttribute("blog", blog);
            return "blog-edit";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/blog/{id}", method = RequestMethod.GET)
    public String getBlogRead(Model model, @PathVariable String id) {

        String userId = SecurityOperations.getLoggedUserId();
        Blog blog = blogService.findBlog(id);
        boolean check = userId.equals(blog.getAuthorId());
        Profile profile = profilesService.findById(blog.getAuthorId());
        String userName = profile.getUsername();

        model.addAttribute("username", userName);
        model.addAttribute("check", check);
        model.addAttribute("blog", blog);
        return "blog";
    }

}
