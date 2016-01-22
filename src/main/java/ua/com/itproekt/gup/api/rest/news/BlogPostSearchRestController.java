package ua.com.itproekt.gup.api.rest.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.itproekt.gup.service.news.BlogPostService;

import java.util.Set;

@RestController
public class BlogPostSearchRestController {

    @Autowired
    BlogPostService blogPostService;

    @RequestMapping("/search/blogPost")
    public Set<String> getMachedNames(@RequestParam String term){
        return blogPostService.getMatchedNames(term);
    }
}
