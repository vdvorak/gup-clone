package ua.com.gup.exception;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseException extends RuntimeException {
	protected String                  message;
	protected String             debugMessage;
	protected Map<String, String> messageArgs = new HashMap<String, String>();

    @Override
	public String getMessage(){
		return this.message;
	}
	public void setMessage(String message){
		this.message = message;
	}
	public String getDebugMessage() {
		return debugMessage;
	}
	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}

	public Map<String, String> getMessageArgs() {
		return messageArgs;
	}
	public void setMessageArgs(Map<String, String> messageArgs) {
		this.messageArgs = messageArgs;
	}
}
