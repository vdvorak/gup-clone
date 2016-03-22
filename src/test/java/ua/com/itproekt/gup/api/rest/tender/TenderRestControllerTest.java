//package ua.com.itproekt.gup.api.rest.tender;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.FilterChainProxy;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//import ua.com.itproekt.gup.api.rest.util.Util;
//import ua.com.itproekt.gup.dao.tender.TenderRepository;
//import ua.com.itproekt.gup.model.profiles.UserRole;
//import ua.com.itproekt.gup.model.tender.Tender;
//import ua.com.itproekt.gup.model.tender.TenderFilterOptions;
//import ua.com.itproekt.gup.model.tender.TenderType;
//import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
//import ua.com.itproekt.gup.service.filestorage.StorageService;
//import ua.com.itproekt.gup.service.nace.NaceService;
//import ua.com.itproekt.gup.service.profile.ProfilesService;
//import ua.com.itproekt.gup.service.tender.TenderService;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations =
//        {"classpath*:applicationContext.xml"})
//@WebAppConfiguration
//
//public class TenderRestControllerTest {
//    private static final String BASIC_URL = "/api/rest/tenderService";
//    @Autowired
//    TenderRepository tenderRepository;
//
////    @Autowired
////    ProfileRepository profileRepository;
//
//    @Autowired
//    TenderService tenderService;
//
//    @Autowired
//    ProfilesService profileService;
//
//    @Autowired
//    NaceService naceService;
//
//    @Autowired
//    StorageService storageService;
//
//    @Autowired
//    ActivityFeedService activityFeedService;
//
//    @Autowired
//    ProfilesService profilesService;
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
//
//    private Tender tenderConstructor(TenderType type) {
//        Tender tender = new Tender();
//        tender.setType(type);
//        tender.setAuthorId("123140dv382dfkjn");
//        tender.setId("iuyoiuyouiyoiuyoui");
//        tenderRepository.createTender(tender);
//        return tender;
//    }
//
//    private String tenderFilterOptionsConstructor() throws Exception {
//        TenderFilterOptions tFO = new TenderFilterOptions();
//        tFO.setSkip(0);
//        tFO.setLimit(1);
//        String tenderFOJson = null;
//        tenderFOJson = Util.ow.writeValueAsString(tFO);
//
//        return tenderFOJson;
//    }
//
//    private MvcResult mvcResultConstructor(UserRole userRole) throws Exception {
//        switch (userRole) {
//            case ROLE_ADMIN:
//                return this.mockMvc.perform(post("/login").param("email", Util.ADMIN_USER_EMAIL).param("password", Util.GENERAL_USER_PASSWORD)).andReturn();
//            case ROLE_MODERATOR:
//                return this.mockMvc.perform(post("/login").param("email", Util.MODERATOR_USER_EMAIL).param("password", Util.GENERAL_USER_PASSWORD)).andReturn();
//            case ROLE_SUPPORT:
//                return this.mockMvc.perform(post("/login").param("email", Util.SUPPORT_USER_EMAIL).param("password", Util.GENERAL_USER_PASSWORD)).andReturn();
//            case ROLE_USER:
//                return this.mockMvc.perform(post("/login").param("email", Util.USER_EMAIL).param("password", Util.GENERAL_USER_PASSWORD)).andReturn();
//        }
//        return this.mockMvc.perform(post("/login").param("email", Util.USER_EMAIL).param("password", Util.GENERAL_USER_PASSWORD)).andReturn();
//    }
//
//
//    @Before
//    public void setUp() throws Exception {
//        Util.createTestUsers(profilesService);
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
//    public void testGetTenderById_shouldReturn200_WithTenderTypeOpen_forRoleAdmin() throws Exception {
//        String url = BASIC_URL + "/tender/id/";
//        Tender tender = tenderConstructor(TenderType.OPEN);
//        MvcResult result = mvcResultConstructor(UserRole.ROLE_ADMIN);
//        this.mockMvc.perform(get(url + tender.getId() + "/read")
//                .header("Authorization", "Bearer " + result.getResponse().getCookie("authToken").getValue())
//                .contentType(Util.contentType))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testGetTenderById_shouldReturn200_WithTenderTypeClose_forRoleAdmin() throws Exception {
//        String url = BASIC_URL + "/tender/id/";
//        Tender project = tenderConstructor(TenderType.CLOSE);
//        MvcResult result = mvcResultConstructor(UserRole.ROLE_ADMIN);
//        this.mockMvc.perform(get(url + project.getId() + "/read")
//                .header("Authorization", "Bearer " + result.getResponse().getCookie("authToken").getValue())
//                .contentType(Util.contentType))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testGetTenderById_shouldReturn403_AllTenderType_forUnloggedUser() throws Exception {
//        String url = BASIC_URL + "/tender/id/";
//        Tender tenderC = tenderConstructor(TenderType.CLOSE);
//        System.out.println("pew-pew");
//        this.mockMvc.perform(post(url + tenderC.getId() + "/read")
//                .contentType(Util.contentType))
//                .andExpect(status().isForbidden());
//
//        Tender tenderO = tenderConstructor(TenderType.OPEN);
//        this.mockMvc.perform(get(url + tenderO.getId() + "/read")
//                .contentType(Util.contentType))
//                .andExpect(status().isForbidden());
//    }
////
////    @Test
////    public void testGetProjectById_shouldReturn200_WithProjectModerationStatusNo_forRoleAdmin() throws Exception {
////        String url = BASIC_URL + "/project/id/";
////        Project project = projectConstructor(ModerationStatus.NO);
////        MvcResult result = mvcResultConstructor(UserRole.ROLE_ADMIN);
////        this.mockMvc.perform(get(url + project.getId() + "/read")
////                .header("Authorization", "Bearer " + result.getResponse().getCookie("authToken").getValue())
////                .contentType(Util.contentType))
////                .andExpect(status().isOk());
////    }
////
////    @Test
////    public void testGetProjectById_shouldReturn404_forNonExistProject_forRoleAdmin() throws Exception {
////        String url = BASIC_URL + "/project/id/";
////        MvcResult result = mvcResultConstructor(UserRole.ROLE_ADMIN);
////        this.mockMvc.perform(get(url + "----" + "/read")
////                .header("Authorization", "Bearer " + result.getResponse().getCookie("authToken").getValue())
////                .contentType(Util.contentType))
////                .andExpect(status().isNotFound());
////    }
//
//
////--------------------------------------------------------------------------------------------------------------
////
////    @Test
////    public void testListOfAllInvestors() throws Exception {
////        String url = BASIC_URL + "/project/read/all";
////        String projectFOJson = projectFilterOptionsConstructor();
////        this.mockMvc.perform(post(url)
////                .content(projectFOJson)
////                .contentType(Util.contentType))
////                .andExpect(status().isOk());
////    }
////
////    @Test
////    public void testCreateProject() throws Exception {
////        String url = BASIC_URL + "/project/create";
////
////        Project project = new Project();
////        String projectJson = Util.ow.writeValueAsString(project);
////
////        this.mockMvc.perform(post(url)
////                .contentType(Util.contentType)
////                .content(projectJson))
////                .andExpect(status().isUnauthorized());
////
////        this.mockMvc.perform(post(url)
//////                .with(user(Util.loggedUser))
////                .contentType(Util.contentType)
////                .content(projectJson))
////                .andExpect(status().isCreated());
////    }
////
////    @Test
////    public void testEditProject() throws Exception {
////        String url = BASIC_URL + "/project/edit";
////
////        Project project = new Project();
////        project.setId(null);
////        project.setAuthorId(profilesService.findProfileByEmail(Util.USER_EMAIL).getId());
////        project.setTitle("****");
////        String projectJson = Util.ow.writeValueAsString(project);
////
////        this.mockMvc.perform(post(url)
////                .contentType(Util.contentType)
////                .content(projectJson))
////                .andExpect(status().isUnauthorized());
////
////        this.mockMvc.perform(post(url)
//////                .with(user(Util.loggedUser))
////                .contentType(Util.contentType)
////                .content(projectJson))
////                .andExpect(status().isBadRequest());
////
////        projectRepository.create(project);
////        project.setTitle("ddddd");
////        projectJson = Util.ow.writeValueAsString(project);
////
////        this.mockMvc.perform(post(url)
//////                .with(user(Util.loggedUser))
////                .contentType(Util.contentType)
////                .content(projectJson))
////                .andExpect(status().isOk());
////
////        project.setId("---");
////        projectJson = Util.ow.writeValueAsString(project);
////        this.mockMvc.perform(post(url)
//////                .with(user(Util.loggedUser))
////                .contentType(Util.contentType)
////                .content(projectJson))
////                .andExpect(status().isNotFound());
////    }
////
////    @Test
////    public void testDeleteProject() throws Exception {
////        String url = BASIC_URL + "/project/id/";
////
////        this.mockMvc.perform(post(url + "---" + "/delete")
////                .contentType(Util.contentType))
////                .andExpect(status().isNotFound());
////
////        Project project = new Project();
////        projectRepository.create(project);
////        this.mockMvc.perform(post(url + project.getId() + "/delete")
////                .contentType(Util.contentType))
////                .andExpect(status().isNoContent());
////    }
//}
