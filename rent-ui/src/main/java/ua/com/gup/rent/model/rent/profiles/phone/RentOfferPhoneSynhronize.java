package ua.com.gup.rent.model.rent.profiles.phone;

public class RentOfferPhoneSynhronize {

    private Long numberPhone;
    private Boolean isFound;

    public RentOfferPhoneSynhronize(){
    }

    public RentOfferPhoneSynhronize(Long numberPhone, Boolean isFound){
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

}
