package ua.com.gup.rent.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "ua.com.gup.rent.service",
        "ua.com.gup.rent.mapper",
        "ua.com.gup.rent.validator",
        "ua.com.gup.rent.event.listener",
        "ua.com.gup.common.service"})
public class RentRootConfig {
}
