package ua.com.gup.search.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.com.gup.search.endpoint.SearchEndpoint;

@Configuration
@ComponentScan(basePackageClasses = SearchEndpoint.class)
public class WebConfig {
}
