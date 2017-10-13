package ua.com.gup.config.mongo;

import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ua.com.gup.config.mongo.converter.JSR310DateConverters;
import ua.com.gup.config.mongo.converter.OAuth2AuthenticationReadConverter;

import java.util.ArrayList;
import java.util.List;

@Configuration
//@EnableMongoAuditing
@EnableMongoRepositories(basePackages = "ua.com.gup")
@PropertySource("classpath:properties/mongo.properties")
public class MongoDBConfiguration {

    @Value("${mongo.remote.db.uri}")
    private String mongoClientURI;

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(new MongoClientURI(mongoClientURI));
    }

    @Bean
    public MongoTemplate getMongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
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

    @Bean
    public MappingMongoConverter mongoConverter() throws Exception {
        MongoMappingContext mappingContext = new MongoMappingContext();
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory());
        MappingMongoConverter mongoConverter = new MappingMongoConverter(dbRefResolver, mappingContext);
        mongoConverter.setCustomConversions(customConversions());
        mongoConverter.afterPropertiesSet();
        return mongoConverter;
    }
    /*@Bean
    public AuditorAware<AuditableUser> myAuditorProvider() {
        return new AuditorAwareImpl();
    }*/

}