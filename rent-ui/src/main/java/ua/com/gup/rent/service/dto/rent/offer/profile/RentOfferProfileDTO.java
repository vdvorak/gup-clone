package ua.com.gup.rent.service.dto.rent.offer.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.com.gup.common.model.enumeration.CommonUserType;
import ua.com.gup.rent.model.mongo.user.RentOfferProfile;
import ua.com.gup.rent.model.rent.RentOfferAddress;

public abstract class RentOfferProfileDTO {

    @JsonProperty("firstname")
    protected String firstName;

    @JsonProperty("lastname")
    protected String lastName;

    @JsonProperty("type")
    protected CommonUserType userType;

    @JsonProperty("email")
    protected String email;

    @JsonProperty("executive")
    protected String executive;

    @JsonProperty("address")
    protected RentOfferAddress address;

    @JsonProperty("contactPerson")
    protected String contactPerson;

    @JsonProperty("imgId")
    protected String imageId;

    /*@JsonProperty("socialList")
    protected Set<RentOfferProfileContactList> socialsList;*/

    @JsonProperty("status")
    protected String status;

    /*@JsonProperty("contact")
    protected Contact contact;*/

    public RentOfferProfileDTO() {
    }

    public RentOfferProfileDTO(RentOfferProfile profile) {
        this.firstName = profile.getFirstname();
        this.lastName = profile.getLastname();
        this.userType = profile.getUserType();
        this.email = profile.getEmail();
        this.executive = profile.getExecutive();
        this.address = profile.getAddress();
        this.contactPerson = profile.getContactPerson();
        this.imageId = profile.getImgId();
      //  this.socialsList = profile.getContactList();
        this.status = profile.getStatus();
     //   this.contact = profile.getContact();
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

    public CommonUserType getUserType() {
        return userType;
    }

    public void setUserType(CommonUserType userType) {
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

    public RentOfferAddress getAddress() {
        return address;
    }

    public void setAddress(RentOfferAddress address) {
        this.address = address;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    /*public Set<RentOfferProfileContactList> getSocialsList() {
        return socialsList;
    }

    public void setSocialsList(Set<RentOfferProfileContactList> socialsList) {
        this.socialsList = socialsList;
    }*/

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

/*    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }*/


}
