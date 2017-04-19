package ua.com.gup.domain;


import java.util.Map;

public class AddressArea {

    private String code;

    private Map<String, String> name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Map<String, String> getName() {
        return name;
    }

    public void setName(Map<String, String> name) {
        this.name = name;
    }
}
