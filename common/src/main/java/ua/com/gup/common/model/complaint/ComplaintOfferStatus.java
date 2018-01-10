package ua.com.gup.common.model.complaint;


/**
 * The complaint status enumeration.
 */
public enum ComplaintOfferStatus {

    NEW("Жалоба создана"),
    OPENED("Жалоба находится на рассмотрении"),
    ACCEPTED("Жалоба принята"),
    DECLINED("Жалоба отклонена");

    private final String name;

    ComplaintOfferStatus(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }
}
