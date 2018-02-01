package ua.com.gup.rent.service.dto.rent.offer.profile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import ua.com.gup.common.model.address.Address;
import ua.com.gup.common.model.enumeration.CommonUserType;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
public abstract class ProfileDTO {

    @JsonProperty("firstname")
    protected String firstName;

    @JsonProperty("lastname")
    protected String lastName;

    @JsonProperty("type")
    protected CommonUserType userType;

    @JsonProperty("email")
    protected String email;

    @JsonProperty("address")
    protected Address address;

        /*@JsonProperty("imgId")
    protected String imageId;*/

    /*@JsonProperty("socialList")
    protected Set<RentOfferProfileContactList> socialsList;*/

    @JsonProperty("status")
    protected String status;

    /*@JsonProperty("contact")
    protected Contact contact;*/

}
