package ua.com.itproekt.gup.controller.blogsAndNews;

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
import ua.com.itproekt.gup.util.SeoUtils;


@Controller
public class BlogController {

    @Autowired
    BlogService blogService;

    @Autowired
    ProfilesService profilesService;



    //ToDo Delete this after SEO url for blog will be complete
//    @RequestMapping(value = "/blog/{id}", method = RequestMethod.GET)
//    public String getBlogRead(Model model, @PathVariable String id) {
//        boolean check = false;
//
//        Blog blog = blogService.findBlog(id);
//
//        if (SecurityOperations.isUserLoggedIn()) {
//            String userId = SecurityOperations.getLoggedUserId();
//            check = userId.equals(blog.getAuthorId());
//            model.addAttribute("check", check);
//        }
//
//        Profile profile = profilesService.findWholeProfileById(blog.getAuthorId());
//        String userName = profile.getUsername();
//
//        String flag = "news";
//        model.addAttribute("flag", flag);
//        model.addAttribute("username", userName);
//        model.addAttribute("check", check);
//        model.addAttribute("blog", blog);
//        return "news/blog";
//    }




    @RequestMapping(value = "/blog/{seoUrl}", method = RequestMethod.GET)
    public String getBlogReadBySeoKey(Model model, @PathVariable String seoUrl) {
        boolean check = false;


        String blogSeoKey = SeoUtils.getKey(seoUrl);


        Blog blog = blogService.findBySeoKey(blogSeoKey);


        if (SecurityOperations.isUserLoggedIn()) {
            String userId = SecurityOperations.getLoggedUserId();
            check = userId.equals(blog.getAuthorId());
            model.addAttribute("check", check);
        }

        Profile profile = profilesService.findWholeProfileById(blog.getAuthorId());
        String userName = profile.getUsername();

        String flag = "news";
        model.addAttribute("flag", flag);
        model.addAttribute("username", userName);
        model.addAttribute("check", check);
        model.addAttribute("blog", blog);
        return "news/blog";
    }











    @RequestMapping("/blogs-and-news")
    public String getAdminProfileBlogsAndNews() {
        return "news/blogs-and-news";
    }

    @RequestMapping("/blog-create")
    public String getBlogCreatePage() {
        return "news/blog-create";
    }


    //ToDo Delete this after all SEO works will complete with blogs and news
//    @RequestMapping(value = "/blog/{id}/edit", method = RequestMethod.GET)
//    public String getBlogEditPage(Model model, @PathVariable String id) {
//
//        String userId = SecurityOperations.getLoggedUserId();
//
//        Blog blog = blogService.findBlog(id);
//
//        if (blog.getAuthorId().equals(userId)) {
//            String flag = "news";
//            model.addAttribute("flag", flag);
//            model.addAttribute("blog", blog);
//            return "news/blog-edit";
//        } else {
//            return "index";
//        }
//    }


    @RequestMapping(value = "/blog/{seoUrl}/edit", method = RequestMethod.GET)
    public String getBlogEditPage(Model model, @PathVariable String seoUrl) {

        String userId = SecurityOperations.getLoggedUserId();

        String seoKey = SeoUtils.getKey(seoUrl);

        Blog blog = blogService.findBySeoKey(seoKey);

        if (blog.getAuthorId().equals(userId)) {
            String flag = "news";
            model.addAttribute("flag", flag);
            model.addAttribute("blog", blog);
            return "news/blog-edit";
        } else {
            return "index";
        }
    }

}
