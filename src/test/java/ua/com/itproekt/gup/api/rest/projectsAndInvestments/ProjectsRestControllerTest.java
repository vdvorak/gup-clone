package ua.com.itproekt.gup.api.rest.projectsAndInvestments;

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
import ua.com.itproekt.gup.dao.profile.ProfileRepository;
import ua.com.itproekt.gup.dao.projectsAndInvestments.project.ProjectRepository;
import ua.com.itproekt.gup.model.projectsAndInvestments.project.Project;
import ua.com.itproekt.gup.model.projectsAndInvestments.project.ProjectFilterOptions;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
        {"classpath*:test-applicationContext.xml"})
@WebAppConfiguration
@Ignore
public class ProjectsRestControllerTest {
    private static final String BASIC_URL = "/api/rest/projectsAndInvestmentsService";

    @Autowired
    ProjectRepository projectRepository;

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
    public void testGetProjectById() throws Exception {
        String url = BASIC_URL + "/project/id/";

        Project project = new Project();
        projectRepository.create(project);

        this.mockMvc.perform(post(url + project.getId() + "/read")
                .contentType(Util.contentType))
                .andExpect(status().isOk());

        this.mockMvc.perform(post(url + "---" + "/read")
                .contentType(Util.contentType))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testListOfAllInvestors() throws Exception {
        String url = BASIC_URL + "/project/read/all";

        ProjectFilterOptions projectFO = new ProjectFilterOptions();
        projectFO.setSkip(0);
        projectFO.setLimit(1);
        String projectFOJson = Util.ow.writeValueAsString(projectFO);

        this.mockMvc.perform(post(url)
                .content(projectFOJson)
                .contentType(Util.contentType))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateProject() throws Exception {
        String url = BASIC_URL + "/project/create";

        Project project = new Project();
        String projectJson = Util.ow.writeValueAsString(project);

        this.mockMvc.perform(post(url)
                .contentType(Util.contentType)
                .content(projectJson))
                .andExpect(status().isUnauthorized());

        this.mockMvc.perform(post(url)
                .with(user(Util.loggedUser))
                .contentType(Util.contentType)
                .content(projectJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void testEditProject() throws Exception {
        String url = BASIC_URL + "/project/edit";

        Project project = new Project();
        project.setId(null);
        project.setAuthorId(profileRepository.findByEmail(Util.USER_EMAIL).getId());
        project.setProjectName("****");
        String projectJson = Util.ow.writeValueAsString(project);

        this.mockMvc.perform(post(url)
                .contentType(Util.contentType)
                .content(projectJson))
                .andExpect(status().isUnauthorized());

        this.mockMvc.perform(post(url)
                .with(user(Util.loggedUser))
                .contentType(Util.contentType)
                .content(projectJson))
                .andExpect(status().isBadRequest());

        projectRepository.create(project);
        project.setProjectName("ddddd");
        projectJson = Util.ow.writeValueAsString(project);

        this.mockMvc.perform(post(url)
                .with(user(Util.loggedUser))
                .contentType(Util.contentType)
                .content(projectJson))
                .andExpect(status().isOk());

        project.setId("---");
        projectJson = Util.ow.writeValueAsString(project);
        this.mockMvc.perform(post(url)
                .with(user(Util.loggedUser))
                .contentType(Util.contentType)
                .content(projectJson))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteProject() throws Exception {
        String url = BASIC_URL + "/project/id/";

        this.mockMvc.perform(post(url + "---" + "/delete")
                .contentType(Util.contentType))
                .andExpect(status().isNotFound());

        Project project = new Project();
        projectRepository.create(project);
        this.mockMvc.perform(post(url + project.getId() + "/delete")
                .contentType(Util.contentType))
                .andExpect(status().isNoContent());
    }
}