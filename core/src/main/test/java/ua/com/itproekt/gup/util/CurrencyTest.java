package ua.com.itproekt.gup.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Currency;
import java.util.Locale;

/**
 *
 */

public class CurrencyTest {

    @Before
    public void setUp() {

    }

    @After
    public void tearDown(){

    }

    @Test
    public void testCurrencyInfoForLocale(){
        Locale defaultLocale = Locale.getDefault();
        currencyInfoForLocale(defaultLocale);

        Locale swedishLocale = new Locale("sv", "SE");
        currencyInfoForLocale(swedishLocale);
    }


    private void currencyInfoForLocale(Locale locale) {
        System.out.println("Locale: " + locale.getDisplayName());
        Currency currency = Currency.getInstance(locale);
        System.out.println("Currency Code: " + currency.getCurrencyCode());
        System.out.println("Symbol: " + currency.getSymbol());
        System.out.println("Default Fraction Digits: " + currency.getDefaultFractionDigits());
        System.out.println();
    }
}
