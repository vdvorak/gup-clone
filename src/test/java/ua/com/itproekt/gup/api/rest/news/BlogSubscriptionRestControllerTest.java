package ua.com.itproekt.gup.api.rest.news;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.com.itproekt.gup.api.rest.util.Util;
import ua.com.itproekt.gup.dao.news.BlogRepository;
import ua.com.itproekt.gup.dao.news.newsfeed.BlogNewsFeedRepository;
import ua.com.itproekt.gup.dao.profile.ProfileRepository;
import ua.com.itproekt.gup.model.news.Blog;
import ua.com.itproekt.gup.model.news.newsfeed.BlogSubscription;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
        {"classpath*:test-applicationContext.xml"})
@WebAppConfiguration
@Ignore
public class BlogSubscriptionRestControllerTest {

    private static final String BASIC_URL = "/api/rest/blogService";

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    BlogNewsFeedRepository blogNewsFeedRepository;

    @Autowired
    BlogRepository blogRepository;

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
    public void tearDown() throws Exception {
        mongoTemplate.dropCollection(BlogSubscription.class);
        SecurityContextHolder.clearContext();
    }

    @Test
    public void testGetBlogSubscription() throws Exception {
        String url = BASIC_URL + "/blogSubscription/read";

        this.mockMvc.perform(post(url)
                .contentType(Util.contentType))
                .andExpect(status().isUnauthorized());

        this.mockMvc.perform(post(url)
                .with(user(Util.loggedUser))
                .contentType(Util.contentType))
                .andExpect(status().isNotFound());

        BlogSubscription blogSubscription = new BlogSubscription();
        blogSubscription.setUserId(profileRepository.findByEmail(Util.principal.getName()).getId());
        blogNewsFeedRepository.createBlogSubscription(blogSubscription);

        this.mockMvc.perform(post(url)
                .with(user(Util.loggedUser))
                .contentType(Util.contentType))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddSubscription() throws Exception {
        Blog blog = new Blog();
        blogRepository.createBlog(blog);
        String url = BASIC_URL + "/blogSubscription/blogId/" + blog.getId() + "/update/subscription/add";

        this.mockMvc.perform(post(url)
                .contentType(Util.contentType))
                .andExpect(status().isUnauthorized());

        this.mockMvc.perform(post(url)
                .with(user(Util.loggedUser))
                .contentType(Util.contentType))
                .andExpect(status().isCreated());
    }

    @Test
    public void testDeleteSubscription() throws Exception {
        Blog blog = new Blog();
        blogRepository.createBlog(blog);
        blogNewsFeedRepository.addSubscription(profileRepository.findByEmail(Util.principal.getName()).getId(),
                blog.getId());
        String url = BASIC_URL + "/blogSubscription/blogId/";

        this.mockMvc.perform(post(url + blog.getId() + "/update/subscription/delete")
                .contentType(Util.contentType))
                .andExpect(status().isUnauthorized());

        this.mockMvc.perform(post(url + "***" + "/update/subscription/delete")
                .with(user(Util.loggedUser))
                .contentType(Util.contentType))
                .andExpect(status().isNotFound());

//        this.mockMvc.perform(post(url + blog.getId())
//                .principal(Util.principal)
//                .contentType(Util.contentType))
//                .andExpect(status().isNoContent());
    }
}