package ua.com.itproekt.gup.rental.algorithm.type;

import ua.com.itproekt.gup.rental.resource.Resource;
import ua.com.itproekt.gup.rental.resource.ResourceBundleType;


public class OfferType extends Type {

    public OfferType(String title, Types type) {
        super(title, type);
    }

    @Override
    public String toString() {
        return Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("offer.toString", getTitle(), getType()) +
                (isAvailable()
                        ? Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("available")
                        : Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("not.available"));
    }
}
