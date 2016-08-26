package ua.com.itproekt.gup.exception;

import com.google.gson.Gson;

import java.util.Map;

public class FacebookException extends Exception {

	private String errType, errMessage;

	private static final long serialVersionUID = 5560904358324172134L;
	
	public FacebookException(String response){
		Gson gson = new Gson();
		try {
			FBError requestError = gson.fromJson( response, FBError.class );
			errType = requestError.error.get( "type" );
			errMessage = requestError.error.get( "message" );
		} catch ( Exception e ){
            System.err.println( e.getMessage() );
			errType	   = "Unknown Error";
			errMessage = "An unkown error has occured. Unable to process response from server: \n" + response;
		}
	}

	public void show(){
		System.err.println( "[Facebook Graph API Error] " + this.errMessage );
	}

	public String getMessage(){
		return this.errMessage;
	}

	public String getType(){
		return this.errType;
	}

	private class FBError {
		public Map<String,String> error;
	}
	
}