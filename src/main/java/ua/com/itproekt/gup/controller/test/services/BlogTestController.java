package ua.com.itproekt.gup.controller.test.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.itproekt.gup.dao.news.BlogPostRepository;
import ua.com.itproekt.gup.dao.news.BlogRepository;
import ua.com.itproekt.gup.dao.news.newsfeed.BlogNewsFeedRepository;
import ua.com.itproekt.gup.model.news.Blog;
import ua.com.itproekt.gup.model.news.BlogPost;
import ua.com.itproekt.gup.model.news.Comment;
import ua.com.itproekt.gup.model.news.newsfeed.BlogNewsFeed;
import ua.com.itproekt.gup.model.news.newsfeed.BlogSubscription;

import java.util.Date;
import java.util.HashSet;

@Controller
public class BlogTestController {
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    BlogPostRepository blogPostRepository;

    @Autowired
    BlogNewsFeedRepository blogNewsFeedRepository;

    @RequestMapping(value = "/test/subscriptionExist/{userId}/{blogId}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BlogNewsFeed> testSubscriptionExist(@PathVariable("userId") String userId,
                                                              @PathVariable("blogId") String blogId) {
        blogNewsFeedRepository.subscriptionExist(userId, blogId);

        return new ResponseEntity<>( HttpStatus.OK);
    }

    @RequestMapping(value = "/test/blogNewsFeed/blogSubscription/create/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createTestB() {
        blogNewsFeedRepository.createBlogNewsFeed(new BlogNewsFeed());
        blogNewsFeedRepository.createBlogSubscription(new BlogSubscription());

     return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/test/blogNewsFeed/addBlogNewsFeedItems/{userId}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createTestC(@PathVariable("userId") String userId) {

        blogNewsFeedRepository.updateBlogNewsFeed(userId);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }


    @RequestMapping(value = "/test/blogNewsFeed/{userId}/{skip}/{limit}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BlogNewsFeed> getBlogNewsFeed(@PathVariable("userId") String userId,
                                                                    @PathVariable("skip") int skip,
                                                                    @PathVariable("limit") int limit) {
        BlogNewsFeed blogNewsFeed = blogNewsFeedRepository.findBlogNewsFeedByUserId(userId, skip, limit);
        if(blogNewsFeed.getBlogNewsFeedItems().isEmpty()){
            return new ResponseEntity<BlogNewsFeed>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<BlogNewsFeed>(blogNewsFeed, HttpStatus.OK);
    }

    @RequestMapping("/addBlog/{numberOfBlogs}")
    public String addBlogWithPosts(@PathVariable("numberOfBlogs") int numberOfBlogs, Model model) {
        for (int i = 0; i < numberOfBlogs; i++) {
            Blog blog = new Blog();
            blog.setAuthorId("24438TEST");
            blog.setDescription("BLOG DESCRIPTION BLOG DESCRIPTION BLOG DESCRIPTION BLOG DESCRIPTION BLOG DESCRIPTION" + numberOfBlogs);
            blog.setTitle("BLOG NAME" + numberOfBlogs);

            HashSet<String> blogTagSet = new HashSet<>();
            blogTagSet.add("blog tag 1");
            blogTagSet.add("blog tag 2");
            blogTagSet.add("blog tag 3");
            blogTagSet.add("blog tag" + numberOfBlogs);
            blog.setCategories(blogTagSet);

            blogRepository.createBlog(blog);
        }


        model.addAttribute("message", numberOfBlogs + " test blogs is created.");
        return "index";
    }

    @RequestMapping("/addPostsToBlog/{blogId}/{numberOfPosts}")
    public String addPostsToBlog(@PathVariable String blogId, @PathVariable int numberOfPosts, Model model) {
        for (int i = 0; i < numberOfPosts; i++) {
            BlogPost blogPost = new BlogPost()
                .setBlogId(blogId)
                .setAuthorId(i + "TEST")
                .setText("post text very important part of news, possible it the most important part" + i)
                .setTitle("post title " + i)
                .setViews(i);
                HashSet<String> blogPostSet = new HashSet<>();
                blogPostSet.add("tag 1");
                blogPostSet.add("tag 2");
                blogPostSet.add("tag" + (3 + i));
            blogPost.setTags(blogPostSet);
                HashSet<Comment> commentSet = new HashSet<>();
                Comment comment1 = new Comment();
                comment1.setFromId("sdsd");
                comment1.setComment("comment");
                comment1.setToId("dfdf");
            blogPost.setComments(commentSet);
            blogPost.setCreatedDate(new Date().getTime());

            blogPostRepository.create(blogPost);
            blogPost.setId(null);
            blogPost.setCreatedDate(blogPost.getCreatedDate() - 10);
            blogPostRepository.create(blogPost);
            blogPost.setId(null);
            blogPost.setCreatedDate(blogPost.getCreatedDate() + 20);
            blogPostRepository.create(blogPost);
        }

        model.addAttribute("message", numberOfPosts + " test blogPosts are created.");
        return "index";
    }


}
