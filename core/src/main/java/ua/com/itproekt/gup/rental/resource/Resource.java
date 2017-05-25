package ua.com.itproekt.gup.rental.resource;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class Resource {

    private static Resource instance;
    private ResourceBundle bundle;

    private Resource(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public static synchronized Resource getInstance(ResourceBundle bundle) {
        if (instance == null) {
            instance = new Resource(bundle);
        } else if (!instance.bundle.getBaseBundleName().equals(bundle.getBaseBundleName())) {
            instance = new Resource(bundle);
        }

        return instance;
    }

    public String getString(String key) {
        if (bundle.containsKey(key))
            return this.bundle.getString(key);
        return null;
    }

    public String getString(String key, Object... params) {
        if (bundle.containsKey(key))
            return MessageFormat.format(this.bundle.getString(key), params);
        return null;
    }

}
