package ua.com.itproekt.gup.exception;

public class AlreadyVerifiedTokenException extends RuntimeException {

    public AlreadyVerifiedTokenException() {
        super("The token has already been verified");
    }
}
