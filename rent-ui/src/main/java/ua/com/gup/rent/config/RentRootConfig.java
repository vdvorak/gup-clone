package ua.com.gup.rent.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.com.gup.rent.mapper.RentObjectMapper;
import ua.com.gup.rent.service.abstracted.generic.GenericService;
import ua.com.gup.rent.validator.category.CategoryDTOValidator;

@Configuration
@ComponentScan(basePackageClasses = {GenericService.class, RentObjectMapper.class, CategoryDTOValidator.class})
public class RentRootConfig {
}
