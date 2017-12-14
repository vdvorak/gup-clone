package ua.com.gup.rent.service.dto.rent.offer.profile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import ua.com.gup.common.model.enumeration.CommonUserType;
import ua.com.gup.rent.model.rent.RentOfferAddress;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
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

}
