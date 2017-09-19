package ua.com.gup.rental.algorithm.type;

import ua.com.gup.rental.resource.Resource;
import ua.com.gup.rental.resource.ResourceBundleType;


public enum Types {
    NEW_RELEASES(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("new.releases")),
    REGULAR(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("regular")),
    OLD(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("old"));

    private final String type;

    private Types(String type) {
        this.type = type;
    }

    public static Types getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }


    @Override
    public String toString() {
        return type;
    }
}
