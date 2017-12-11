package ua.com.gup.dto.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.mongo.model.enumeration.UserType;
import ua.com.gup.mongo.model.offer.Address;
import ua.com.gup.mongo.model.profiles.Contact;
import ua.com.gup.mongo.model.profiles.ProfileContactList;

import java.util.Set;

public abstract class ProfileDTO {

    @JsonProperty("firstname")
    protected String firstName;

    @JsonProperty("lastname")
    protected String lastName;

    @JsonProperty("type")
    protected UserType userType;

    @JsonProperty("email")
    protected String email;

    @JsonProperty("executive")
    protected String executive;

    @JsonProperty("address")
    protected Address address;

    @JsonProperty("contactPerson")
    protected String contactPerson;

    @JsonProperty("imageUrlLarge")
    protected String imageUrlLarge;
    
    @JsonProperty("imageUrlSmall")
    protected String imageUrlSmall;

    @JsonProperty("socialList")
    protected Set<ProfileContactList> socialsList;

    @JsonProperty("status")
    protected String status;

    @JsonProperty("contact")
    protected Contact contact;

    public ProfileDTO() {
    }

    public ProfileDTO(Profile profile) {
        this.firstName = profile.getFirstname();
        this.lastName = profile.getLastname();
        this.userType = profile.getUserType();
        this.email = profile.getEmail();
        this.executive = profile.getExecutive();
        this.address = profile.getAddress();
        this.contactPerson = profile.getContactPerson();
        this.imageUrlLarge = profile.imageLargeUrl();
        this.imageUrlSmall = profile.imageSmallUrl();
        this.socialsList = profile.getContactList();
        this.status = profile.getStatus();
        this.contact = profile.getContact();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExecutive() {
        return executive;
    }

    public void setExecutive(String executive) {
        this.executive = executive;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getImageUrlLarge() {
        return imageUrlLarge;
    }

    public void setImageUrlLarge(String imageUrlLarge) {
        this.imageUrlLarge = imageUrlLarge;
    }

    public String getImageUrlSmall() {
        return imageUrlSmall;
    }

    public void setImageUrlSmall(String imageUrlSmall) {
        this.imageUrlSmall = imageUrlSmall;
    }


    public Set<ProfileContactList> getSocialsList() {
        return socialsList;
    }

    public void setSocialsList(Set<ProfileContactList> socialsList) {
        this.socialsList = socialsList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }


}
