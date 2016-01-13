package ua.com.itproekt.gup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.itproekt.gup.model.news.Blog;
import ua.com.itproekt.gup.service.news.BlogService;
import ua.com.itproekt.gup.util.SecurityOperations;

/**
 * Created by Optical Illusion on 13.01.2016.
 */
@Controller
public class BlogController {

    @Autowired
    BlogService blogService;

    @RequestMapping("/blog-create")
    public String getBlogCreatePage() {
        return "blog-create";
    }

    @RequestMapping(value = "/blog/{id}/edit", method = RequestMethod.POST)
    public String getBlogEditPage(Model model, @PathVariable String id) {

        String userId = SecurityOperations.getLoggedUserId();

        Blog oldBlog = blogService.findBlog(id);

        if (oldBlog.getAuthorId().equals(userId)) {
            model.addAttribute("profile", oldBlog);
            return "blog-edit";
        } else {
            return "index";
        }
    }
}
