package ua.com.gup.storage.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.com.gup.storage.endpoint.ImageEndpoint;

@Configuration
@ComponentScan(basePackageClasses = ImageEndpoint.class)
public class WebConfig {
}
