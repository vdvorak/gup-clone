package ua.com.gup.rent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import ua.com.gup.rent.config.*;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableAutoConfiguration
public class RentUiApplication {

    @PostConstruct
    void initialize() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

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
                RentSchedulerConfig.class,
                RentUiApplication.class}, args);
    }
}