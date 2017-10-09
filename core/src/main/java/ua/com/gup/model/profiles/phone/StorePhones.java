package ua.com.gup.model.profiles.phone;

import java.util.List;

public class StorePhones {

    public StorePhones(){
    }

    public StorePhones(List<Long> mainPhones, List<PhoneSynhronize> contactPhones){
        this.mainPhones = mainPhones;
        this.contactPhones = contactPhones;
    }

    public StorePhones(String idUser, List<Long> mainPhones, List<PhoneSynhronize> contactPhones){
        this.idUser = idUser;
        this.mainPhones = mainPhones;
        this.contactPhones = contactPhones;
    }


    private String idUser;
    private List<Long> mainPhones;
    private List<PhoneSynhronize> contactPhones;


    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public List<Long> getMainPhones() {
        return mainPhones;
    }

    public void setMainPhones(List<Long> mainPhones) {
        this.mainPhones = mainPhones;
    }

    public List<PhoneSynhronize> getContactPhones() {
        return contactPhones;
    }

    public void setContactPhones(List<PhoneSynhronize> contactPhones) {
        this.contactPhones = contactPhones;
    }

}

