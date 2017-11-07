package ua.com.gup.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.com.gup.search.config.RootConfig;
import ua.com.gup.search.config.WebConfig;

@SpringBootApplication
public class FullTextSearchApplication {


    public static void main(String[] args) {
        SpringApplication.run(new Object[]{RootConfig.class, WebConfig.class, FullTextSearchApplication.class}, args);


    }
}
