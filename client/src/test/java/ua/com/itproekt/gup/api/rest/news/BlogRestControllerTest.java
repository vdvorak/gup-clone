//package ua.com.itproekt.gup.api.rest.news;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.FilterChainProxy;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//import ua.com.itproekt.gup.api.rest.util.Util;
//import ua.com.itproekt.gup.dao.news.BlogRepository;
//import ua.com.itproekt.gup.dao.profile.ProfileRepository;
//import ua.com.itproekt.gup.model.news.Blog;
//
//import static org.hamcrest.core.Is.is;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations =
//        {"classpath*:test-applicationContext.xml"})
//@WebAppConfiguration
//@Ignore
//public class BlogRestControllerTest {
//    private static final String BASIC_URL = "/api/rest/blogService";
//
//    @Autowired
//    BlogRepository blogRepository;
//
//    @Autowired
//    ProfileRepository profileRepository;
//
//    @Autowired
//    private FilterChainProxy springSecurityFilterChain;
//
//
//    @Autowired
//    WebApplicationContext wac;
//
//    private MockMvc mockMvc;
//
//    @Before
//    public void setUp() throws Exception {
//        Util.createTestProfile(Util.USER_EMAIL, profileRepository);
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
//                .addFilter(springSecurityFilterChain)
//                .build();
//    }
//
//    @After
//    public void tearDown() {
//        SecurityContextHolder.clearContext();
//    }
//
//    @Test
//    public void testGetBlogById() throws Exception {
//        String url = BASIC_URL + "/blog/id/";
//
//        Blog blog = new Blog();
//        blogRepository.createBlog(blog);
//
//        this.mockMvc.perform(post(url + blog.getId() + "/read")
//                .contentType(Util.contentType))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(blog.getId())));
//
//        this.mockMvc.perform(post(url + "---"  + "/read")
//                .contentType(Util.contentType))
//                .andExpect(status().isNotFound());
//
//    }
//
//
//    @Test
//    public void tesCreateBlog() throws Exception {
//        String url = BASIC_URL + "/blog/create";
//
//        Blog blog = new Blog();
//        String blogJson = Util.ow.writeValueAsString(blog);
//
//        this.mockMvc.perform(post(url)
//                .contentType(Util.contentType)
//                .content(blogJson))
//                .andExpect(status().isUnauthorized());
//
//        this.mockMvc.perform(post(url)
//                .with(user(Util.loggedUser))
//                .contentType(Util.contentType)
//                .content(blogJson))
//                .andExpect(status().isCreated());
//
//    }
//
//    @Test
//    public void testEditBlog() throws Exception {
//        String url = BASIC_URL + "/blog/edit";
//
//        Blog blog = new Blog();
//        blog.setId(null);
//        String blogJson = Util.ow.writeValueAsString(blog);
//
//        this.mockMvc.perform(post(url)
//                .contentType(Util.contentType)
//                .content(blogJson))
//                .andExpect(status().isBadRequest());
//
//        blogRepository.createBlog(blog);
//        blogJson = Util.ow.writeValueAsString(blog);
//        this.mockMvc.perform(post(url)
//                .contentType(Util.contentType)
//                .content(blogJson))
//                .andExpect(status().isOk());
//
//        blog.setId("---");
//        blogJson = Util.ow.writeValueAsString(blog);
//        this.mockMvc.perform(post(url)
//                .contentType(Util.contentType)
//                .content(blogJson))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void testDeleteBlog() throws Exception {
//        String url = BASIC_URL + "/blog/id/";
//
//        this.mockMvc.perform(post(url + "---" + "/delete")
//                .contentType(Util.contentType))
//                .andExpect(status().isNotFound());
//
//        Blog blog = new Blog();
//        blogRepository.createBlog(blog);
//        this.mockMvc.perform(post(url + blog.getId() + "/delete")
//                .contentType(Util.contentType))
//                .andExpect(status().isNoContent());
//
//    }
//}