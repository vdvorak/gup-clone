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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.com.itproekt.gup.api.rest.util.Util;
import ua.com.itproekt.gup.dao.projectsAndInvestments.project.ProjectRepository;
import ua.com.itproekt.gup.model.profiles.UserRole;
import ua.com.itproekt.gup.model.projectsAndInvestments.project.ModerationStatus;
import ua.com.itproekt.gup.model.projectsAndInvestments.project.Project;
import ua.com.itproekt.gup.model.projectsAndInvestments.project.ProjectFilterOptions;
import ua.com.itproekt.gup.service.profile.ProfilesService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
        {"classpath*:applicationContext.xml"})
@WebAppConfiguration

public class ProjectsRestControllerTest {
    private static final String BASIC_URL = "/api/rest/projectsAndInvestmentsService";

    @Autowired
    ProjectRepository projectRepository;

//    @Autowired
//    ProfileRepository profileRepository;

    @Autowired
    ProfilesService profilesService;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;


    @Autowired
    WebApplicationContext wac;

    private MockMvc mockMvc;


    private Project projectConstructor(ModerationStatus moderationStatus) {
        Project project = new Project();
        project.setModerationStatus(moderationStatus);
        project.setAuthorId("123140dv382dfkjn");
        projectRepository.create(project);
        return project;
    }

    private String projectFilterOptionsConstructor() throws Exception {
        ProjectFilterOptions projectFO = new ProjectFilterOptions();
        projectFO.setSkip(0);
        projectFO.setLimit(1);
        String projectFOJson = null;
        projectFOJson = Util.ow.writeValueAsString(projectFO);

        return projectFOJson;
    }

    private MvcResult mvcResultConstructor(UserRole userRole) throws Exception {
        switch (userRole) {
            case ROLE_ADMIN:
                return this.mockMvc.perform(post("/login").param("email", Util.ADMIN_USER_EMAIL).param("password", Util.GENERAL_USER_PASSWORD)).andReturn();
            case ROLE_MODERATOR:
                return this.mockMvc.perform(post("/login").param("email", Util.MODERATOR_USER_EMAIL).param("password", Util.GENERAL_USER_PASSWORD)).andReturn();
            case ROLE_SUPPORT:
                return this.mockMvc.perform(post("/login").param("email", Util.SUPPORT_USER_EMAIL).param("password", Util.GENERAL_USER_PASSWORD)).andReturn();
            case ROLE_USER:
                return this.mockMvc.perform(post("/login").param("email", Util.USER_EMAIL).param("password", Util.GENERAL_USER_PASSWORD)).andReturn();
        }
        return this.mockMvc.perform(post("/login").param("email", Util.USER_EMAIL).param("password", Util.GENERAL_USER_PASSWORD)).andReturn();
    }


    @Before
    public void setUp() throws Exception {
        Util.createTestUsers(profilesService);


        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .addFilter(springSecurityFilterChain)
                .build();
    }

    @After
    public void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    public void testGetProjectById_shouldReturn200_WithProjectModerationStatusComplete_forRoleAdmin() throws Exception {
        String url = BASIC_URL + "/" + "project/id/";
        Project project = projectConstructor(ModerationStatus.COMPLETE);
        MvcResult result = mvcResultConstructor(UserRole.ROLE_ADMIN);
        this.mockMvc.perform(get(url + project.getId() + "/read")
                .header("Authorization", "Bearer " + result.getResponse().getCookie("authToken").getValue())
                .contentType(Util.contentType))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetProjectById_shouldReturn200_WithProjectModerationStatusFail_forRoleAdmin() throws Exception {
        String url = BASIC_URL + "/project/id/";
        Project project = projectConstructor(ModerationStatus.FAIL);
        MvcResult result = mvcResultConstructor(UserRole.ROLE_ADMIN);
        this.mockMvc.perform(get(url + project.getId() + "/read")
                .header("Authorization", "Bearer " + result.getResponse().getCookie("authToken").getValue())
                .contentType(Util.contentType))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetProjectById_shouldReturn200_WithProjectModerationStatusNo_forRoleAdmin() throws Exception {
        String url = BASIC_URL + "/project/id/";
        Project project = projectConstructor(ModerationStatus.NO);
        MvcResult result = mvcResultConstructor(UserRole.ROLE_ADMIN);
        this.mockMvc.perform(get(url + project.getId() + "/read")
                .header("Authorization", "Bearer " + result.getResponse().getCookie("authToken").getValue())
                .contentType(Util.contentType))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetProjectById_shouldReturn404_forNonExistProject_forRoleAdmin() throws Exception {
        String url = BASIC_URL + "/project/id/";
        MvcResult result = mvcResultConstructor(UserRole.ROLE_ADMIN);
        this.mockMvc.perform(get(url + "----" + "/read")
                .header("Authorization", "Bearer " + result.getResponse().getCookie("authToken").getValue())
                .contentType(Util.contentType))
                .andExpect(status().isNotFound());
    }
//--------------------------------------------------------------------------------------------------------------

    @Test
    public void testListOfAllInvestors() throws Exception {
        String url = BASIC_URL + "/project/read/all";
        String projectFOJson = projectFilterOptionsConstructor();
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
//                .with(user(Util.loggedUser))
                .contentType(Util.contentType)
                .content(projectJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void testEditProject() throws Exception {
        String url = BASIC_URL + "/project/edit";

        Project project = new Project();
        project.setId(null);
        project.setAuthorId(profilesService.findProfileByEmail(Util.USER_EMAIL).getId());
        project.setProjectName("****");
        String projectJson = Util.ow.writeValueAsString(project);

        this.mockMvc.perform(post(url)
                .contentType(Util.contentType)
                .content(projectJson))
                .andExpect(status().isUnauthorized());

        this.mockMvc.perform(post(url)
//                .with(user(Util.loggedUser))
                .contentType(Util.contentType)
                .content(projectJson))
                .andExpect(status().isBadRequest());

        projectRepository.create(project);
        project.setProjectName("ddddd");
        projectJson = Util.ow.writeValueAsString(project);

        this.mockMvc.perform(post(url)
//                .with(user(Util.loggedUser))
                .contentType(Util.contentType)
                .content(projectJson))
                .andExpect(status().isOk());

        project.setId("---");
        projectJson = Util.ow.writeValueAsString(project);
        this.mockMvc.perform(post(url)
//                .with(user(Util.loggedUser))
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