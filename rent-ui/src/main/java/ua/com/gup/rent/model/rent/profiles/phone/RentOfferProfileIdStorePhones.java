package ua.com.gup.rent.model.rent.profiles.phone;


import java.util.List;

public class RentOfferProfileIdStorePhones {

    public RentOfferProfileIdStorePhones(){
    }

    public RentOfferProfileIdStorePhones(List<RentOfferPhoneSynhronizeID> contactPhones){
        this.contactPhones = contactPhones;
    }

    private List<RentOfferPhoneSynhronizeID> contactPhones;

    public List<RentOfferPhoneSynhronizeID> getContactPhones() {
        return contactPhones;
    }

    public void setContactPhones(List<RentOfferPhoneSynhronizeID> contactPhones) {
        this.contactPhones = contactPhones;
    }

}
