package ua.com.itproekt.gup.api.rest.news;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.com.itproekt.gup.api.rest.util.Util;
import ua.com.itproekt.gup.dao.news.BlogPostRepository;
import ua.com.itproekt.gup.dao.profile.ProfileRepository;
import ua.com.itproekt.gup.model.news.BlogPost;
import ua.com.itproekt.gup.model.news.Comment;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
        {"classpath*:test-applicationContext.xml"})
@WebAppConfiguration
@Ignore

public class BlogPostCommentRestControllerTest {

    private static final String BASIC_URL = "/api/rest/blogService";

    @Autowired
    BlogPostRepository blogPostRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        Util.createTestProfile(Util.USER_EMAIL, profileRepository);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .addFilter(springSecurityFilterChain)
                .build();
    }

    @After
    public void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    public void testGetCommentById() throws Exception {
        BlogPost blogPost = new BlogPost();
        Set<Comment> comments = new HashSet<>();
        Comment comment = new Comment();
        comments.add(comment);
        blogPost.setComments(comments);
        blogPostRepository.create(blogPost);

        String url = BASIC_URL + "/blogPost/id/" + blogPost.getId() + "/comment/";

        this.mockMvc.perform(post(url + "---" + "/read")
                .contentType(Util.contentType))
                .andExpect(status().isNotFound());

        this.mockMvc.perform(post(url + comment.getcId() + "/read")
                .contentType(Util.contentType))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateComment() throws Exception {
        BlogPost blogPost = new BlogPost();
        Set<Comment> comments = new HashSet<>();
        Comment comment = new Comment();
        comments.add(comment);
        blogPost.setComments(comments);
        blogPostRepository.create(blogPost);

        String url = BASIC_URL + "/blogPost/id/" + blogPost.getId() + "/comment/create";

        String blogPostJson = Util.ow.writeValueAsString(blogPost);

        this.mockMvc.perform(post(url)
                .contentType(Util.contentType)
                .content(blogPostJson))
                .andExpect(status().isUnauthorized());

        this.mockMvc.perform(post(url)
                .with(user(Util.loggedUser))
                .contentType(Util.contentType)
                .content(blogPostJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void testDeleteComment() throws Exception {
        BlogPost blogPost = new BlogPost();
        Set<Comment> comments = new HashSet<>();
        Comment comment = new Comment();
        comments.add(comment);
        blogPost.setComments(comments);
        blogPostRepository.create(blogPost);

        String url = BASIC_URL + "/blogPost/id/" + blogPost.getId() + "/comment/id/";


        this.mockMvc.perform(post(url + "---" + "/delete")
                .contentType(Util.contentType))
                .andExpect(status().isNotFound());

        this.mockMvc.perform(post(url + comment.getcId() + "/delete")
                .contentType(Util.contentType))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testLikeComment() throws Exception {
        BlogPost blogPost = new BlogPost();
        Set<Comment> comments = new HashSet<>();
        Comment comment = new Comment();
        comments.add(comment);
        blogPost.setComments(comments);
        blogPostRepository.create(blogPost);

        String url = BASIC_URL + "/blogPost/id/" + blogPost.getId() + "/comment/id/";

        this.mockMvc.perform(post(url + "***" + "/like")
                .contentType(Util.contentType))
                .andExpect(status().isUnauthorized());

        this.mockMvc.perform(post(url + "***" + "/like")
                .with(user(Util.loggedUser))
                .contentType(Util.contentType))
                .andExpect(status().isNotFound());

        this.mockMvc.perform(post(url + comment.getcId() + "/like")
                .with(user(Util.loggedUser))
                .contentType(Util.contentType))
                .andExpect(status().isOk());
    }
}