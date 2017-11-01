package ua.com.gup.search.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.com.gup.search.service.ESSearchService;

@Configuration
@ComponentScan(basePackageClasses = ESSearchService.class)
public class RootConfig {
}
