package ua.com.gup.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.TestPropertySource;

@Configuration
//@TestPropertySource(locations = {"classpath:properties/*.properties"})
@ImportResource({"classpath:appContext.xml"})
public class TestConfig {
}
