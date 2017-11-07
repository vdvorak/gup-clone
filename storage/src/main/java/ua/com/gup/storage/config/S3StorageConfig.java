package ua.com.gup.storage.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.com.gup.storage.repository.ImageRepository;
import ua.com.gup.storage.repository.impl.ImageS3RepositoryImpl;

@Configuration
@ComponentScan(basePackageClasses = {ImageS3RepositoryImpl.class})
public class S3StorageConfig {

    private static final String accessKey = "3NVJUN83Y5X6B07MD3YQ";
    private static final String secretKey = "Upu9uKzY7kve12kFo5ZRaQvqeSsPnkP7UqApxllk";
    private static final String BUCKET_NAME = "images";

    @Bean
    public ClientConfiguration clientConfiguration() {
        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setConnectionTimeout(5000);
        clientConfig.setMaxConnections(100);
        clientConfig.withRequestTimeout(5000);
        clientConfig.setProtocol(Protocol.HTTPS);
        return clientConfig;
    }

    @Bean
    public AmazonS3 amazonS3(ClientConfiguration clientConfiguration) {

        AmazonS3 conn = AmazonS3ClientBuilder
                .standard()
                .withClientConfiguration(clientConfiguration)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("s3.dev.gup.ua", Regions.DEFAULT_REGION.getName()))
                .build();
        return conn;
    }

    @Bean
    public Bucket imagesBucket(AmazonS3 amazonS3) {
        return amazonS3.createBucket(BUCKET_NAME);
    }


}
