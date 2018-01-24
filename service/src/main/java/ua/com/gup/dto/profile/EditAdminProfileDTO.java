package ua.com.gup.dto.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.com.gup.mongo.composition.domain.profile.Profile;

import java.util.Set;

public class EditAdminProfileDTO extends EditProfileDTO {
    @JsonProperty("roles")
    private Set<String> roles;

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    @Override
    public Profile updateModel(Profile profile) {
        profile.setUserRoles(this.roles);
        return super.updateModel(profile);
    }
}
