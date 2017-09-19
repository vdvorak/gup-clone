package ua.com.itproekt.gup.model.profiles;


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
