package ua.com.itproekt.gup.exception;

public enum Service {
	CUSTOMER_SERVICE(1, "CustomerGupErrorCode"),
	;
	
	private int      serviceId;
	private String serviceName;

	Service(int serviceId, String serviceName) {
		this.serviceId   = serviceId;
		this.serviceName = serviceName;
	}
	
	public int getServiceId() {
		return serviceId;
	}
	
	public String getServiceName(){
		return serviceName;
	}
	
	static public BaseException createServiceException(RestError restError) {
		switch (restError.getServiceId()){
		case 1:
			ErrorCode errorCode = CustomerGupErrorCode.get(restError.getErrorCode());
			return new CustomerGupException(errorCode, restError.getDebugMessage(), restError.getMessageArgs());
		default:
			return null;
		}
	}
}
