package ua.com.gup.service.currency;

import com.ritaja.xchangerate.api.CurrencyConverter;
import com.ritaja.xchangerate.api.CurrencyConverterBuilder;
import com.ritaja.xchangerate.api.CurrencyNotSupportedException;
import com.ritaja.xchangerate.endpoint.EndpointException;
import com.ritaja.xchangerate.service.ServiceException;
import com.ritaja.xchangerate.storage.StorageException;
import com.ritaja.xchangerate.util.Strategy;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.com.gup.common.model.enumeration.CommonCurrency;

import java.math.BigDecimal;

@Service
public class CurrencyConverterServiceImpl implements CurrencyConverterService {

    private final Logger log = LoggerFactory.getLogger(CurrencyConverterServiceImpl.class);

    private CurrencyConverter currencyConverter;

    private CommonCurrency baseCurrency = CommonCurrency.UAH;

    /**
     * Get base currency.
     *
     * @return the base currency
     */
    @Override
    public CommonCurrency getBaseCurrency() {
        return baseCurrency;
    }

    /**
     * Calculate the amount in base currency.
     *
     * @param currency the currency from
     * @param amount   the amount to convert
     * @return the amount in base currency
     */
    @Override
    public BigDecimal convertToBaseCurrency(CommonCurrency currency, BigDecimal amount) {
        if (currency == baseCurrency) {
            return amount;
        } else {
            final com.ritaja.xchangerate.util.Currency convertorBaseCurrency = com.ritaja.xchangerate.util.Currency.get(baseCurrency.name());
            final com.ritaja.xchangerate.util.Currency convertorCurrency = com.ritaja.xchangerate.util.Currency.get(currency.name());
            try {
                BigDecimal exchangeRate = getInstance().convertCurrency(new BigDecimal("100000"), convertorCurrency, convertorBaseCurrency);
                exchangeRate = exchangeRate.divide(new BigDecimal("100000"));
                return amount.multiply(exchangeRate);
            } catch (CurrencyNotSupportedException e) {
                log.error("Currency {} or {} not supported:", currency, baseCurrency, e);
            } catch (StorageException e) {
                log.error("Storage exception: ", e);
            } catch (EndpointException e) {
                log.error("Endpoint exception: ", e);
            } catch (ServiceException e) {
                log.error("Service exception: ", e);
            }
        }
        return amount;
    }

    private CurrencyConverter getInstance()
            throws ServiceException, StorageException, CurrencyNotSupportedException, EndpointException, JSONException {
        CurrencyConverter localInstance = currencyConverter;
        if (localInstance == null) {
            synchronized (CurrencyConverterServiceImpl.class) {
                localInstance = currencyConverter;
                if (localInstance == null) {
                    currencyConverter = localInstance = new CurrencyConverterBuilder()
                            .strategy(Strategy.YAHOO_FINANCE_FILESTORE)
                            .accessKey("")
                            .buildConverter();
                    localInstance.setRefreshRateSeconds(86400);
                }
            }
        }
        return localInstance;
    }
}
