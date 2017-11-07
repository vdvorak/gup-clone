package ua.com.gup.storage.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.com.gup.storage.repository.ImageRepository;
import ua.com.gup.storage.service.ImageService;

@Configuration
@ComponentScan(basePackageClasses = {ImageService.class})
public class RootConfig {
}
