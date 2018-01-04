package ua.com.gup.common.dto.profile;

public class ProfileContactListDto {

    public ProfileContactListDto(){
    }

    public ProfileContactListDto(String key, String value){
        this.value = key;
        this.value = value;
    }

    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ProfileContactList{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
