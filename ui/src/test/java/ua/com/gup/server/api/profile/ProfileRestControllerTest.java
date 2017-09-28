package ua.com.gup.server.api.profile;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.com.gup.service.profile.ProfilesService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml", "classpath:dispatcherServlet.xml"})
@WebAppConfiguration
public class ProfileRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProfilesService profilesService;
    @InjectMocks
    private ProfileRestController profileRestController;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(profileRestController)
                .build();
    }

    @Test
    public void registeredUserAlreadyExists() throws Exception {
        when(profilesService.profileExists("1")).thenReturn(false);
        mockMvc.perform(post("/api/rest/profilesService/profile/id/1/myContactList/toggle")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }


}