package ua.com.gup.domain.complaint;


/**
 * The complaint status enumeration.
 */
public enum ComplaintOfferStatus {

    NEW("Жалоба создаётся пользователем"),
    OPENED("Жалоба открывается администратором"),
    DECLINED("Жалоба отклоняется администратором"),
    ACCEPTED("Жалоба подтверждается администратором");

    private final String name;

    ComplaintOfferStatus(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }
}
