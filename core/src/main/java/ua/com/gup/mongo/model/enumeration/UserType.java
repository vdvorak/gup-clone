package ua.com.gup.mongo.model.enumeration;


public enum UserType {
    LEGAL_ENTITY("Физическое лицо"),
    INDIVIDUAL("Частный предприниматель"),
    ENTREPRENEUR("Юридическое лицо");

    private final String name;

    UserType(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }
}
