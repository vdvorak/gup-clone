package ua.com.itproekt.gup.rental.util;

import ua.com.itproekt.gup.rental.resource.Resource;
import ua.com.itproekt.gup.rental.resource.ResourceBundleType;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

public class Converter {
    public static String toDays(int days) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(days);
        if (days > 1)
            stringBuilder.append(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("days"));
        else
            stringBuilder.append(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("day"));
        return stringBuilder.toString();
    }

    public static String toCurrency(BigDecimal price) {
        Currency currency = Currency.getInstance(Locale.getDefault());
        return currency.getSymbol() + price;
    }
}
