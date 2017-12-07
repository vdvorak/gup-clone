package ua.com.gup.rent.model.rent.profiles.phone;

public class RentOfferPhone {
    private String phoneNumber;
    private Boolean hidden;

    public RentOfferPhone() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Boolean isHidden() {
        return hidden;
    }

    @Override
    public String toString() {
        return "RentOfferPhone{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", hidden=" + hidden +
                '}';
    }
}
