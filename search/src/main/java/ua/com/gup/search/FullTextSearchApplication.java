package ua.com.gup.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.com.gup.search.config.CLApp;
import ua.com.gup.search.config.RootConfig;
import ua.com.gup.search.config.WebConfig;
import ua.com.gup.search.repository.ESCategoryRepository;
import ua.com.gup.search.repository.ESOfferRepository;

@SpringBootApplication
public class FullTextSearchApplication {



    public static void main(String[] args) {
        SpringApplication.run(new Object[]{RootConfig.class, WebConfig.class, FullTextSearchApplication.class, CLApp.class}, args);


    }
}
