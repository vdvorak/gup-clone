package ua.com.gup.rent.model.rent.profiles.phone;

import java.util.List;

public class RentOfferStorePhones {

    public RentOfferStorePhones(){
    }

    public RentOfferStorePhones(List<Long> mainPhones, List<RentOfferPhoneSynhronize> contactPhones){
        this.mainPhones = mainPhones;
        this.contactPhones = contactPhones;
    }

    public RentOfferStorePhones(String idUser, List<Long> mainPhones, List<RentOfferPhoneSynhronize> contactPhones){
        this.idUser = idUser;
        this.mainPhones = mainPhones;
        this.contactPhones = contactPhones;
    }


    private String idUser;
    private List<Long> mainPhones;
    private List<RentOfferPhoneSynhronize> contactPhones;


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

    public List<RentOfferPhoneSynhronize> getContactPhones() {
        return contactPhones;
    }

    public void setContactPhones(List<RentOfferPhoneSynhronize> contactPhones) {
        this.contactPhones = contactPhones;
    }

}

