package ua.com.itproekt.gup.dto;

import ua.com.itproekt.gup.model.offer.Image;
import ua.com.itproekt.gup.model.offer.Offer;

import java.util.List;
import java.util.Set;

public class OfferRegistration {
    String selectedImageIndex; // index of the first image if it is exist
    String selectedImageType; // type of the first image if it is exist
    private Offer offer;
    private String username;
    private String lastname;
    private String email;
    private String password;
    private List<String> importImagesUrlList;
    private Set<String> contactPhones;


    private List<Image> images;

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


    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public Set<String> getContactPhones() {
        return contactPhones;
    }

    public void setContactPhones(Set<String> contactPhones) {
        this.contactPhones = contactPhones;
    }

    public String getSelectedImageIndex() {
        return selectedImageIndex;
    }

    public void setSelectedImageIndex(String selectedImageIndex) {
        this.selectedImageIndex = selectedImageIndex;
    }

    public String getSelectedImageType() {
        return selectedImageType;
    }

    public void setSelectedImageType(String selectedImageType) {
        this.selectedImageType = selectedImageType;
    }


    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "OfferRegistration{" +
                "selectedImageIndex='" + selectedImageIndex + '\'' +
                ", selectedImageType='" + selectedImageType + '\'' +
                ", offer=" + offer +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", importImagesUrlList=" + importImagesUrlList +
                ", contactPhones=" + contactPhones +
                ", images=" + images +
                '}';
    }
}
