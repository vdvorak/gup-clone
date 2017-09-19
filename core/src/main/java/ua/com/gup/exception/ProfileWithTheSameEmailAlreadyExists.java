package ua.com.gup.exception;

/**
 * Created by Sasha on 26.12.2015.
 */
public class ProfileWithTheSameEmailAlreadyExists  extends RuntimeException {

    public ProfileWithTheSameEmailAlreadyExists() {
        super("Profile with the same email already exists");
    }
}
