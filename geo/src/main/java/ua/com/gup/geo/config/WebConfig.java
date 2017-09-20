package ua.com.gup.geo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ua.com.gup.geo.controller.GeoController;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = GeoController.class)
public class WebConfig {
}
