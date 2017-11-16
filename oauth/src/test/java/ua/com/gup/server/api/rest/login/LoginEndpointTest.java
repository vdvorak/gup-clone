package ua.com.gup.server.api.rest.login;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ua.com.gup.server.config.TestConfig;

//import ua.com.gup.service.profile.ProfilesService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@WebAppConfiguration
public class LoginEndpointTest {

    private MockMvc mockMvc;

//    @Mock
//    private ProfilesService profilesService;
//    @InjectMocks
//    private LoginEndpoint loginEndpoint;
//
//
//    @Before
//    public void init() {
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders
//                .standaloneSetup(loginEndpoint)
//                .build();
//    }
//
//    @Test
//    public void registeredUserAlreadyExists() throws Exception {
//        String requestBody = "{\"email\":\"website@corp.gup.ua\",\"password\":\"123Qwe\"}";
//        when(profilesService.profileExistsWithEmail("website@corp.gup.ua")).thenReturn(true);
//        mockMvc.perform(
//                post("/api/oauth/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(requestBody))
//                .andExpect(status().isConflict());
//
//    }

}