package ua.com.gup.service.currency;


import ua.com.gup.domain.enumeration.Currency;

import java.math.BigDecimal;

public interface CurrencyConverterService {

    /**
     * Get base currency.
     *
     * @return the base currency
     */
    Currency getBaseCurrency();

    /**
     * Calculate the amount in base currency.
     *
     * @param currency the currency from
     * @param amount   the amount to convert
     * @return the amount in base currency
     */
    BigDecimal convertToBaseCurrency(Currency currency, BigDecimal amount);
}
