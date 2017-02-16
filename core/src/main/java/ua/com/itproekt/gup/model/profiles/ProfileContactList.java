package ua.com.itproekt.gup.model.profiles;

public class ProfileContactList {

    public ProfileContactList(){
    }

    public ProfileContactList(String value){
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ProfileContactList{" +
                "value='" + value + '\'' +
                '}';
    }
}
