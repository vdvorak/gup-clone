package ua.com.itproekt.gup.util;

import java.text.NumberFormat;
import java.util.*;

public class CurrencyLocaleUtil {
    public static SortedMap<Currency, Locale> currencyLocaleMap;

    static {
        currencyLocaleMap = new TreeMap<Currency, Locale>(new Comparator<Currency>() {
            @Override
            public int compare(Currency c1, Currency c2) {
                return c1.getCurrencyCode().compareTo(c2.getCurrencyCode());
            }
        });

        for (Locale locale : Locale.getAvailableLocales()) {
            try {
                Currency currency = Currency.getInstance(locale);
                currencyLocaleMap.put(currency, locale);
            }
            catch (Exception e) {
            }
        }
    }


    public static String getCurrencySymbol(String currencyCode) {
        Currency currency = Currency.getInstance(currencyCode);
        return currency.getSymbol(currencyLocaleMap.get(currency));
    }

    public static String  getAmountAsFormattedString(Double amount, Double decimals, String currencyCode) {
        Currency currency = Currency.getInstance(currencyCode);
        double doubleBalance = 0.00;
        if (amount != null) {
            doubleBalance = ((Double) amount) / (Math.pow(10.0, decimals));
        }
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(currencyLocaleMap.get(currency));
        return numberFormat.format(doubleBalance);
    }


}
