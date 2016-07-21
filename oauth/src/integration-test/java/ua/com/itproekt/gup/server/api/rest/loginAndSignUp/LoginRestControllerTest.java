package ua.com.itproekt.gup.server.api.rest.loginAndSignUp;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

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

        String             actual = restTemplate.postForObject(urlLogin, new HttpEntity<>(requestJson, headers), String.class);
        String           expected = "{\"id\":\"575697a53880f94fe2ced184\",\"idSeoWord\":null,\"email\":null,\"password\":null,\"mainPhoneNumber\":null,\"username\":\"NEO\",\"imgId\":\"577530314c8eb310cacffc49\",\"birthDate\":null,\"contact\":{\"member\":false,\"naceId\":null,\"type\":\"INDIVIDUAL\",\"position\":\"\",\"companyName\":\"\",\"aboutUs\":\"\",\"skypeUserName\":\"\",\"linkToWebSite\":\"\",\"contactEmails\":[],\"contactPhones\":[],\"socNetLink\":{}},\"contactList\":[\"572368bffb644cbdbcf3cc1c\",\"575697a53880f94fe2ced184\"],\"userProfile\":{\"usreou\":null,\"bankCode\":null,\"vatNumber\":null,\"beneficiaryBank\":null,\"beneficiaryAccount\":null,\"legalEntityLocation\":null,\"idAddFile\":null},\"priofficeSets\":null,\"point\":0,\"unreadMessages\":0,\"profileRating\":[],\"confirmModerator\":null,\"userRoles\":[\"ROLE_USER\"],\"createdDate\":null,\"lastLoginDate\":null,\"orderAddressList\":null}";

        Assert.assertEquals(expected, actual.toString()); // Assert.assertEquals(expected.substring(8, 250), actual.toString().substring(8, 250));
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
