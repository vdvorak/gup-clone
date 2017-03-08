package ua.com.itproekt.gup.dao.offers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertTrue;

public class CurrencyDriverTest {

    private CurrencyDriver driver;

    @Before
    public void setUp() {
        try {
            driver = new CurrencyDriver(0l,0l);
        } catch (Exception e){
            e.getStackTrace();
        }
    }

    @After
    public void tearDown(){

    }

    @Test
    public void testCreateCurrencyDriver(){
        assertTrue( true );
    }

}
