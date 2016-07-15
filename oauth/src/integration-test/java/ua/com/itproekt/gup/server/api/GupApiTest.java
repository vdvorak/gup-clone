package ua.com.itproekt.gup.server.api;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;
import static com.jayway.restassured.RestAssured.*;

public class GupApiTest {

    @Test
    public void indexApiTest() {
        RequestSpecBuilder       builder = new RequestSpecBuilder();
        builder.setContentType("application/json; charset=UTF-8");
        RequestSpecification requestSpec = builder.build();

        String gupMessage = given(requestSpec)
                .when()
                .get("http://localhost:9090/api/index")
                .then().statusCode(200)
                .extract().path("oauth");

        Assert.assertEquals(gupMessage, "Index");
    }

    @Test
    public void helloApiTest() {
        RequestSpecBuilder       builder = new RequestSpecBuilder();
        builder.setContentType("application/json; charset=UTF-8");
        RequestSpecification requestSpec = builder.build();

        String gupMessage = given(requestSpec)
                .when()
                .get("http://localhost:9090/api/hello")
                .then().statusCode(200)
                .extract()
                .path("oauth");

        Assert.assertEquals(gupMessage, "Hello!!");
    }

}
