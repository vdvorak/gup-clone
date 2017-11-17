package ua.com.gup.rent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import ua.com.gup.rent.config.*;

@EnableAutoConfiguration
//@ComponentScan(basePackageClasses = GenericRepository.class) //  костыль, пока не вынесен SSO
public class RentUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(new Object[]{
                RentWebConfig.class,
                RentSwaggerConfig.class,
                RentRootConfig.class,
                RentMongoConfig.class,
                RentRootConfig.class,
                RentMailConfig.class,
                RentWebSecurityConfig.class,
                RentMethodSecurityConfig.class,
                RentUiApplication.class}, args);
    }


}
