package ua.com.gup.model.profiles.phone;


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

}
