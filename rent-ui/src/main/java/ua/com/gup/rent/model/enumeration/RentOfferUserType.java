package ua.com.gup.rent.model.enumeration;


public enum RentOfferUserType {
    LEGAL_ENTITY("Физическое лицо"),
    INDIVIDUAL("Частный предприниматель"),
    ENTREPRENEUR("Юридическое лицо");

    private final String name;

    RentOfferUserType(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }
}
