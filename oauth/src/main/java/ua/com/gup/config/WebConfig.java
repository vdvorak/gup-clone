package ua.com.gup.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ua.com.gup.config.filter.OAuthFilter;

@Configuration
@ComponentScan(value = "ua.com.gup.api.oauth2")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public FilterRegistrationBean characterEncodingFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CharacterEncodingFilter("UTF-8", true));
        registration.addUrlPatterns("/*");
        registration.setName("CharacterEncodingFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public FilterRegistrationBean oauthFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new OAuthFilter());
        registration.addUrlPatterns("/*");
        registration.setName("OAuthFilter");
        registration.setOrder(2);
        return registration;
    }

    @Bean
    public FilterRegistrationBean springSecurityFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new DelegatingFilterProxy());
        registration.addUrlPatterns("/*");
        registration.setName("springSecurityFilterChain");
        registration.setOrder(3);
        return registration;
    }
}
