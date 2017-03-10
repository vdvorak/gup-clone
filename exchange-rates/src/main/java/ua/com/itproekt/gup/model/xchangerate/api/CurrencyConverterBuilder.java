package ua.com.itproekt.gup.model.xchangerate.api;

import ua.com.itproekt.gup.model.xchangerate.endpoint.CurrencyLayerEndpoint;
import ua.com.itproekt.gup.model.xchangerate.endpoint.YahooEndpoint;
import ua.com.itproekt.gup.model.xchangerate.storage.DiskStore;
import ua.com.itproekt.gup.model.xchangerate.storage.FileStore;
import ua.com.itproekt.gup.model.xchangerate.util.Strategy;

public class CurrencyConverterBuilder {
	private String   _accessKey;
	private Strategy  _strategy;
	private DiskStore diskStore;

	public CurrencyConverterBuilder() {
	}

	public CurrencyConverter buildConverter() {
		if (_strategy == null) {
			throw new IllegalArgumentException("No Strategy defined to create Currency converter");
		} else if (_strategy.equals(Strategy.YAHOO_FINANCE_FILESTORE)) {
			diskStore = new FileStore("yahoo");
			return new CurrencyConverter(diskStore, new YahooEndpoint(diskStore));
		} else if (_strategy.equals(Strategy.CURRENCY_LAYER_FILESTORE) && _accessKey != null) {
			diskStore = new FileStore("currencyLayer");
			return new CurrencyConverter(diskStore, new CurrencyLayerEndpoint(diskStore, _accessKey));
		}
		throw new IllegalArgumentException("Strategy: " + _strategy + " requires accessKey for endpoint service, none provided!");
	}

	public CurrencyConverterBuilder strategy(Strategy _strategy) {
		this._strategy = _strategy;
		return this;
	}

	public CurrencyConverterBuilder accessKey(String _accessKey) {
		this._accessKey = _accessKey;
		return this;
	}
}
