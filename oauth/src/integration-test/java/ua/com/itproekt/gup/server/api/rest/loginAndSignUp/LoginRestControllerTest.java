package ua.com.itproekt.gup.server.api.rest.loginAndSignUp;

import org.apache.http.impl.client.HttpClients;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.UserRole;

import java.util.*;

import static com.jayway.restassured.RestAssured.get;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.describedAs;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;

public class LoginRestControllerTest {

    private RestTemplate restTemplate;
    private final String     urlLogin = "http://localhost:9090/api/oauth/login";
    private final String    urlLogout = "http://localhost:9090/api/oauth/logout";
    private final String  requestJson = "{\"email\":\"sss2@gmail.com\",\"password\":\"123456\"}";

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
     * test Login-Status
     */
    @Test
    public void testLoginStatus() {
        HttpHeaders            headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<Profile> actual = restTemplate.exchange(urlLogin, HttpMethod.POST, new HttpEntity<>(requestJson,headers), Profile.class, 100);

        assertThat(actual.getStatusCode(), equalTo(HttpStatus.OK));
    }

    /**
     * test Login-Response
     */
    @Test
    public void testLoginResponse() {
        HttpHeaders             headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Profile                  actual = restTemplate.postForObject(urlLogin, new HttpEntity<>(requestJson, headers), Profile.class);
        String               expectedId = "575697a53880f94fe2ced184";
        String         expectedUsername = "NEO";
        Set<UserRole> expectedUserRoles = new HashSet<>();
        expectedUserRoles.add(UserRole.ROLE_USER);

        assertThat(actual.getId(), equalTo(expectedId));
        assertThat(actual.getUsername(), equalTo(expectedUsername));
        assertThat(actual.getUserRoles(), equalTo(expectedUserRoles));
    }

    /**
     * test Logout-Status
     */
    @Test
    public void testLogoutStatus() {
        HttpHeaders           headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        restTemplate.postForObject(urlLogin, new HttpEntity<>(requestJson, headers), Profile.class);
        ResponseEntity<String> actual = restTemplate.exchange(urlLogout, HttpMethod.GET, new HttpEntity<>("", headers), String.class);

        assertThat(actual.getStatusCode(), equalTo(HttpStatus.OK));
    }

}
