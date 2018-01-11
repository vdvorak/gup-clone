package ua.com.gup.common.model.mongo.profile.phone;


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

}
