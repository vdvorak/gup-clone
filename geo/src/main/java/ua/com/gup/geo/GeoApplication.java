package ua.com.gup.geo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import ua.com.gup.geo.config.MongoConfig;
import ua.com.gup.geo.config.RootConfig;
import ua.com.gup.geo.config.WebConfig;

@EnableAutoConfiguration
public class GeoApplication {

    public static void main(String[] args) {
        SpringApplication.run(new Object[]{RootConfig.class, WebConfig.class, MongoConfig.class, GeoApplication.class}, args);
    }
}
