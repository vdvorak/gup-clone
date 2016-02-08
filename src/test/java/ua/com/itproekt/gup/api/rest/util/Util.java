package ua.com.itproekt.gup.api.rest.util;


import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
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
    public static String USER_EMAIL;
    public static String USER_PASSWORD;
    public static String USER_ID;
    public static MediaType contentType;
    public static Principal principal;
    public static LoggedUser loggedUser;
    public static ObjectWriter ow;

    static {
        USER_EMAIL = "admin@abc.com";
        USER_PASSWORD = "admin";
        USER_ID = "777cdd7bf777777777777777";

        contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
                MediaType.APPLICATION_JSON.getSubtype(),
                Charset.forName("utf8"));

        principal = new Principal() {
            @Override
            public String getName() {
                return USER_EMAIL;
            }
        };

        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
        loggedUser = new LoggedUser(USER_EMAIL, USER_PASSWORD, true, true, true, true, authorities, USER_ID);
        ow = new ObjectMapper().writer();
    }

    public static void createTestProfile(String email, ProfilesService profilesService) {
        if (profilesService.findProfileByEmail(email) == null) {
                Profile profile = new Profile();
                profile.setId(USER_ID);
                profile.setEmail(email);
                profile.setPassword(USER_PASSWORD);
                profile.setUsername("MainAdmin");
                Set<UserRole> userRoles = new HashSet<>();
                userRoles.add(UserRole.ROLE_ADMIN);
                profile.setUserRoles(userRoles);
            profilesService.createProfile(profile);
        }
    }

}
