package ua.com.gup.model.xchangerate.storage;

import org.json.JSONObject;

public abstract class DiskStore {

	public String resourceFilepath = System.getProperty("java.io.tmpdir"); //TODO:

	/**
	 * Saves the exchange rates in a stored resource file
	 *
	 * @throws StorageException
	 */
	public abstract void saveRates(JSONObject exchangeRates)
            throws StorageException;

	/**
	 * Parses the exchange rates from the stored resource file and stores them as a JSONObject
	 *
	 * @throws StorageException
	 */
	public abstract JSONObject loadRates()
            throws StorageException;

	/**
	 * Check if this resource resource exists
	 *
	 * @return boolean truth value
	 */
	public abstract boolean resourceExists();

}
