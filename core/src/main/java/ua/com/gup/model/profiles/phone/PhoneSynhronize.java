package ua.com.itproekt.gup.model.profiles.phone;

import com.google.gson.Gson;

public class PhoneSynhronize {

    private Long numberPhone;
    private Boolean isFound;

    public PhoneSynhronize(){
    }

    public PhoneSynhronize(Long numberPhone, Boolean isFound){
        this.numberPhone=numberPhone;
        this.isFound=isFound;
    }

    public Long getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(Long numberPhone) {
        this.numberPhone = numberPhone;
    }

    public Boolean getIsFound() {
        return isFound;
    }

    public void setIsFound(Boolean isFound) {
        this.isFound = isFound;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
