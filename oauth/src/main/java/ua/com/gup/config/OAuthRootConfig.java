package ua.com.gup.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "ua.com.gup.service")
public class OAuthRootConfig {
}
