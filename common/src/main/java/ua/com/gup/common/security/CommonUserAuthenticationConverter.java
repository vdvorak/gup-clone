package ua.com.gup.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import ua.com.gup.common.GupLoggedUser;
import ua.com.gup.common.model.enumeration.CommonUserRole;
import ua.com.gup.common.model.security.Function;
import ua.com.gup.common.service.UserRoleService;

import java.util.*;
import java.util.stream.Collectors;

public class CommonUserAuthenticationConverter implements UserAuthenticationConverter {

    private final String USERNAME = "user_name";
    private final String PROFILE_ID = "profile_id";
    private final String PUBLIC_ID = "public_id";
    private final String EMAIL = "email";
    private final String AUTHORITIES = "authorities";
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public Map<String, ?> convertUserAuthentication(Authentication userAuthentication) {
        return null;
    }

    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {
        if (map.containsKey(USERNAME)) {
            String username = (String) map.get(USERNAME);
            String profileId = (String) map.get(PROFILE_ID);
            String publicId = (String) map.get(PUBLIC_ID);
            String email = (String) map.get(EMAIL);
            List<String> authorities = (List<String>) map.get(AUTHORITIES);


            List<GrantedAuthority> buildUserAuthority = buildUserAuthority(authorities);

            GupLoggedUser user = buildUserForAuthentication(profileId, username, publicId, email, buildUserAuthority);
            return new UsernamePasswordAuthenticationToken(user, "N/A", buildUserAuthority);
        }
        return null;
    }

    private GupLoggedUser buildUserForAuthentication(String id, String username, String publicId, String email, List<GrantedAuthority> authorities) {
        return new GupLoggedUser(id, publicId, username, email, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(List<String> userRoles) {
        Set<Function> functions = new HashSet<>();
        for (String role : userRoles) {
            Collection<Function> roleFunctions = userRoleService.getUserFunctionsByRole(role);
            functions.addAll(roleFunctions);
        }

        return functions.stream()
                .map(f -> new SimpleGrantedAuthority(f.getName()))
                .collect(Collectors.toList());
    }

}
