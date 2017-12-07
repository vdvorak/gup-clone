package ua.com.gup.rent.model.rent.profiles.phone;

public class RentOfferPhoneSynhronizeID {

    private Long numberPhone;
    private String userId;

    public RentOfferPhoneSynhronizeID(){
    }

    public RentOfferPhoneSynhronizeID(Long numberPhone, String userId){
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

}
