package ua.com.itproekt.gup.server.api.profiles;

import org.apache.http.impl.client.HttpClients;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import ua.com.itproekt.gup.model.profiles.UserRole;
import ua.com.itproekt.gup.server.api.rest.profiles.dto.ProfileInfo;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ProfileRestControllerTest {

    private RestTemplate       restTemplate;
    private final String           urlLogin = "http://localhost:9090/api/oauth/login";
    private final String          urlLogout = "http://localhost:9090/api/oauth/logout";
    private final String urlLoggedInProfile = "http://localhost:9090/api/rest/profilesService/profile/read/loggedInProfile";
    private final String        requestJson = "{\"email\":\"sss2@gmail.com\",\"password\":\"123456\"}";

    @Before
    public void setUp() {
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory( HttpClients.createDefault() );
        restTemplate = new RestTemplate( requestFactory );
    }

    @After
    public void tearDown() {
        restTemplate = null;
    }

    /**
     * test Logged-In Profile-Response
     */
    @Test
    public void testLoggedInProfileResponse() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        restTemplate.postForObject(urlLogin, new HttpEntity<>(requestJson, headers), ProfileInfo.class);

        ProfileInfo              actual = restTemplate.getForObject(urlLoggedInProfile, ProfileInfo.class);
        String               expectedId = "575697a53880f94fe2ced184";
        String         expectedUsername = "NEO";
        Set<UserRole> expectedUserRoles = new HashSet<>();
        expectedUserRoles.add(UserRole.ROLE_USER);
        restTemplate.exchange(urlLogout, HttpMethod.GET, new HttpEntity<>("", headers), String.class);

        assertThat(actual.getProfile().getId(), equalTo(expectedId));
        assertThat(actual.getProfile().getUsername(), equalTo(expectedUsername));
        assertThat(actual.getProfile().getUserRoles(), equalTo(expectedUserRoles));
    }

    /**
     * test Logged-In Profile-Status
     */
    @Test
    public void testLoggedInProfileStatus() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        restTemplate.postForObject(urlLogin, new HttpEntity<>(requestJson, headers), ProfileInfo.class);

        ResponseEntity<ProfileInfo> actual = restTemplate.exchange(urlLoggedInProfile, HttpMethod.GET, new HttpEntity<>("", headers), ProfileInfo.class);
        restTemplate.exchange(urlLogout, HttpMethod.GET, new HttpEntity<>("", headers), String.class);

        assertThat(actual.getStatusCode(), equalTo(HttpStatus.OK));
    }
}
