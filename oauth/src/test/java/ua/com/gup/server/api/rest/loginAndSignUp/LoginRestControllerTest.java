package ua.com.gup.server.api.rest.loginAndSignUp;

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
import ua.com.gup.server.api.rest.LoginRestController;
import ua.com.gup.server.config.TestConfig;
import ua.com.gup.service.profile.ProfilesService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@WebAppConfiguration
public class LoginRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProfilesService profilesService;
    @InjectMocks
    private LoginRestController loginRestController;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(loginRestController)
                .build();
    }

    @Test
    public void registeredUserAlreadyExists() throws Exception {
        String requestBody = "{\"email\":\"website@corp.gup.ua\",\"password\":\"123Qwe\"}";
        when(profilesService.profileExistsWithEmail("website@corp.gup.ua")).thenReturn(true);
        mockMvc.perform(
                post("/api/oauth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isConflict());

    }

//    @Test
//    public void registerConfirm() throws Exception {
//    }
//
//    @Test
//    public void login() throws Exception {
//    }

}