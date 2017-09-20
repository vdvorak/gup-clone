package ua.com.gup.exception;


public class TokenNotFoundException extends RuntimeException {

    public TokenNotFoundException() {
        super("Token Not Found");
    }
}
