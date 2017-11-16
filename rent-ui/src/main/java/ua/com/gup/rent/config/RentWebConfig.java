package ua.com.gup.rent.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import ua.com.gup.rent.api.RentEndpoint;
import ua.com.gup.rent.config.filter.OAuthFilter;

import javax.servlet.Filter;

@Configuration
@ComponentScan(basePackageClasses = RentEndpoint.class)
public class RentWebConfig {

    @Bean
    public OAuthFilter oAuthFilter() {
        return new OAuthFilter();
    }

    @Bean
    public FilterRegistrationBean characterEncodingFilterRegistration() {
        return registerFilter(new CharacterEncodingFilter("UTF-8", true), "CharacterEncodingFilter", 1, "/*");
    }

    @Bean
    public FilterRegistrationBean oauthFilterRegistration(@Autowired OAuthFilter oAuthFilter) {
        return registerFilter(oAuthFilter, "OAuthFilter", 2, "/*");
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
