//package ua.com.itproekt.gup.api.rest.projectsAndInvestments;
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
//import ua.com.itproekt.gup.dao.profile.ProfileRepository;
//import ua.com.itproekt.gup.model.projectsAndInvestments.project.Comment;
//import ua.com.itproekt.gup.model.projectsAndInvestments.project.Project;
//import ua.com.itproekt.gup.service.projectsAndInvestments.project.ProjectService;
//
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations =
//        {"classpath*:test-applicationContext.xml"})
//@WebAppConfiguration
//@Ignore
//public class ProjectCommentsRestControllerTest {
//    private static final String BASIC_URL = "/api/rest/projectsAndInvestmentsService";
//
//    @Autowired
//    ProjectService projectService;
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
//    public void getCommentById() throws Exception {
//        Project project = new Project();
//
//        Comment comment = new Comment();
//        comment.setComment("sdsdds");
//
//        projectService.create(project);
//        projectService.addComment(project.getId(), comment);
//
//        String url = BASIC_URL + "/project/id/" + project.getId() + "/comment/id/";
//
//        this.mockMvc.perform(post(url + "---" + "/read")
//                .contentType(Util.contentType))
//                .andExpect(status().isNotFound());
//
//        this.mockMvc.perform(post(url + comment.getcId() + "/read")
//                .contentType(Util.contentType))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testCreateComment() throws Exception {
//        Project project = new Project();
//
//        Comment comment = new Comment();
//        comment.setComment("sfdfsd");
//        comment.setCreatedDateEqualsToCurrentDate();
//
//        projectService.create(project);
//
//        String url = BASIC_URL + "/project/id/" + project.getId() + "/comment/create";
//
//        String commentJson = Util.ow.writeValueAsString(comment);
//        this.mockMvc.perform(post(url)
//                .contentType(Util.contentType)
//                .content(commentJson))
//                .andExpect(status().isUnauthorized());
//
//        this.mockMvc.perform(post(url)
//                .with(user(Util.loggedUser))
//                .contentType(Util.contentType)
//                .content(commentJson))
//                .andExpect(status().isConflict());
//
//        projectService.vote(project.getId(), Util.USER_ID, 0);
//        this.mockMvc.perform(post(url)
//                .with(user(Util.loggedUser))
//                .contentType(Util.contentType)
//                .content(commentJson))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    public void testDeleteComment() throws Exception {
//        Project project = new Project();
//        Comment comment = new Comment();
//        comment.setComment("dfdf");
//
//        projectService.create(project);
//        projectService.addComment(project.getId(), comment);
//
//        String url = BASIC_URL + "/project/id/" + project.getId() + "/comment/id/";
//
//        this.mockMvc.perform(post(url + "---" + "/delete")
//                .contentType(Util.contentType))
//                .andExpect(status().isNotFound());
//
//        this.mockMvc.perform(post(url + comment.getcId() + "/delete")
//                .contentType(Util.contentType))
//                .andExpect(status().isNoContent());
//    }
//}