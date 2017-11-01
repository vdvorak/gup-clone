package ua.com.gup.search.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.com.gup.search.repository.ESOfferRepository;

@Configuration
@ComponentScan(basePackageClasses = ESOfferRepository.class)
public class ElasticSearchConfig {

    @Value("${elasticsearch.host}")
    private String esHost;

    @Value("${elasticsearch.port}")
    private int esPort;

    @Value("${elasticsearch.clustername}")
    private String esClusterName;

    @Bean
    public RestHighLevelClient devClient() throws Exception {
        RestClient client = RestClient.builder(new HttpHost(esHost, esPort)).build();
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(client);
        return restHighLevelClient;
    }


}
