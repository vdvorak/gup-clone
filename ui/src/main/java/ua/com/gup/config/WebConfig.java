//package ua.com.gup.config;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
//import java.util.List;
//
//@Configuration
//public class WebConfig extends WebMvcConfigurationSupport {
//
//    @Bean
//    public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
//        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
//        jsonConverter.setObjectMapper(objectMapper());
//        return jsonConverter;
//    }
//
//    @Override
//    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
//        super.addArgumentResolvers(argumentResolvers);
//    }
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        super.addDefaultHttpMessageConverters(converters);
//        for (int i = 0; i < converters.size(); i++) {
//            if (converters.get(i).getClass().equals(MappingJackson2HttpMessageConverter.class)) {
//                converters.set(i, customJackson2HttpMessageConverter());
//            }
//        }
//    }
//
//    @Bean
//    public ObjectMapper objectMapper() {
//        return jackson2ObjectMapperBuilder().build();
//    }
//
//    private Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
//        return Jackson2ObjectMapperBuilder.json()
//                .serializationInclusion(JsonInclude.Include.NON_NULL) // Don’t include null values
//                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS)
//                .modules(new JavaTimeModule());
//    }
//}
