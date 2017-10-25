package ua.com.gup.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = {"ua.com.gup.service", "ua.com.gup.mapper"})
public class RootConfig {

}
