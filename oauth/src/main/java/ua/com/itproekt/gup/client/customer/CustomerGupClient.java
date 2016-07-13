package ua.com.itproekt.gup.client.customer;

import ua.com.itproekt.gup.client.BaseRestServiceClient;
import ua.com.itproekt.gup.model.RestCustomer;

public interface CustomerGupClient extends BaseRestServiceClient {

    RestCustomer saveCustomer(RestCustomer customer);
    RestCustomer getCustomerByName(String name);

}
