package ua.com.itproekt.gup.model.profiles.phone;

import com.google.gson.Gson;

public class PhoneSynhronizeID {

    private Long numberPhone;
    private String userId;

    public PhoneSynhronizeID(){
    }

    public PhoneSynhronizeID(Long numberPhone, String userId){
        this.numberPhone=numberPhone;
        this.userId=userId;
    }

    public Long getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(Long numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId=userId;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
