package ua.com.gup.config.mongo;

import com.mongodb.Mongo;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

//@Configuration
//@EnableMongoAuditing
//@EnableMongoRepositories(basePackages = "ua.com.gup")
//@PropertySource("classpath:properties/mongo.properties")
public class MongoDBConfiguration extends AbstractMongoConfiguration {
    private final String mappingBasePackage = "ua.com.gup";

    @Override
    protected String getDatabaseName() {
        return null;
    }

    @Override
    public Mongo mongo() throws Exception {
        return null;
    }

    @Override
    protected String getMappingBasePackage() {
        return mappingBasePackage;
    }

/*    @Value("${mongo.remote.db.uri}")
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
    }*/
    /*@Bean
    public AuditorAware<AuditableUser> myAuditorProvider() {
        return new AuditorAwareImpl();
    }*/

}