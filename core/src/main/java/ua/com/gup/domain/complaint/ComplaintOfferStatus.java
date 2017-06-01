package ua.com.gup.domain.complaint;


/**
 * The complaint status enumeration.
 */
public enum ComplaintOfferStatus {
    NEW("Жалоба создаётся пользователем"),
    OPENED("Жалоба открывается администратором"),
    DECLINED("(Администратор выносит вердикт по жалобе) жалоба отклоняется администратором, кто-то глупости написал"),
    ACCEPTED("Жалоба подтверждается администратором, кто-то написал по делу");
    private final String name;

    ComplaintOfferStatus(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }
}
