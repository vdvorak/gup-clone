package ua.com.gup.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket apiGPU() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("REST_API_GUP")
                .select()
                .apis(RequestHandlerSelectors.basePackage("ua.com.gup"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "UI REST API",
                "Some custom description of API.",
                "1.0",
                "Terms of service",
                new Contact("GUP", "gup.com.ua", "gpu@dev.gup.ua"),
                "License of API",
                "API license URL");
        return apiInfo;
    }
}
