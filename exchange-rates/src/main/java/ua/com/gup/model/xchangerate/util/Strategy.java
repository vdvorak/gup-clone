package ua.com.gup.model.xchangerate.util;

public enum Strategy {

	YAHOO_FINANCE_FILESTORE("YAHOO_FINANCE_FILESTORE"),
	CURRENCY_LAYER_FILESTORE("CURRENCY_LAYER_FILESTORE");

	private final String strategy;

	private Strategy(String strategy) {
		this.strategy = strategy;
	}

    /**
     * Constants only
     */
	public String toString() {
		return this.strategy;
	}

}
