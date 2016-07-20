package ua.com.itproekt.gup.server.api.rest.loginAndSignUp;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class LoginRestControllerTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testLoginStatus() {
        RestTemplate restTemplate = new RestTemplate();
        String                     url = "http://localhost:9090/api/login";
        String             requestJson = "{\"email\":\"sss2@gmail.com\",\"password\":\"123456\"}";
        HttpHeaders            headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String>       entity = new HttpEntity<String>(requestJson,headers);

        ResponseEntity<String> actual = restTemplate.exchange(url, HttpMethod.POST, entity, String.class, 100);
        String               expected = "{\"id\":\"575697a53880f94fe2ced184\",\"idSeoWord\":null,\"email\":null,\"password\":null,\"mainPhoneNumber\":null,\"username\":\"NEO\",\"imgId\":\"577530314c8eb310cacffc49\",\"birthDate\":null,\"contact\":{\"member\":false,\"naceId\":null,\"type\":\"INDIVIDUAL\",\"position\":\"\",\"companyName\":\"\",\"aboutUs\":\"\",\"skypeUserName\":\"\",\"linkToWebSite\":\"\",\"contactEmails\":[],\"contactPhones\":[],\"socNetLink\":{}},\"contactList\":[\"572368bffb644cbdbcf3cc1c\",\"575697a53880f94fe2ced184\"],\"userProfile\":{\"usreou\":null,\"bankCode\":null,\"vatNumber\":null,\"beneficiaryBank\":null,\"beneficiaryAccount\":null,\"legalEntityLocation\":null,\"idAddFile\":null},\"priofficeSets\":null,\"point\":0,\"unreadMessages\":0,\"profileRating\":[],\"confirmModerator\":null,\"userRoles\":[\"ROLE_USER\"],\"createdDate\":null,\"lastLoginDate\":null}";
//        System.out.println("testLoginStatus:----------------------------------------" + expected + "----------------------------------------");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testLoginResponse() {
        RestTemplate restTemplate = new RestTemplate();
        String                url = "http://localhost:9090/api/login";
        String        requestJson = "{\"email\":\"sss2@gmail.com\",\"password\":\"123456\"}";
        HttpHeaders       headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);

        String             actual = restTemplate.postForObject(url, entity, String.class);
        String           expected = "<200 OK,{\"id\":\"575697a53880f94fe2ced184\",\"idSeoWord\":null,\"email\":null,\"password\":null,\"mainPhoneNumber\":null,\"username\":\"NEO\",\"imgId\":\"577530314c8eb310cacffc49\",\"birthDate\":null,\"contact\":{\"member\":false,\"naceId\":null,\"type\":\"INDIVIDUAL\",\"position\":\"\",\"companyName\":\"\",\"aboutUs\":\"\",\"skypeUserName\":\"\",\"linkToWebSite\":\"\",\"contactEmails\":[],\"contactPhones\":[],\"socNetLink\":{}},\"contactList\":[\"572368bffb644cbdbcf3cc1c\",\"575697a53880f94fe2ced184\"],\"userProfile\":{\"usreou\":null,\"bankCode\":null,\"vatNumber\":null,\"beneficiaryBank\":null,\"beneficiaryAccount\":null,\"legalEntityLocation\":null,\"idAddFile\":null},\"priofficeSets\":null,\"point\":0,\"unreadMessages\":0,\"profileRating\":[],\"confirmModerator\":null,\"userRoles\":[\"ROLE_USER\"],\"createdDate\":null,\"lastLoginDate\":null},{Server=[Apache-Coyote/1.1], Set-Cookie=[authToken=74d78be6-822e-45aa-8c30-a5f733a7ad2d; Expires=Wed, 20-Jul-2016 16:01:50 GMT; Path=/, refreshToken=da1646dc-a065-42f1-a881-42a958065a4d; Expires=Fri, 19-Aug-2016 15:51:50 GMT; Path=/], Content-Type=[application/json;charset=UTF-8], Transfer-Encoding=[chunked], Date=[Wed, 20 Jul 2016 15:51:49 GMT]}>";
//        System.out.println("testLoginResponse:----------------------------------------" + expected + "----------------------------------------");
        Assert.assertEquals(expected, actual);
    }

//    @Test
//    public void testLogoutStatus() {
//        RestTemplate restTemplate = new RestTemplate();
//        String                     url = "http://localhost:9090/api/logout";
//        String             requestJson = null;
//        HttpHeaders            headers = new HttpHeaders();
//
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<String>       entity = new HttpEntity<String>(requestJson,headers);
//        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class, 100);
//
//        System.out.println("testLogoutStatus:----------------------------------------" + response + "----------------------------------------");
////        Assert.assertEquals(response, HttpStatus.OK);
//    }

}
