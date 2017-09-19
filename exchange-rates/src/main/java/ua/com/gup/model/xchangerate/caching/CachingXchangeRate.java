package ua.com.itproekt.gup.model.xchangerate.caching;

import org.joda.time.DateTime;
import org.json.JSONException;
import org.json.JSONObject;

import ua.com.itproekt.gup.model.xchangerate.api.CurrencyNotSupportedException;
import ua.com.itproekt.gup.model.xchangerate.endpoint.EndpointException;
import ua.com.itproekt.gup.model.xchangerate.storage.DiskStore;
import ua.com.itproekt.gup.model.xchangerate.storage.StorageException;
import ua.com.itproekt.gup.model.xchangerate.util.Currency;

public abstract class CachingXchangeRate {

	public int   refreshRateSeconds = 86400;
	public JSONObject exchangeRates = null;
	private DiskStore     diskStore;

	public CachingXchangeRate(DiskStore diskStore) {
		this.diskStore = diskStore;
	}

	public void setExchangeRates(JSONObject exchangeRates) {
		this.exchangeRates = exchangeRates;
	}

	/**
	 * Checks if the rates have expired judging from the timestamp of the stored exchange rate resource file.
	 *
	 * @return boolean truth value
	 * @throws EndpointException
	 * @throws CurrencyNotSupportedException
	 * @throws StorageException
	 */
	public boolean checkRatesUsable(Currency currency)
            throws JSONException, CurrencyNotSupportedException, StorageException
    {
		if (!diskStore.resourceExists()) {
			return false;
		} else if (exchangeRates == null) {
			setExchangeRates(diskStore.loadRates());
		}

		// calculate the difference in timestamp and return false if not expired
		long old = getTimestamp(currency);
		long now = new DateTime().getMillis();
		if (Math.abs((old - now) / 1000) < (refreshRateSeconds)) {
			return true;
		}

		// return true if the timestamp has expired
		return false;
	}

	/**
	 * Get the timestamp of associated exchange rate
	 *
	 * @param currency
	 * @return timestamp
	 * @throws EndpointException
	 * @throws CurrencyNotSupportedException
	 */
	public abstract long getTimestamp(Currency currency)
            throws JSONException, CurrencyNotSupportedException;

}
