package ua.com.gup.rent.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import ua.com.gup.rent.api.RentOfferEndpoint;
import ua.com.gup.rent.component.SpringApplicationContextProvider;
import ua.com.gup.rent.error.GlobalExceptionHandler;

import javax.servlet.Filter;

@Configuration
@ComponentScan(basePackageClasses = {SpringApplicationContextProvider.class, RentOfferEndpoint.class, GlobalExceptionHandler.class},
        basePackages = {"ua.com.gup.common"})
public class RentWebConfig {


    @Bean
    public FilterRegistrationBean characterEncodingFilterRegistration() {
        return registerFilter(new CharacterEncodingFilter("UTF-8", true), "CharacterEncodingFilter", 1, "/*");
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
