package ua.com.gup.rent.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.com.gup.rent.mapper.RentObjectMapper;
import ua.com.gup.rent.service.GenericService;
import ua.com.gup.rent.validator.CategoryDTOValidator;

@Configuration
@ComponentScan(basePackageClasses = {GenericService.class, RentObjectMapper.class, CategoryDTOValidator.class})
public class RentRootConfig {
}
