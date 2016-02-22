package ua.com.itproekt.gup.controller.blogsAndNews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.itproekt.gup.model.news.Blog;
import ua.com.itproekt.gup.model.news.BlogFilterOptions;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.service.news.BlogService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.EntityPage;
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


//    @RequestMapping("/blog-create")
//    public String getBlogCreatePage() {
//        return "blog-create";
//    }

    //ToDo - delete when new version will work
    @RequestMapping("/blog-create")
    public String getBlogCreatePage() {
        return "news/blog-create-OLD";
    }

    @RequestMapping("/blogs")
    public String getBlogsRead(Model model) {

        BlogFilterOptions blogFilterOptions = new BlogFilterOptions();

        EntityPage<Blog> blogEntityPage = blogService.findBlogWihOptions(blogFilterOptions);
        model.addAttribute("blogPages", blogEntityPage);
        return "news/blogs-OLD";
    }


    @RequestMapping(value = "/blog/{id}/edit", method = RequestMethod.GET)
    public String getBlogEditPage(Model model, @PathVariable String id) {

        String userId = SecurityOperations.getLoggedUserId();

        Blog blog = blogService.findBlog(id);

        if (blog.getAuthorId().equals(userId)) {
            model.addAttribute("blog", blog);
            return "news/blog-edit-OLD";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/blog/{id}", method = RequestMethod.GET)
    public String getBlogRead(Model model, @PathVariable String id) {
        boolean check = false;

        Blog blog = blogService.findBlog(id);

        if (SecurityOperations.isUserLoggedIn()) {
            String userId = SecurityOperations.getLoggedUserId();
            check = userId.equals(blog.getAuthorId());
            model.addAttribute("check", check);
        }

        Profile profile = profilesService.findWholeProfileById(blog.getAuthorId());
        String userName = profile.getUsername();

        model.addAttribute("username", userName);
        model.addAttribute("check", check);
        model.addAttribute("blog", blog);
        return "news/blog-OLD";
    }


    @RequestMapping("/blogs-and-news")
    public String getAdminProfileBlogsAndNews() {
        return "news/blogs-and-news";
    }

}
