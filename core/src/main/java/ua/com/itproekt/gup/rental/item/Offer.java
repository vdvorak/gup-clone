package ua.com.itproekt.gup.rental.item;

import ua.com.itproekt.gup.rental.resource.Resource;
import ua.com.itproekt.gup.rental.resource.ResourceBundleType;

public class Offer extends Item {

    public Offer(String title, ItemType type) {
        super(title, type);
    }

    @Override
    public String toString() {
        return Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("offer.toString", getTitle(), getType()) +
                (isAvailable() ?
                        Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("available") :
                        Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("not.available"));
    }
}
