package ua.com.itproekt.gup.rental.item;


import ua.com.itproekt.gup.rental.resource.Resource;
import ua.com.itproekt.gup.rental.resource.ResourceBundleType;

public enum ItemType {
    NEW_RELEASES(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("new.releases")),
    REGULAR(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("regular")),
    OLD(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("old"));

    private final String type;

    private ItemType(String type) {
        this.type = type;
    }

    public static ItemType getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }


    @Override
    public String toString() {
        return type;
    }
}
