package ua.com.itproekt.gup.exception;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum CustomerGupErrorCode implements ErrorCode {
	UNKNOWN_ERROR(1, "UNKNOWN_ERROR", "customer error unknown"),
	INVALID_PARAMS(2, "INVALID_PARAMS", "customer error params invalid"),
	NOT_FOUND(3, "NOT_FOUND", "customer error entity notfound"),
	DUP_CUSTNAME(4, "DUP_CUSTNAME", "customer error entity dup cust"),
    ;

	private static final Map<Integer,CustomerGupErrorCode> lookup = new HashMap<Integer,CustomerGupErrorCode>();
	static {
		for(CustomerGupErrorCode e : EnumSet.allOf(CustomerGupErrorCode.class))
			lookup.put(e.getErrorCode(), e);
	}
	
	private static Service service = Service.CUSTOMER_SERVICE;
	private int  errorCode;
	private String    name;
	private String i18nKey;
	
	CustomerGupErrorCode(int errorCode, String name, String i18nKey) {
		this.errorCode = errorCode;
		this.name      = name;
		this.i18nKey   = i18nKey;
	}

    @Override
	public int getErrorCode() {
		return this.errorCode;
	}

    @Override
	public String getName() {
		return this.name;
	}

    @Override
	public int getServiceId() {
		return service.getServiceId();
	}

    @Override
	public String getMessageKey() {
		return i18nKey;
	}

    @Override
	public String getDefaultMessage() {
		switch (this){
		case UNKNOWN_ERROR:
			return "An unknown error has been encountered";
		case INVALID_PARAMS:
			return "Invalid parameters were received";
		case NOT_FOUND:
			return "Requested entity was not found";
		case DUP_CUSTNAME:
			return "Duplicate customer name used";
		default: 
			return "An undefined error has been encountered";
		}
	}

	public static CustomerGupErrorCode get(int errorCode) {
		return lookup.get(errorCode); 
	}
}
