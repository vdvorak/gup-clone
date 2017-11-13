package ua.com.gup.rent.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@ImportResource(value = {"classpath:security/spring-security.xml"})
public class RentWebSecurityConfig extends WebSecurityConfigurerAdapter {


}
