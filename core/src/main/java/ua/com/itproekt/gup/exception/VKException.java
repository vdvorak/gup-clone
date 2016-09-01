package ua.com.itproekt.gup.exception;

import com.google.gson.Gson;

import java.util.Collection;
import java.util.Map;

public class VkException extends Exception {

    private String errType, errMessage;

    private static final long serialVersionUID = -4914754445188651128L;

    public VkException(String response){
        Gson gson = new Gson();
        try {
            VKErrorContainer requestError = gson.fromJson( response, VKErrorContainer.class );
            errType    = requestError.getErrors().get( "reason" );
            errMessage = requestError.getErrors().get( "message" );
        } catch ( Exception e ){
            System.err.println( e.getMessage() );
            errType	   = "Unknown Error";
            errMessage = "An unkown error has occured. Unable to process response from server: \n" + response;
        }
    }

    public void show(){
        System.err.println( "[VK API Error] " + this.errMessage );
    }

    public String getMessage(){
        return this.errMessage;
    }

    public String getType(){
        return this.errType;
    }

    private class VKErrorContainer {
        private VKError error;
        public Map<String,String> getErrors(){ return error.errors.iterator().next(); }
        public String getCode(){ return error.code; }
        public String getMessage(){ return error.message; }
    }

    private class VKError {
        protected Collection<Map<String,String>> errors;
        protected String code;
        protected String message;
    }

}

