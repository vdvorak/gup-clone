package ua.com.gup.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ua.com.gup.config.filter.OAuthFilter;

import javax.servlet.Filter;

@Configuration
@ComponentScan(value = "ua.com.gup.api.oauth2")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public FilterRegistrationBean characterEncodingFilterRegistration() {
        return registerFilter(new CharacterEncodingFilter("UTF-8", true), "CharacterEncodingFilter", 1, "/*");
    }

    @Bean
    public FilterRegistrationBean oauthFilterRegistration() {
        return registerFilter(new OAuthFilter(), "OAuthFilter", 2, "/*");
    }

    @Bean
    public FilterRegistrationBean springSecurityFilterRegistration() {
        return registerFilter(new DelegatingFilterProxy(), "springSecurityFilterChain", 3, "/*");
    }


    private FilterRegistrationBean registerFilter(Filter filter, String name, int order, String... urlPatterns) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.addUrlPatterns(urlPatterns);
        registration.setName(name);
        registration.setOrder(order);
        return registration;

    }
}
