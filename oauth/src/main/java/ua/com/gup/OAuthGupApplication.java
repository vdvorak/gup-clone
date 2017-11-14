package ua.com.gup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import ua.com.gup.config.MailConfig;
import ua.com.gup.config.MongoConfig;
import ua.com.gup.config.RootConfig;
import ua.com.gup.config.WebConfig;
import ua.com.gup.config.security.SecurityConfig;
import ua.com.gup.config.swagger.SwaggerConfig;

@EnableAutoConfiguration
//@EnableResourceServer
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
