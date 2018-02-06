package ua.com.gup.notify.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;

@Configuration
public class NotifyWebConfig {

    private final Logger log = LoggerFactory.getLogger(NotifyWebConfig.class);

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate template = new RestTemplate();
        template.setInterceptors(Arrays.asList(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                log.info("request: " + request.getURI().toString());
                log.info("request body: " + new String(body));
                return execution.execute(request, body);
            }
        }));
        return template;
    }
}
