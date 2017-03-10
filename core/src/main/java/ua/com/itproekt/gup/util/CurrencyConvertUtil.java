package ua.com.itproekt.gup.util;

import org.json.JSONException;
import ua.com.itproekt.gup.model.xchangerate.api.CurrencyConverter;
import ua.com.itproekt.gup.model.xchangerate.api.CurrencyConverterBuilder;
import ua.com.itproekt.gup.model.xchangerate.api.CurrencyNotSupportedException;
import ua.com.itproekt.gup.model.xchangerate.endpoint.EndpointException;
import ua.com.itproekt.gup.model.xchangerate.service.ServiceException;
import ua.com.itproekt.gup.model.xchangerate.storage.StorageException;
import ua.com.itproekt.gup.model.xchangerate.util.Strategy;

public class CurrencyConvertUtil {

    private static volatile CurrencyConverter instance;

    public static CurrencyConverter getInstance()
            throws ServiceException, StorageException, CurrencyNotSupportedException, EndpointException, JSONException
    {
        CurrencyConverter localInstance = instance;
        if (localInstance == null) {
            synchronized (CurrencyConverter.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CurrencyConverterBuilder()
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
