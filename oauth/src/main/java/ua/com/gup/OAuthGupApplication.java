package ua.com.gup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import ua.com.gup.config.*;
import ua.com.gup.config.swagger.SwaggerConfig;

@EnableAutoConfiguration
public class OAuthGupApplication {
    public static void main(String[] args) {
        SpringApplication.run(new Object[]{WebConfig.class
                , RootConfig.class
                , SecurityConfig.class
                , SwaggerConfig.class
                , MailConfig.class
                , MongoConfig.class
                , OAuthGupApplication.class
        }, args);
    }
}
