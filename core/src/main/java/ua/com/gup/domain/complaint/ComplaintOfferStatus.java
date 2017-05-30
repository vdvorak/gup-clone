package ua.com.gup.domain.complaint;


/**
 * The complaint status enumeration.
 */
public enum ComplaintOfferStatus {
    NEW("New"),
    BEING_CONSIDERED("Under consideration"),
    REJECTED("Rejected"),
    DEACTIVATED("Not active"),
    ACTIVE("Active"),
    ARCHIVED("Sent to archive");

    private final String name;

    ComplaintOfferStatus(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }
}
