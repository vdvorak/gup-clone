package ua.com.itproekt.gup.server.api.rest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class LoginTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

//    @Test
//    public void test_successful_response() {
//        RestTemplate       restTemplate = new RestTemplate();
//        ResponseEntity<String> response = restTemplate.getForEntity(
//                "https://data.sparkfun.com/streams/dZ4EVmE8yGCRGx5XRX1W.json",
//                String.class);
//
//        if (HttpStatus.OK == response.getStatusCode()) {
//            System.out.println(response);
//        } else {
//            // log error, retry or ?
//        }
//    }

//    @Test
//    public void testLoginResponse() {
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://93.73.109.38:8083/login";
//        String requestJson = "{\"email\":\"sss2@gmail.com\",\"password\":\"123456\"}";
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
//        String answer = restTemplate.postForObject(url, entity, String.class);
//        System.out.println(answer);
//
////        ResponseEntity<String> response = restTemplate.postForObject(url, entity, String.class);
////
////        if (HttpStatus.OK == response.getStatusCode()) {
////            System.out.println(response);
////        } else {
////            // log error, retry or ?
////        }
//    }

}
