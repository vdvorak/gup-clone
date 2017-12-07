package ua.com.gup.rent.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;

@RestController
public class RentUaPayPaymentEndpoint {

    @Autowired
    private Environment e;
    private UriComponentsBuilder uriComponentsBuilder;

    public RentUaPayPaymentEndpoint() {

    }

    @PostConstruct
    public void initialize() {
        uriComponentsBuilder = UriComponentsBuilder.newInstance().scheme(e.getProperty("uapay.int-service.scheme"))
                .host(e.getProperty("uapay.int-service.host")).port(e.getProperty("uapay.int-service.port")).path("uapay.int-service.base-path");
    }

    private String createSession() {

        return "";
    }

}
