package ua.com.gup.common.model.mongo.profile.phone;

public class PhoneSynhronizeID {

    private Long numberPhone;
    private String userId;

    public PhoneSynhronizeID(){
    }

    public PhoneSynhronizeID(Long numberPhone, String userId){
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
