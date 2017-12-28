package ua.com.gup.rent.repository.profile;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.domain.Sort;
import ua.com.gup.common.model.enumeration.CommonUserRole;
import ua.com.gup.rent.service.profile.ProfileFilter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
@Data
public class ProfileRepositoryFilter {
    private CommonUserRole role;

    private String username;

    private String email;

    private String publicId;

    private Set<CommonUserRole> userRoles;

    private String mainPhone;

    private String additionalPhone;

    private String managerUsername;

    private String managerPublicId;


    public ProfileRepositoryFilter(ProfileFilter filter) {
        this.username = filter.getUsername();
        this.email = filter.getEmail();
        this.publicId = filter.getPublicId();
        this.mainPhone = filter.getMainPhone();
        this.additionalPhone = filter.getAdditionalPhone();
        this.userRoles = filter.getUserRoles();
        this.managerUsername = filter.getManagerUsername();

    }


}
