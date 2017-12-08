package ua.com.gup.common.model.enumeration;


public enum CommonUserType {
    LEGAL_ENTITY("Физическое лицо"),
    INDIVIDUAL("Частный предприниматель"),
    ENTREPRENEUR("Юридическое лицо");

    private final String name;

    CommonUserType(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }
}
