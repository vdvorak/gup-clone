package ua.com.itproekt.gup.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 *
 */

public class CurrencyLocaleUtilTest {

    @Before
    public void setUp() {

    }

    @After
    public void tearDown(){

    }

    @Test
    public void testCurrencyLocale(){
        System.out.println( "currencyLocaleMap: " + CurrencyLocaleUtil.currencyLocaleMap ); // <Currency,Locale> USD=es_US
        System.out.println( "getCurrencySymbol: " + CurrencyLocaleUtil.getCurrencySymbol("USD") );
        System.out.println( "getAmountAsFormattedString: " + CurrencyLocaleUtil.getAmountAsFormattedString(100.00, 100.00, "USD") );
    }

}
