package ua.com.gup.model.profiles.phone;


import com.google.gson.Gson;

import java.util.List;

public class ProfileStorePhones {

    public ProfileStorePhones(){
    }

    public ProfileStorePhones(List<PhoneSynhronize> contactPhones){
        this.contactPhones = contactPhones;
    }

    private List<PhoneSynhronize> contactPhones;

    public List<PhoneSynhronize> getContactPhones() {
        return contactPhones;
    }

    public void setContactPhones(List<PhoneSynhronize> contactPhones) {
        this.contactPhones = contactPhones;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
