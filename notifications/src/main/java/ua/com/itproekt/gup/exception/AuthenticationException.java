package ua.com.itproekt.gup.exception;


public class AuthenticationException extends RuntimeException {

    public AuthenticationException() {
        super("Authentication Error. The username or password were incorrect");
    }
}