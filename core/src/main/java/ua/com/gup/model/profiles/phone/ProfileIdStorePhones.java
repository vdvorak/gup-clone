package ua.com.gup.model.profiles.phone;


import com.google.gson.Gson;

import java.util.List;

public class ProfileIdStorePhones {

    public ProfileIdStorePhones(){
    }

    public ProfileIdStorePhones(List<PhoneSynhronizeID> contactPhones){
        this.contactPhones = contactPhones;
    }

    private List<PhoneSynhronizeID> contactPhones;

    public List<PhoneSynhronizeID> getContactPhones() {
        return contactPhones;
    }

    public void setContactPhones(List<PhoneSynhronizeID> contactPhones) {
        this.contactPhones = contactPhones;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
