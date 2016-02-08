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
//import ua.com.itproekt.gup.model.projectsAndInvestments.investment.InvestorPost;
//import ua.com.itproekt.gup.model.projectsAndInvestments.investment.InvestorPostFilterOptions;
//import ua.com.itproekt.gup.service.projectsAndInvestments.investment.InvestorService;
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
//public class InvestorPostRestControllerTest {
//    private static final String BASIC_URL = "/api/rest/projectsAndInvestmentsService";
//
//    @Autowired
//    InvestorService investorService;
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
//    public void testGetInvestorById() throws Exception {
//        String url = BASIC_URL + "/investorPost/id/";
//
//        InvestorPost investorPost = new InvestorPost();
//        investorService.create(investorPost);
//
//        this.mockMvc.perform(post(url + investorPost.getId() + "/read")
//                .contentType(Util.contentType))
//                .andExpect(status().isOk());
//
//        this.mockMvc.perform(post(url + "---" + "/read")
//                .contentType(Util.contentType))
//                .andExpect(status().isNotFound());
//
//    }
//
//    @Test
//    public void testListOfAllInvestors() throws Exception {
//        String url = BASIC_URL + "/investorPost/read/all";
//
//        InvestorPostFilterOptions investorFO = new InvestorPostFilterOptions();
//        investorFO.setSkip(0);
//        investorFO.setLimit(1);
//        String investorFOJson = Util.ow.writeValueAsString(investorFO);
//
//        this.mockMvc.perform(post(url)
//                .content(investorFOJson)
//                .contentType(Util.contentType))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testCreateInvestorPost() throws Exception {
//        String url = BASIC_URL + "/investorPost/create";
//
//        InvestorPost investorPost = new InvestorPost();
//
//        String investorPostJson = Util.ow.writeValueAsString(investorPost);
//
//        this.mockMvc.perform(post(url)
//                .with(user(Util.loggedUser))
//                .contentType(Util.contentType)
//                .content(investorPostJson))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    public void testEditInvestorPost() throws Exception {
//        String url = BASIC_URL + "/investorPost/edit";
//
//        InvestorPost investorPost = new InvestorPost();
//        investorPost.setId(null);
//        String investorJson = Util.ow.writeValueAsString(investorPost);
//
//        this.mockMvc.perform(post(url)
//                .contentType(Util.contentType)
//                .content(investorJson))
//                .andExpect(status().isBadRequest());
//
//        investorService.create(investorPost);
//        investorPost.setuId("***");
//        investorJson = Util.ow.writeValueAsString(investorPost);
//        this.mockMvc.perform(post(url)
//                .contentType(Util.contentType)
//                .content(investorJson))
//                .andExpect(status().isOk());
//
//        investorPost.setId("---");
//        investorJson = Util.ow.writeValueAsString(investorPost);
//        this.mockMvc.perform(post(url)
//                .contentType(Util.contentType)
//                .content(investorJson))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void testDeleteInvestorPost() throws Exception {
//        String url = BASIC_URL + "/investorPost/id/";
//
//        this.mockMvc.perform(post(url + "---" + "/delete")
//                .contentType(Util.contentType))
//                .andExpect(status().isNotFound());
//
//        InvestorPost investorPost = new InvestorPost();
//        investorService.create(investorPost);
//        this.mockMvc.perform(post(url + investorPost.getId() + "/delete")
//                .contentType(Util.contentType))
//                .andExpect(status().isNoContent());
//    }
//}