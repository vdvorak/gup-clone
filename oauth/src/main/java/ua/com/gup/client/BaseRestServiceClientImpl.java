package ua.com.itproekt.gup.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class BaseRestServiceClientImpl implements BaseRestServiceClient {

    protected RestTemplate restTemplate;
    private String serviceUrl;

    @Override
    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }
    @Override
    public String getServiceUrl() {
        return serviceUrl;
    }


    public BaseRestServiceClientImpl() { }

    public BaseRestServiceClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        GupResponseErrorHandler errorHandler = new GupResponseErrorHandler();
        restTemplate.setErrorHandler(errorHandler);
    }


    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        GupResponseErrorHandler errorHandler = new GupResponseErrorHandler();
        restTemplate.setErrorHandler(errorHandler);
    }

}
