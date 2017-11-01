package ua.com.gup.config.http.client;

import com.google.common.base.Throwables;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.client.HttpAsyncClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.AsyncClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfiguration {
    private static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 100;
    private static final int DEFAULT_MAX_CONNECTIONS_PER_ROUTE = 5;
    private static final int DEFAULT_READ_TIMEOUT_MILLISECONDS = (60 * 1000);

    // ################################################### SYNC #########################################################################
    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory(HttpClient httpClient) {
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpComponentsClientHttpRequestFactory.setHttpClient(httpClient);
        return httpComponentsClientHttpRequestFactory;
    }

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory clientHttpRequestFactory) {
        return new RestTemplate(clientHttpRequestFactory);
    }

    @Bean
    public CloseableHttpClient httpClient() {
        try {
            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
            connectionManager.setMaxTotal(DEFAULT_MAX_TOTAL_CONNECTIONS);
            connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_CONNECTIONS_PER_ROUTE);
            connectionManager.setMaxPerRoute(new HttpRoute(new HttpHost("payment.dev.gup.ua")), 20);

            RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout(DEFAULT_READ_TIMEOUT_MILLISECONDS)
                    .setConnectionRequestTimeout(DEFAULT_READ_TIMEOUT_MILLISECONDS)
                    .setSocketTimeout(DEFAULT_READ_TIMEOUT_MILLISECONDS)
                    .build();

            CloseableHttpClient defaultHttpClient = HttpClientBuilder.create()
                    .setConnectionManager(connectionManager)
                    .setDefaultRequestConfig(config).build();
            return defaultHttpClient;
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }

    // ################################################### ASYNC  ####################################################
    @Bean
    public AsyncClientHttpRequestFactory asyncHttpRequestFactory(HttpAsyncClient asyncHttpClient) {
        return new HttpComponentsAsyncClientHttpRequestFactory(asyncHttpClient);
    }

    @Bean
    public AsyncRestTemplate asyncRestTemplate(AsyncClientHttpRequestFactory asyncClientHttpRequestFactory, RestTemplate restTemplate) {
        return new AsyncRestTemplate(asyncClientHttpRequestFactory, restTemplate);
    }

    @Bean
    public CloseableHttpAsyncClient asyncHttpClient() {
        try {
            PoolingNHttpClientConnectionManager connectionManager =
                    new PoolingNHttpClientConnectionManager(new DefaultConnectingIOReactor(IOReactorConfig.DEFAULT));

            connectionManager.setMaxTotal(DEFAULT_MAX_TOTAL_CONNECTIONS);
            connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_CONNECTIONS_PER_ROUTE);
            connectionManager.setMaxPerRoute(new HttpRoute(new HttpHost("payment.dev.gup.ua")), 20);
            RequestConfig config = RequestConfig.custom().setConnectTimeout(DEFAULT_READ_TIMEOUT_MILLISECONDS).build();
            CloseableHttpAsyncClient httpclient = HttpAsyncClientBuilder.create().setConnectionManager(connectionManager).setDefaultRequestConfig(config).build();
            return httpclient;
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }
}