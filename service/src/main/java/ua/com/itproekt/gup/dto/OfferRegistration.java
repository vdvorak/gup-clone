package ua.com.itproekt.gup.dto;


import ua.com.itproekt.gup.model.offer.Offer;

import java.util.List;

public class OfferRegistration {
    private Offer offer;
    private String email;
    private String password;
    private List<String> importImagesUrlList;

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public List<String> getImportImagesUrlList() {
        return importImagesUrlList;
    }

    public void setImportImagesUrlList(List<String> importImagesUrlListl) {
        this.importImagesUrlList = importImagesUrlListl;
    }

    @Override
    public String toString() {
        return "OfferRegistration{" +
                "offer=" + offer +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", importImagesUrlListl=" + importImagesUrlList +
                '}';
    }
}
