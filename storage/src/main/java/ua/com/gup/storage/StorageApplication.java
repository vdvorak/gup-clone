package ua.com.gup.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import ua.com.gup.storage.config.RootConfig;
import ua.com.gup.storage.config.S3StorageConfig;
import ua.com.gup.storage.config.WebConfig;

@EnableAutoConfiguration
public class StorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(new Object[]{WebConfig.class, RootConfig.class, S3StorageConfig.class, StorageApplication.class}, args);
    }
}
