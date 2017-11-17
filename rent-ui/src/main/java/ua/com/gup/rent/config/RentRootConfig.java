package ua.com.gup.rent.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import ua.com.gup.rent.mapper.RentObjectMapper;
import ua.com.gup.rent.repository.category.CategoryRepository;
import ua.com.gup.rent.repository.category.attribute.CategoryAttributeRepository;
import ua.com.gup.rent.repository.rent.RentObjectRepository;
import ua.com.gup.rent.repository.sequence.SequenceRepository;
import ua.com.gup.rent.service.abstracted.generic.GenericService;
import ua.com.gup.rent.service.category.CategoryService;
import ua.com.gup.rent.service.category.attribute.CategoryAttributeService;
import ua.com.gup.rent.service.rent.RentObjectService;
import ua.com.gup.rent.service.sequence.SequenceService;
import ua.com.gup.rent.validator.category.CategoryDTOValidator;
import ua.com.gup.rent.validator.category.attribute.CategoryAttributeDTOValidator;

@Configuration
@ComponentScans({
        @ComponentScan(basePackageClasses = {GenericService.class, RentObjectMapper.class}),
        @ComponentScan(basePackageClasses = {RentObjectService.class, CategoryService.class, CategoryAttributeService.class}),
        @ComponentScan(basePackageClasses = {SequenceService.class, SequenceRepository.class}),
        @ComponentScan(basePackageClasses = {CategoryRepository.class, CategoryAttributeRepository.class, RentObjectRepository.class}),
        @ComponentScan(basePackageClasses = {CategoryDTOValidator.class, CategoryAttributeDTOValidator.class})
})
public class RentRootConfig {
}
