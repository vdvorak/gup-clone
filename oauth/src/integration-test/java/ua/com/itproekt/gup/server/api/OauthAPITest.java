package ua.com.itproekt.gup.server.api;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;

public class OauthAPITest {

    @Test
    public void testLogin() {
        RequestSpecBuilder         builder = new RequestSpecBuilder();
        builder.setContentType("application/json; charset=UTF-8");
        RequestSpecification specification = builder.build();

        String message = given(specification)
                .when()
                .post("http://localhost:9090/api/loginTest")
                .then()
                .statusCode(200)
                .extract()
                .path("oauth");

        Assert.assertEquals(message, "LoginTest");
    }

    @Test
    public void testLogout() {
        RequestSpecBuilder         builder = new RequestSpecBuilder();
        builder.setContentType("application/json; charset=UTF-8");
        RequestSpecification specification = builder.build();

        String message = given(specification)
                .when()
                .post("http://localhost:9090/api/logoutTest")
                .then()
                .statusCode(200)
                .extract()
                .path("oauth");

        Assert.assertEquals(message, "LogoutTest");

////        RestTemplate restTemplate = new RestTemplate();
////        String uri = "http://localhost:9090/api/hello";
////        HttpHeaders headers = new HttpHeaders();
////        headers.setContentType(MediaType.APPLICATION_JSON);
////        HttpEntity<String> entity = new HttpEntity<String>("Hello World!", headers);
////        ResponseEntity<Person> personEntity = restTemplate.exchange(uri, HttpMethod.POST, entity, Person.class, 100);
////        System.out.println("ID:"+personEntity.getBody().getId());
////        System.out.println("Name:"+personEntity.getBody().getName());
////        System.out.println("Village:"+personEntity.getBody().getAddress().getVillage());
//
//
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:9090/api/hello";
//        String requestJson = "{\"email\":\"sss2@gmail.com\",\"password\":\"123456\"}";
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
//        String answer = restTemplate.postForObject(url, entity, String.class);
//        System.err.println(answer);
    }
}
