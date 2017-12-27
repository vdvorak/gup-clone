package ua.com.gup.repository.profile;

import ua.com.gup.common.model.enumeration.CommonUserRole;

import java.util.Set;

public class ProfileFilter {
    private String username;
    private String email;
    private String publicId;
    private Set<CommonUserRole> userRoles;
    private String mainPhone;
    private String additionalPhone;
}
