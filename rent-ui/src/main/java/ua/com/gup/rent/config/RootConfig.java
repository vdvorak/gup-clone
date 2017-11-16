package ua.com.gup.rent.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = {"ua.com.gup.rent.service", "ua.com.gup.rent.mapper","ua.com.gup.rent.validator"})
public class RootConfig {

}
