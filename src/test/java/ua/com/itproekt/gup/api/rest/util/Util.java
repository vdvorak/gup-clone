package ua.com.itproekt.gup.api.rest.util;


import com.sun.corba.se.spi.servicecontext.UEInfoServiceContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import ua.com.itproekt.gup.dao.profile.ProfileRepository;
import ua.com.itproekt.gup.model.login.LoggedUser;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.UserRole;
import ua.com.itproekt.gup.service.profile.ProfilesService;

import java.nio.charset.Charset;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Util {
    public static String ADMIN_USER_EMAIL;
    public static String SUPPORT_USER_EMAIL;
    public static String MODERATOR_USER_EMAIL;
    public static String USER_EMAIL;
    public static String GENERAL_USER_PASSWORD;
    public static String ADMIN_USER_ID;
    public static String SUPPORT_USER_ID;
    public static String MODERATOR_USER_ID;
    public static String ANONYMOUS_USER_ID;
    public static String USER_ID;

    public static MediaType contentType;
    public static Principal principal;
    public static LoggedUser loggedUser;
    public static ObjectWriter ow;

    static {
        GENERAL_USER_PASSWORD = "admin";

        ADMIN_USER_EMAIL = "admin@abc.com";
        ADMIN_USER_ID = "777cdd7bf777777777777777";

        SUPPORT_USER_EMAIL = "support@abc.com";
        SUPPORT_USER_ID = "177cdd7bf777777777777777";

        MODERATOR_USER_EMAIL = "moderator@abc.com";
        MODERATOR_USER_ID = "277cdd7bf777777777777777";

        USER_EMAIL = "user@abc.com";
        USER_ID = "477cdd7bf777777777777777";

        contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
                MediaType.APPLICATION_JSON.getSubtype(),
                Charset.forName("utf8"));

        principal = new Principal() {
            @Override
            public String getName() {
                return ADMIN_USER_EMAIL;
            }
        };

        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
        loggedUser = new LoggedUser(ADMIN_USER_EMAIL, GENERAL_USER_PASSWORD, true, true, true, true, authorities, USER_ID);
        ow = new ObjectMapper().writer();
    }


    public static void createTestUsers(ProfilesService profilesService) {
        createTestProfile(ADMIN_USER_EMAIL, ADMIN_USER_ID, UserRole.ROLE_ADMIN, profilesService);
        createTestProfile(SUPPORT_USER_EMAIL, SUPPORT_USER_ID, UserRole.ROLE_SUPPORT, profilesService);
        createTestProfile(MODERATOR_USER_EMAIL, MODERATOR_USER_ID, UserRole.ROLE_MODERATOR, profilesService);
        createTestProfile(USER_EMAIL, USER_ID, UserRole.ROLE_USER, profilesService);
    }


    public static void createTestProfile(String email, String id, UserRole userRole, ProfilesService profilesService) {
        if (profilesService.findProfileByEmail(email) == null) {
            Profile profile = new Profile();
            profile.setId(id);
            profile.setEmail(email);
            profile.setPassword(GENERAL_USER_PASSWORD);
            profile.setUsername("CommonUser");
            Set<UserRole> userRoles = new HashSet<>();
            userRoles.add(userRole);
            profile.setUserRoles(userRoles);
            profilesService.createProfile(profile);
        }
    }


}
