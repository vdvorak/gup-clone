package ua.com.itproekt.gup.api.rest.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.itproekt.gup.service.news.BlogPostService;

import java.util.List;

@RestController
public class BlogPostSearch {

    @Autowired
    BlogPostService blogPostService;

    @RequestMapping("/search/blogPost")
    public List<String> getMachedNames(@RequestParam String term){
        return blogPostService.getMatchedNames(term);
    }
}
