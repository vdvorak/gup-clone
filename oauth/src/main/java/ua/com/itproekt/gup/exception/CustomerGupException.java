package ua.com.itproekt.gup.exception;

import java.util.Map;

public class CustomerGupException extends BaseException {
	private static final long serialVersionUID = 8823356956725033191L;

	public CustomerGupException(ErrorCode errorCode) {
		super();
		this.setErrorCode(errorCode);
	}
	
	public CustomerGupException(ErrorCode errorCode, String debugMessage) {
		super();
		this.setErrorCode(errorCode);
		this.setDebugMessage(debugMessage);
	}
	
	public CustomerGupException(ErrorCode errorCode, String debugMessage, Map<String, String> messageArgs) {
		super();
		this.setErrorCode(errorCode);
		this.setDebugMessage(debugMessage);
		this.messageArgs = messageArgs;
	}
}
