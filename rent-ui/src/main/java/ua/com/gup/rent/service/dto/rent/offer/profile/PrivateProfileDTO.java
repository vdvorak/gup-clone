package ua.com.gup.rent.service.dto.rent.offer.profile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ua.com.gup.common.model.mongo.Phone;
import ua.com.gup.rent.model.mongo.user.Profile;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PrivateProfileDTO extends DetailProfileDTO {

    @JsonProperty("mainPhone")
    private Phone mainPhone;

    @JsonProperty("chatUID")
    private String chatUID;


    public PrivateProfileDTO(Profile profile) {
        super(profile);
        this.mainPhone = profile.getMainPhone();
        this.chatUID = profile.getChatUID();
    }

}
