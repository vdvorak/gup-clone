package ua.com.gup.rent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import ua.com.gup.rent.config.*;
import ua.com.gup.rent.repository.GenericRepository;

@EnableAutoConfiguration
@ComponentScan(basePackageClasses = GenericRepository.class) //  костыль, пока не вынесен SSO
public class RentUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(new Object[]{RentWebConfig.class,
                RentSwaggerConfig.class,
                RentRootConfig.class,
                ua.com.gup.config.MongoConfig.class, // костыль,
                ua.com.gup.config.RootConfig.class, // пока не вынесен SSO
                RentMailConfig.class,
                RentWebSecurityConfig.class,
//                RentMethodSecurityConfig.class,
                RentUiApplication.class}, args);
    }


}
