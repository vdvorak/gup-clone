package ua.com.itproekt.gup.dto;


import ua.com.itproekt.gup.model.offer.Offer;

import java.util.List;
import java.util.Set;

public class OfferRegistration {
    private Offer offer;
    private String username;
    private String email;
    private String password;
    private List<String> importImagesUrlList;
    private Set<String> contactPhones;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getContactPhones() {
        return contactPhones;
    }

    public void setContactPhones(Set<String> contactPhones) {
        this.contactPhones = contactPhones;
    }

    @Override
    public String toString() {
        return "OfferRegistration{" +
                "offer=" + offer +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", importImagesUrlList=" + importImagesUrlList +
                ", contactPhones=" + contactPhones +
                '}';
    }
}
