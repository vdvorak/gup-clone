package ua.com.gup.rent.model.rent.profiles.phone;


import java.util.List;

public class RentOfferProfileStorePhones {

    public RentOfferProfileStorePhones(){
    }

    public RentOfferProfileStorePhones(List<RentOfferPhoneSynhronize> contactPhones){
        this.contactPhones = contactPhones;
    }

    private List<RentOfferPhoneSynhronize> contactPhones;

    public List<RentOfferPhoneSynhronize> getContactPhones() {
        return contactPhones;
    }

    public void setContactPhones(List<RentOfferPhoneSynhronize> contactPhones) {
        this.contactPhones = contactPhones;
    }

}
