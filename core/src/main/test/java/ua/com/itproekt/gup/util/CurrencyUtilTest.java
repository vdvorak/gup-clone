package ua.com.itproekt.gup.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.TransformerException;

/**
 *
 */

public class CurrencyUtilTest {

    private CurrencyUtil currency;

    @Before
    public void setUp() throws SOAPException, TransformerException {
        currency = new CurrencyUtil();
    }

    @After
    public void tearDown(){

    }

    @Test
    public void testCurrency() throws SOAPException, TransformerException {
        SOAPMessage reply = currency.send();
        currency.get(reply);
    }
}
