package ua.com.itproekt.gup.exception;


public class TokenHasExpiredException extends RuntimeException {

    public TokenHasExpiredException() {
        super("Token has expired");
    }
}
