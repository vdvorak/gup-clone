package ua.com.gup.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@ImportResource(value = {"classpath:spring/security/spring-security.xml"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

}

