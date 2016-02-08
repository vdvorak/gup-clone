package ua.com.itproekt.gup.api.rest.tender;

import org.junit.After;
import org.junit.Before;
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
import ua.com.itproekt.gup.dao.tender.TenderRepository;
import ua.com.itproekt.gup.model.tender.Tender;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
 * Created by Fairy on 08.02.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
        {"classpath*:applicationContext.xml"})
@WebAppConfiguration

public class TenderRestControllerTest1 {
    private static final String BASIC_URL = "/api/rest/tenderService";

    @Autowired
    TenderRepository tenderRepository;

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
    public void testGetTenderById() throws Exception {
        String url = BASIC_URL + "/tender/id/";

        Tender tender = new Tender();
        tenderRepository.createTender(tender);

        this.mockMvc.perform(post(url + tender.getId() + "/read")
                .contentType(Util.contentType))
                .andExpect(status().isOk());

        this.mockMvc.perform(post(url + "---" + "/read")
                .contentType(Util.contentType))
                .andExpect(status().isNotFound());

    }

}