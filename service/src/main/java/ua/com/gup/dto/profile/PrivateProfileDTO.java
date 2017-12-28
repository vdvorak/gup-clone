package ua.com.gup.dto.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.common.model.mongo.Phone;

import java.util.List;
import java.util.stream.Collectors;

public class PrivateProfileDTO extends DetailProfileDTO {

    @JsonProperty("mainPhone")
    private Phone mainPhone;

    @JsonProperty("chatUID")
    private String chatUID;

    @JsonProperty("roles")
    private List<String> roles;

    public PrivateProfileDTO() {
    }

    public PrivateProfileDTO(Profile profile) {
        super(profile);
        this.mainPhone = profile.getMainPhone();
        this.chatUID = profile.getChatUID();
        this.roles = profile.getUserRoles().stream().map(userRole -> userRole.toString()).collect(Collectors.toList());
    }

    public Phone getMainPhone() {
        return mainPhone;
    }

    public void setMainPhone(Phone mainPhone) {
        this.mainPhone = mainPhone;
    }

    public String getChatUID() {
        return chatUID;
    }

    public void setChatUID(String chatUID) {
        this.chatUID = chatUID;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
