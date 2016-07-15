package ua.com.itproekt.gup.client.customer;

import ua.com.itproekt.gup.client.BaseRestServiceClientImpl;
import ua.com.itproekt.gup.client.GupRequestCallback;
import ua.com.itproekt.gup.client.GupResponseExtractor;
import ua.com.itproekt.gup.client.Response;
import ua.com.itproekt.gup.exception.BaseException;
import ua.com.itproekt.gup.exception.CustomerGupErrorCode;
import ua.com.itproekt.gup.exception.CustomerGupException;
import ua.com.itproekt.gup.exception.RestError;
import ua.com.itproekt.gup.model.RestCustomer;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RequestCallback;

import java.util.HashMap;
import java.util.Map;

public class CustomerGupClientImpl extends BaseRestServiceClientImpl implements CustomerGupClient {

    public RestCustomer saveCustomer(RestCustomer customer) {
        if (customer == null) {
            throw new CustomerGupException(CustomerGupErrorCode.INVALID_PARAMS, "Customer is empty");
        }

        Response                     response = null;
        RequestCallback       requestCallback = new GupRequestCallback(null, customer, restTemplate.getMessageConverters());
        GupResponseExtractor responseExtractor = new GupResponseExtractor(RestCustomer.class, restTemplate.getMessageConverters());

        response = restTemplate.execute(getServiceUrl() + "api/oauth", HttpMethod.POST, requestCallback, responseExtractor);

        if (response == null){
            System.err.println("[DEBUG] Response is null");
            throw new IllegalArgumentException("REST response is null");
        }

        if (!response.isFoundError()) {
            RestCustomer results = (RestCustomer) response.getResponse();
            return results;
        } else {
            /*
             * need to convert RestError back into exception and throw it
             */
            RestError restError = response.getErrResponse();
            BaseException    ex = restError.transformRestError();
            /*
             * can do i18n here, for now just go with default
             */
            ex.setMessage(ex.getErrorCode().getDefaultMessage());
            throw ex;
        }
    }

    public RestCustomer getCustomerByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new CustomerGupException(CustomerGupErrorCode.INVALID_PARAMS, "A valid name is required");
        }

        Response                     response = null;
        RequestCallback       requestCallback = new GupRequestCallback();
        GupResponseExtractor responseExtractor = new GupResponseExtractor(RestCustomer.class, restTemplate.getMessageConverters());

        /*
         * create params for request
         */
        Map<String,Object> uriVariables = new HashMap<String,Object>();
        uriVariables.put("name", name);

        /*
         * could throw one of HttpClientErrorException, HttpServerErrorException or RestClientException and the caller will need to catch and handle these
         */
        response = restTemplate.execute(getServiceUrl() + "api/oauth?name={name}", HttpMethod.GET, requestCallback, responseExtractor, uriVariables);

        if (response == null){
            System.err.println("[DEBUG] Response is null");
            throw new IllegalArgumentException("REST response is null");
        }

        if (!response.isFoundError()) {
            RestCustomer customer = (RestCustomer) response.getResponse();
            return customer;
        } else {
            /*
             * need to convert RestError back into exception and throw it
             */
            RestError restError = response.getErrResponse();
            BaseException    ex = restError.transformRestError();
            /*
             * can do i18n here, for now just go with default
             */
            ex.setMessage(ex.getErrorCode().getDefaultMessage());
            throw ex;
        }
    }

}
