package ua.com.itproekt.gup.service;

import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;

import java.util.*;

/**
 * Created by Zver on 05.01.2016.
 */
public class OAuth2AuthenticationReadConverter implements Converter<DBObject, OAuth2Authentication> {

    @Override
    public OAuth2Authentication convert(DBObject source) {
        DBObject storedRequest = (DBObject)source.get("storedRequest");
        OAuth2Request oAuth2Request = new OAuth2Request((Map<String, String>)storedRequest.get("requestParameters"),
                (String)storedRequest.get("clientId"), null, true, new HashSet((List)storedRequest.get("scope")),
                null, null, null, null);
        DBObject userAuthorization = (DBObject)source.get("userAuthentication");
//        Object principal = getPrincipalObject(userAuthorization.get("principal"));
        Object principal = userAuthorization.get("principal");
        Authentication userAuthentication = new UsernamePasswordAuthenticationToken(principal,
                (String)userAuthorization.get("credentials"), getAuthorities((List) userAuthorization.get("authorities")));

        OAuth2Authentication authentication = new OAuth2Authentication(oAuth2Request, userAuthentication);
        return authentication;
    }

    private Object getPrincipalObject(Object principal) {
//  !!! principalDBObject.password == null - exception
//        if(principal instanceof DBObject) {
//            DBObject principalDBObject = (DBObject)principal;
//            LoggedUser user = new LoggedUser(principalDBObject);
//            return user;
//        } else {
        System.err.println("**** getPrincipalObject.else");

        return principal;
//        }
    }

    private Collection<GrantedAuthority> getAuthorities(List<Map<String, String>> authorities) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>(authorities.size());
        for(Map<String, String> authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.get("role")));
        }
        return grantedAuthorities;
    }

}