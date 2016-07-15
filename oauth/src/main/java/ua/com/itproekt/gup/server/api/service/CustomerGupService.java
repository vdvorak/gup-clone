package ua.com.itproekt.gup.server.api.service;

import ua.com.itproekt.gup.exception.CustomerGupException;
import ua.com.itproekt.gup.model.RestCustomer;

public interface CustomerGupService {

    /**
     *
     * @param customer
     * @return
     */
	RestCustomer saveCustomer(RestCustomer customer);

    /**
     *
     * @param name
     * @return
     * @throws CustomerGupException
     */
	RestCustomer getCustomerByName(String name) throws CustomerGupException;

    /**
     *
     * @param name
     * @return
     * @throws CustomerGupException
     */
	boolean deleteCustomerByName(String name) throws CustomerGupException;

}
