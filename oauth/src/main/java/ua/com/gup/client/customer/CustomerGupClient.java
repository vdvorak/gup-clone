package ua.com.gup.client.customer;

import ua.com.gup.client.BaseRestServiceClient;
import ua.com.gup.model.RestCustomer;

public interface CustomerGupClient extends BaseRestServiceClient {

    RestCustomer saveCustomer(RestCustomer customer);
    RestCustomer getCustomerByName(String name);

}
