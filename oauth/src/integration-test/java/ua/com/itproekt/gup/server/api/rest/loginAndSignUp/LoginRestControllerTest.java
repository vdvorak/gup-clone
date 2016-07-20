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
        String                     url = "http://localhost:9090/login";
        String             requestJson = "{\"email\":\"sss2@gmail.com\",\"password\":\"123456\"}";
        HttpHeaders            headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String>       entity = new HttpEntity<String>(requestJson,headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class, 100);

        Assert.assertEquals(response, HttpStatus.OK);
    }

    @Test
    public void testLoginResponse() {
        RestTemplate restTemplate = new RestTemplate();
        String                url = "http://localhost:9090/login";
        String        requestJson = "{\"email\":\"sss2@gmail.com\",\"password\":\"123456\"}";
        HttpHeaders       headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        String             answer = restTemplate.postForObject(url, entity, String.class);

        System.out.println(answer);
        Assert.assertEquals(answer, "");
    }

    @Test
    public void testLogoutStatus() {
        RestTemplate restTemplate = new RestTemplate();
        String                     url = "http://localhost:9090/logout";
        String             requestJson = "";
        HttpHeaders            headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String>       entity = new HttpEntity<String>(requestJson,headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class, 100);

        Assert.assertEquals(response, HttpStatus.OK);
    }

}
