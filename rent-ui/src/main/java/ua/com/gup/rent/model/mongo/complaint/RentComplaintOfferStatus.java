package ua.com.gup.rent.model.mongo.complaint;


/**
 * The complaint status enumeration.
 */
public enum RentComplaintOfferStatus {

    NEW("Жалоба создана"),
    OPENED("Жалоба находится на рассмотрении"),
    ACCEPTED("Жалоба принята"),
    DECLINED("Жалоба отклонена");

    private final String name;

    RentComplaintOfferStatus(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }
}
