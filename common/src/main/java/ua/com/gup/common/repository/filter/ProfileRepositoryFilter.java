package ua.com.gup.common.repository.filter;

import lombok.Data;
import ua.com.gup.common.dto.profile.manager.ManagerProfileFilter;

import java.util.Set;
@Data
public class ProfileRepositoryFilter {
    private String role;

    private String username;

    private String email;

    private String publicId;

    private Set<String> userRoles;

    private String mainPhone;

    private String additionalPhone;

    private String managerUsername;

    private String managerPublicId;


    public ProfileRepositoryFilter(ManagerProfileFilter filter) {
        this.username = filter.getUsername();
        this.email = filter.getEmail();
        this.publicId = filter.getPublicId();
        this.mainPhone = filter.getMainPhone();
        this.additionalPhone = filter.getAdditionalPhone();
        this.userRoles = filter.getUserRoles();
        this.managerUsername = filter.getManagerUsername();

    }


}
