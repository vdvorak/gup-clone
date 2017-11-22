package ua.com.gup.rent.config;

import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import ua.com.gup.rent.config.converter.JSR310DateConverters;
import ua.com.gup.rent.repository.abstracted.generic.GenericRepository;

import java.util.Arrays;

@Configuration
@ComponentScan(basePackageClasses = GenericRepository.class)
public class RentMongoConfig {

    @Autowired
    private Environment e;

    @Bean
    public MongoClientURI mongo() throws Exception {
        MongoClientURI mongoClient = new MongoClientURI(e.getRequiredProperty("mongo.db.uri"));
        return mongoClient;
    }

    @Bean
    public MongoDbFactory mongoDbFactory(MongoClientURI mongo) throws Exception {
        MongoDbFactory factory = new SimpleMongoDbFactory(mongo);
        return factory;
    }

    @Bean
    public MongoConverter mongoConverter(MongoDbFactory mongoDbFactory) throws Exception {
        MappingContext mappingContext = new MongoMappingContext();
        MappingMongoConverter mappingMongoConverter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory), mappingContext);
        mappingMongoConverter.setCustomConversions(new CustomConversions(Arrays.asList(
                new JSR310DateConverters.DateToZonedDateTimeConverter(),
                new JSR310DateConverters.ZonedDateTimeToDateConverter()
        )));
        return mappingMongoConverter;
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory, MongoConverter mongoConverter) throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory, mongoConverter);
        return mongoTemplate;
    }

}
