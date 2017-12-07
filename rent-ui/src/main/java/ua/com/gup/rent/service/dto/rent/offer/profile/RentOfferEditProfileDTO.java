package ua.com.gup.rent.service.dto.rent.offer.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.com.gup.rent.model.mongo.user.RentOfferProfile;
import ua.com.gup.rent.model.rent.profiles.phone.RentOfferPhone;

public class RentOfferEditProfileDTO extends RentOfferProfileDTO {

    @JsonProperty("mainPhone")
    private RentOfferPhone mainPhone;

    public RentOfferEditProfileDTO() {
    }

    public RentOfferPhone getMainPhone() {
        return mainPhone;
    }

    public void setMainPhone(RentOfferPhone mainPhone) {
        this.mainPhone = mainPhone;
    }


    public RentOfferProfile updateModel(RentOfferProfile profile) {
        profile.setFirstname(this.firstName);
        profile.setLastname(this.lastName);
        profile.setUserType(this.userType);

        //check email not Update if exists
        if (profile.getEmail().isEmpty() && !this.email.isEmpty()) {
            profile.setEmail(this.email);
        }

        profile.setExecutive(this.executive);
        profile.setAddress(this.address);
        profile.setContactPerson(this.contactPerson);
        profile.setImgId(this.imageId);
       // profile.setContactList(this.socialsList);
        profile.setStatus(this.status);
      //  profile.setContact(this.contact);
        profile.setMainPhone(this.mainPhone);
        return profile;
    }
}
