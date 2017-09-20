//package ua.com.gup.model.xchangerate;
//
//import java.math.BigDecimal;
//
//import org.json.JSONException;
//import org.junit.BeforeClass;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import ua.com.gup.model.xchangerate.api.CurrencyConverter;
//import ua.com.gup.model.xchangerate.api.CurrencyConverterBuilder;
//import ua.com.gup.model.xchangerate.api.CurrencyNotSupportedException;
//import ua.com.gup.model.xchangerate.endpoint.EndpointException;
//import ua.com.gup.model.xchangerate.service.ServiceException;
//import ua.com.gup.model.xchangerate.storage.StorageException;
//import ua.com.gup.model.xchangerate.util.Currency;
//import ua.com.gup.model.xchangerate.util.Strategy;
//
//
//@Test
//public class ConversionTest {
//
//	private CurrencyConverter converter;
//
//	@BeforeClass
//	public void setup() {
//		converter = new CurrencyConverterBuilder()
//				.strategy(Strategy.YAHOO_FINANCE_FILESTORE)
//				.accessKey("")
//				.buildConverter();
//        converter.setRefreshRateSeconds(86400);
//	}
//
//	@Test
//	public void simpleConverterFunctionalityTest()
//            throws ServiceException, StorageException, CurrencyNotSupportedException, EndpointException, JSONException
//    {
//		Assert.assertNotNull(converter.convertCurrency(new BigDecimal("100"), Currency.USD, Currency.EUR), "Expected a value after conversion");
//	}
//
//	@Test
//	public void simpleConvertersionTest()
//            throws ServiceException, StorageException, CurrencyNotSupportedException, EndpointException, JSONException
//    {
//		Assert.assertTrue(converter.convertCurrency(new BigDecimal("100"), Currency.USD, Currency.GBP).compareTo(new BigDecimal("100.00")) == -1, "Expected a value after conversion");
//	}
//
//}
