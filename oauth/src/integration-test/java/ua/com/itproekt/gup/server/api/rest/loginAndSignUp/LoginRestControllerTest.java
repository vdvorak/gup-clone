package ua.com.itproekt.gup.server.api.rest.loginAndSignUp;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.UserRole;

import java.util.*;

public class LoginRestControllerTest {

    private RestTemplate restTemplate;
    private final String     urlLogin = "http://localhost:9090/api/oauth/login";
    private final String    urlLogout = "http://localhost:9090/api/oauth/logout";
    private final String  requestJson = "{\"email\":\"sss2@gmail.com\",\"password\":\"123456\"}";

    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
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

        ResponseEntity<String> actual = restTemplate.exchange(urlLogin, HttpMethod.POST, new HttpEntity<>(requestJson,headers), String.class, 100);

        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    /**
     * test Login-Response
     */
    @Test
    public void testLoginResponse() {
        HttpHeaders       headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Profile                  actual = restTemplate.postForObject(urlLogin, new HttpEntity<>(requestJson, headers), Profile.class);
        String               expectedId = "575697a53880f94fe2ced184";
        String         expectedUsername = "NEO";
        Set<UserRole> expectedUserRoles = new HashSet<>();
        expectedUserRoles.add(UserRole.ROLE_USER);

        Assert.assertEquals(expectedId, actual.getId());
        Assert.assertEquals(expectedUsername, actual.getUsername());
        Assert.assertEquals(expectedUserRoles, actual.getUserRoles());
    }

//    /**
//     * test Logout-Status
//     */
//    @Test
//    public void testLogoutStatus() {
//        HttpHeaders headers = new HttpHeaders();
////        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> entity = new HttpEntity<>(requestJson,headers);
//
////        ResponseEntity<String> actual = restTemplate.exchange(urlLogout, HttpMethod.GET, entity, String.class);
//        ResponseEntity<String> actual = restTemplate.getForEntity(urlLogout, String.class);
//
//        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
//    }

}
