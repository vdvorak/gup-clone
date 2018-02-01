package ua.com.gup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import ua.com.gup.config.*;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableAutoConfiguration
@EnableResourceServer
public class OAuthServerGupApplication {
    @PostConstruct
    void initialize() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
    public static void main(String[] args) {
        SpringApplication.run(new Object[]{WebConfig.class
                , OAuthRootConfig.class
                , WebSecurityConfig.class
                , SwaggerConfig.class
                , MailConfig.class
                , MongoConfig.class
                , OAuth2AuthorizationServerConfig.class
//                , OAuthRestTemplatesConfig.class
                , SchedulerConfig.class
                , OAuthServerGupApplication.class
        }, args);
    }
}
