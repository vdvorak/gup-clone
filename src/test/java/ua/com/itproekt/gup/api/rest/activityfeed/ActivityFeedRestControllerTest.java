package ua.com.itproekt.gup.api.rest.activityfeed;

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
import ua.com.itproekt.gup.model.activityfeed.Event;
import ua.com.itproekt.gup.model.activityfeed.EventFilterOptions;
import ua.com.itproekt.gup.model.activityfeed.EventType;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
        {"classpath*:test-applicationContext.xml"})
@WebAppConfiguration
@Ignore
public class ActivityFeedRestControllerTest {
    private static final String BASIC_URL = "/api/rest/activityFeed";

    @Autowired
    ActivityFeedService activityFeedService;

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

    @Ignore
    @Test
    public void testGetUserEvents() throws Exception {
        String url = BASIC_URL + "/event/read/all";

        activityFeedService.createEvent(new Event(Util.USER_ID, EventType.BLOG_POST_COMMENT, null, null));

        EventFilterOptions eventFO = new EventFilterOptions();
        eventFO.setSkip(0);
        eventFO.setLimit(1);

        String eventFOJson = Util.ow.writeValueAsString(eventFO);
        this.mockMvc.perform(post(url)
                .with(user(Util.loggedUser))
                .contentType(Util.contentType)
                .content(eventFOJson))
                .andExpect(status().isOk());

//        activityFeedService.deleteEventsWihOptions(eventFO);
//        activityFeedService.createEvent(new Event("*****ID*****", EventType.BLOG_POST_COMMENT, null, null));
//
//        eventFOJson = Util.ow.writeValueAsString(eventFO);
//        this.mockMvc.perform(post(url)
//                .with(user(Util.loggedUser))
//                .contentType(Util.contentType)
//                .content(eventFOJson))
//                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteEvent() throws Exception {
        Event event = new Event(Util.USER_ID, EventType.BLOG_POST_COMMENT, null, null);
        activityFeedService.createEvent(event);

        this.mockMvc.perform(post(BASIC_URL + "/event/id/" + event.getId() + "/delete")
                .with(user(Util.loggedUser))
                .contentType(Util.contentType))
                .andExpect(status().isNoContent());
    }
}