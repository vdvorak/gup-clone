package ua.com.gup.rental.resource;

import java.util.Locale;
import java.util.ResourceBundle;

public enum ResourceBundleType {
    LABELS(ResourceBundle.getBundle("Labels", Locale.ENGLISH)),
    ERRORS(ResourceBundle.getBundle("Errors", Locale.ENGLISH));

    private final ResourceBundle bundle;

    private ResourceBundleType(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public ResourceBundle getBundle() {
        return bundle;
    }
}
