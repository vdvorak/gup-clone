package ua.com.gup.search.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.com.gup.search.endpoint.SearchOffersEndpoint;

@Configuration
@ComponentScan(basePackageClasses = SearchOffersEndpoint.class)
public class WebConfig {
}
