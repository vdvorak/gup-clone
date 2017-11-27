package ua.com.gup.rent.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class RentUserAuthenticationConverter implements UserAuthenticationConverter {

    private final String USERNAME = "user_name";
    private final String PROFILE_ID = "profile_id";
    private final String PUBLIC_ID = "public_id";
    private final String EMAIL = "email";

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

            Collection<? extends GrantedAuthority> authorities = Collections.EMPTY_LIST;
            RentLoggedUser user = new RentLoggedUser(profileId, publicId, username, email);


            return new UsernamePasswordAuthenticationToken(user, "N/A", authorities);
        }
        return null;
    }

}
