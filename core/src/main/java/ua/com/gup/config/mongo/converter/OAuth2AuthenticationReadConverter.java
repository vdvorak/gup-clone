package ua.com.itproekt.gup.mongo.converter;

import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import ua.com.itproekt.gup.model.login.LoggedUser;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class OAuth2AuthenticationReadConverter implements Converter<DBObject, OAuth2Authentication> {

    public static Collection<GrantedAuthority> getAuthorities(List<Map<String, String>> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.get("role")))
                .collect(Collectors.toSet());
    }

    @Override
    public OAuth2Authentication convert(DBObject source) {
        DBObject storedRequest = (DBObject) source.get("storedRequest");
        OAuth2Request oAuth2Request = new OAuth2Request((Map<String, String>) storedRequest.get("requestParameters"),
                (String) storedRequest.get("clientId"), null, true, new HashSet((List) storedRequest.get("scope")),
                null, null, null, null);
        DBObject userAuthorization = (DBObject) source.get("userAuthentication");

        Object principal = getPrincipalObject(userAuthorization.get("principal"));

        Authentication userAuthentication = null;
        try {
            userAuthentication = new UsernamePasswordAuthenticationToken(principal, (String) userAuthorization.get("credentials"), getAuthorities((List) userAuthorization.get("authorities")));
        } catch (Exception e){
            userAuthentication = new UsernamePasswordAuthenticationToken(principal, getAuthorities((List) userAuthorization.get("authorities")));
        }

        return new OAuth2Authentication(oAuth2Request, userAuthentication);
    }

    private Object getPrincipalObject(Object principal) {
        if (principal instanceof DBObject) {
            return new LoggedUser((DBObject) principal);
        } else {
            return principal;
        }
    }
}