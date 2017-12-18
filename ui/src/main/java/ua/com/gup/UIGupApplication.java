package ua.com.gup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import ua.com.gup.config.*;
import ua.com.gup.config.swagger.SwaggerConfig;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableAutoConfiguration
public class UIGupApplication {

    @PostConstruct
    void initialize() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(new Object[]{SaleWebConfig.class
                , RootConfig.class
                , SaleRestTemplateConfig.class
                , SaleWebSecurityConfig.class
                , SaleMethodSecurityConfig.class
                , SwaggerConfig.class
                , MailConfig.class
                , SchedulerConfig.class
                , MongoConfig.class
                , SaleSessionConfig.class
                , UIGupApplication.class
        }, args);
    }


}
