package ua.com.itproekt.gup.model.xchangerate.api;

import java.math.BigDecimal;

import org.json.JSONException;

import ua.com.itproekt.gup.model.xchangerate.endpoint.EndpointException;
import ua.com.itproekt.gup.model.xchangerate.service.ServiceException;
import ua.com.itproekt.gup.model.xchangerate.storage.StorageException;
import ua.com.itproekt.gup.model.xchangerate.util.Currency;

public interface Converter {

	public BigDecimal convertCurrency(BigDecimal moneyAmount, Currency fromCurrency, Currency toCurrency)
            throws CurrencyNotSupportedException, JSONException, StorageException, EndpointException, ServiceException;

}
