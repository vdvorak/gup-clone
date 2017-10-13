package ua.com.gup.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ua.com.gup.config.mongo.converter.JSR310DateConverters;
import ua.com.gup.config.mongo.converter.OAuth2AuthenticationReadConverter;

import java.util.ArrayList;
import java.util.List;

@Configuration
//@EnableMongoAuditing
@EnableMongoRepositories(basePackages = "ua.com.gup")
@PropertySource("classpath:pro")
public class MongoDBConfiguration {

    @Bean
    public MongoDbFactory getMongoDbFactory() throws Exception {
        new MongoClientURI();
        return new SimpleMongoDbFactory();
    }

    @Bean
    public MongoTemplate getMongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(getMongoDbFactory());
        return mongoTemplate;
    }

    @Bean
    public CustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(JSR310DateConverters.DateToZonedDateTimeConverter.INSTANCE);
        converters.add(JSR310DateConverters.ZonedDateTimeToDateConverter.INSTANCE);
        converters.add(new OAuth2AuthenticationReadConverter());
        return new CustomConversions(converters);
    }

    /*@Bean
    public AuditorAware<AuditableUser> myAuditorProvider() {
        return new AuditorAwareImpl();
    }*/

}
