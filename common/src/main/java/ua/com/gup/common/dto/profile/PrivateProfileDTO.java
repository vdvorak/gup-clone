package ua.com.gup.common.dto.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ua.com.gup.common.model.mongo.CommonProfile;
import ua.com.gup.common.model.mongo.Phone;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class PrivateProfileDTO extends DetailProfileDTO {

    @JsonProperty("mainPhone")
    private Phone mainPhone;

    @JsonProperty("chatUID")
    private String chatUID;

    @JsonProperty("roles")
    private Set<String> roles;

    public PrivateProfileDTO() {
    }

    public PrivateProfileDTO(CommonProfile profile) {
        super(profile);
        this.mainPhone = profile.getMainPhone();
        this.chatUID = profile.getChatUID();
        this.roles = profile.getUserRoles();
    }
}
