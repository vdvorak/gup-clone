package ua.com.gup.service.currency;


import ua.com.gup.common.model.enumeration.CommonCurrency;

import java.math.BigDecimal;

public interface CurrencyConverterService {

    /**
     * Get base currency.
     *
     * @return the base currency
     */
    CommonCurrency getBaseCurrency();

    /**
     * Calculate the amount in base currency.
     *
     * @param currency the currency from
     * @param amount   the amount to convert
     * @return the amount in base currency
     */
    BigDecimal convertToBaseCurrency(CommonCurrency currency, BigDecimal amount);
}
