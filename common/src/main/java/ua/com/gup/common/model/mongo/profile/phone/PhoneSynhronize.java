package ua.com.gup.common.model.mongo.profile.phone;

public class PhoneSynhronize {

    private Long numberPhone;
    private Boolean isFound;

    public PhoneSynhronize(){
    }

    public PhoneSynhronize(Long numberPhone, Boolean isFound){
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