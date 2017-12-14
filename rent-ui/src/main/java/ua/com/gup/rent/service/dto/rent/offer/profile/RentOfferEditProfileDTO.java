package ua.com.gup.rent.service.dto.rent.offer.profile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ua.com.gup.common.model.enumeration.CommonUserType;
import ua.com.gup.rent.model.mongo.user.RentOfferProfile;
import ua.com.gup.rent.model.rent.RentOfferAddress;
import ua.com.gup.rent.model.rent.profiles.phone.RentOfferPhone;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferEditProfileDTO extends RentOfferProfileDTO {

    @JsonProperty("mainPhone")
    private RentOfferPhone mainPhone;

    public RentOfferEditProfileDTO(String firstName, String lastName, CommonUserType userType, String email, String executive, RentOfferAddress address, String contactPerson, String imageId, String status) {
        super(firstName, lastName, userType, email, executive, address, contactPerson, imageId, status);
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
