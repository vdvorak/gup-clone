/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.gup.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import ua.com.gup.common.GupLoggedUser;
import ua.com.gup.mongo.model.enumeration.UserRole;
import ua.com.gup.mongo.model.login.LoggedUser;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SalesUserAuthenticationConverter implements UserAuthenticationConverter {

    private final String USERNAME = "user_name";
    private final String PROFILE_ID = "profile_id";
    private final String PUBLIC_ID = "public_id";
    private final String EMAIL = "email";
    
    private final String AUTHORITIES = "authorities";

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
            
            Set<UserRole> collect = authorities.stream().map(as -> UserRole.valueOf(as)).collect(Collectors.toSet());
            List<GrantedAuthority> buildUserAuthority = buildUserAuthority(collect);

            GupLoggedUser user = buildUserForAuthentication(profileId,username,publicId, email, buildUserAuthority);
            return new UsernamePasswordAuthenticationToken(user, "N/A", buildUserAuthority);
        }
        return null;
    }

    private GupLoggedUser buildUserForAuthentication(String id, String username, String publicId, String email, List<GrantedAuthority> authorities) {
        return new GupLoggedUser(id, publicId, username, email, authorities);
    }
    
    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
        return userRoles.stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole.toString()))
                .collect(Collectors.toList());
    }

}
