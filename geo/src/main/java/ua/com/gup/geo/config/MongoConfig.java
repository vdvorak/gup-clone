package ua.com.gup.geo.config;

import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.io.IOException;
import java.net.UnknownHostException;

@Configuration
@ComponentScan(basePackages = "ua.com.gup.geo.mongo.repository.impl")
public class MongoConfig {
    @Autowired
    private Environment environment;

    @Bean
    public MongoDbFactory mongoDbFactory() throws UnknownHostException {
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(new MongoClientURI(environment.getRequiredProperty("mongo.remote.db.uri")));

        return mongoDbFactory;
    }

    @Bean
    public MongoOperations mongoTemplate() throws IOException {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }

}
