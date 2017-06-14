package ua.com.gup.domain.enumeration;

/**
 * The offer status enumeration.
 */
public enum OfferStatus {
    ON_MODERATION("На модерации"),
    REJECTED("Отклонена"),
    ACTIVE("Активная"),
    DEACTIVATED("Деактивирована"),
    ARCHIVED("В архив");

    private final String name;

    OfferStatus(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }
}
