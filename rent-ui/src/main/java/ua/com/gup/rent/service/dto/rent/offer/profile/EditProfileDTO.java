package ua.com.gup.rent.service.dto.rent.offer.profile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ua.com.gup.common.model.address.Address;
import ua.com.gup.common.model.enumeration.CommonUserType;
import ua.com.gup.common.model.mongo.Phone;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class EditProfileDTO extends ProfileDTO {

    @JsonProperty("mainPhone")
    private Phone mainPhone;

    public EditProfileDTO(String firstName, String lastName, CommonUserType userType, String email, Address address, String status) {
        super(firstName, lastName, userType, email, address, status);
    }


}
