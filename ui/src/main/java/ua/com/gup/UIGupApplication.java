package ua.com.gup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import ua.com.gup.common.config.CommonRestTemplatesConfig;
import ua.com.gup.config.*;
import ua.com.gup.config.swagger.SwaggerConfig;

@EnableAutoConfiguration
public class UIGupApplication {
    public static void main(String[] args) {
        SpringApplication.run(new Object[]{SaleWebConfig.class
                , RootConfig.class
                , SaleRestTemplateConfig.class
                , SaleWebSecurityConfig.class
                , WebSecurityConfig.class
                , SwaggerConfig.class
                , MailConfig.class
                , SchedulerConfig.class
                , MongoConfig.class
                , SaleSessionConfig.class
                , UIGupApplication.class
        }, args);
    }

}
