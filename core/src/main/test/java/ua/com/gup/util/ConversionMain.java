package ua.com.gup.util;

import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.com.gup.model.xchangerate.api.CurrencyNotSupportedException;
import ua.com.gup.model.xchangerate.endpoint.EndpointException;
import ua.com.gup.model.xchangerate.service.ServiceException;
import ua.com.gup.model.xchangerate.storage.StorageException;
import ua.com.gup.model.xchangerate.util.Currency;

import java.math.BigDecimal;


public class ConversionMain {

    @Before
    public void setUp() {

    }

    @After
    public void tearDown(){

    }

    @Test
    public void testCurrencyConvert()
        throws ServiceException, StorageException, CurrencyNotSupportedException, EndpointException, JSONException
    {
        System.out.println( "100(USD) = " + CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal("100"), Currency.USD, Currency.EUR) + "(EUR)" ); // 100(USD) = 94.340(EUR)
        System.out.println( "100(UAH) = " + CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal("100"), Currency.UAH, Currency.USD) + "(USD)" ); // 100(UAH) = 3.73(USD)
        System.out.println( "200(USD) = " + CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal("200"), Currency.USD, Currency.UAH) + "(UAH)" ); // 200(USD) = 5366.0(UAH)
        System.out.println( "7.954545454545455(USD) = " + CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal("7.954545454545455"), Currency.USD, Currency.UAH) + "(UAH)" ); // 7.954545454545455(USD) = 213(UAH)

        System.out.println( "7.954545454545455(USD) = " + CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal("7.954545454545455"), Currency.USD, Currency.USD) + "(USD)" ); // 7.954545454545455(USD) = 7.954545454545455(USD)
        System.out.println( "213(UAH) = " + CurrencyConvertUtil.getInstance().convertCurrency(new BigDecimal("213"), Currency.UAH, Currency.UAH) + "(UAH)" ); // 213(UAH) = 213(UAH)
    }

}
