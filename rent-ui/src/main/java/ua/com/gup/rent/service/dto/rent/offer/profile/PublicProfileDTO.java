package ua.com.gup.rent.service.dto.rent.offer.profile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;
import ua.com.gup.common.model.mongo.Phone;
import ua.com.gup.rent.model.mongo.user.Profile;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PublicProfileDTO extends DetailProfileDTO {

    @JsonProperty("mainPhoneNumber")
    @Getter @Setter
    private String mainPhoneNumber;


    public PublicProfileDTO(Profile profile) {
        super(profile);
        //for publicProfile not show email user.
        this.setEmail("[PROTECTED]");
        Phone mainPhone = profile.getMainPhone();
        if (!StringUtils.isEmpty(mainPhone.getPhoneNumber())) {
            this.mainPhoneNumber = mainPhone.getPhoneNumber();
            if (Boolean.TRUE.equals(mainPhone.getHidden())) {
                this.mainPhoneNumber = this.mainPhoneNumber.replaceAll("(?s).", "*");
            }
        }

    }

}
