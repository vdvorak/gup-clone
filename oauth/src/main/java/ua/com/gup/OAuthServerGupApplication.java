package ua.com.gup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import ua.com.gup.config.*;

@EnableAutoConfiguration
@EnableResourceServer
public class OAuthServerGupApplication {
    public static void main(String[] args) {
        SpringApplication.run(new Object[]{WebConfig.class
                , RootConfig.class
                , WebSecurityConfig.class
                , SwaggerConfig.class
                , MailConfig.class
                , MongoConfig.class
                , OAuth2AuthorizationServerConfig.class
                , OAuthServerGupApplication.class
        }, args);
    }
}
