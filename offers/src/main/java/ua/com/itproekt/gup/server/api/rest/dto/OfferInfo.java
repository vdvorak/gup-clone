package ua.com.itproekt.gup.server.api.rest.dto;


import ua.com.itproekt.gup.model.offer.Offer;

public class OfferInfo extends Offer {
    private Offer offer;
    private boolean isOnline;
    private String userName;

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setIsOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "OfferInfo{" +
                "offer=" + offer +
                ", isOnline=" + isOnline +
                ", userName='" + userName + '\'' +
                '}';
    }
}
