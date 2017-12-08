package ua.com.gup.rent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import ua.com.gup.rent.config.*;

@EnableAutoConfiguration
public class RentUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(new Object[]{
                RentWebConfig.class,
                RentSwaggerConfig.class,
                RentMongoConfig.class,
                RentRootConfig.class,
                RentMailConfig.class,
                RentWebSecurityConfig.class,
                RentMethodSecurityConfig.class,
                RentRestTemplatesConfig.class,
                RentSessionConfig.class,
                RentUiApplication.class}, args);
    }


}
