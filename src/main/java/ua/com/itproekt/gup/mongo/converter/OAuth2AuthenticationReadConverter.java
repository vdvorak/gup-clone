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

import java.util.*;


public class OAuth2AuthenticationReadConverter implements Converter<DBObject, OAuth2Authentication> {

    @Override
    public OAuth2Authentication convert(DBObject source) {
        System.err.println("*** DBObject source" + source);
        DBObject storedRequest = (DBObject)source.get("storedRequest");
        OAuth2Request oAuth2Request = new OAuth2Request((Map<String, String>)storedRequest.get("requestParameters"),
                (String)storedRequest.get("clientId"), null, true, new HashSet((List)storedRequest.get("scope")),
                null, null, null, null);
        DBObject userAuthorization = (DBObject)source.get("userAuthentication");
        System.err.println("DBObject  userAuthorization" + userAuthorization);

        Object principal = getPrincipalObject(userAuthorization.get("principal"));
        System.err.println("Object principal  " + principal);

//        Object principal = userAuthorization.get("principal");
        Authentication userAuthentication = new UsernamePasswordAuthenticationToken(principal,
                (String)userAuthorization.get("credentials"), getAuthorities((List) userAuthorization.get("authorities")));
        System.err.println("Authentication userAuthentication " + userAuthentication);

        OAuth2Authentication authentication = new OAuth2Authentication(oAuth2Request, userAuthentication);
        System.err.println("gOAuth2Authentication authentication" + authentication);


        return authentication;
    }

    private Object getPrincipalObject(Object principal) {
//  !!! principalDBObject.password == null - exception
        System.err.println("getPrincipalObject  principal" + principal);
        if (principal instanceof DBObject) {
            System.err.println("getPrincipalObject  if");

            DBObject dboPr = (DBObject) principal;
            System.err.println("** username" + dboPr.get("username"));
            System.err.println("** password" + dboPr.get("password"));
            System.err.println("** " + dboPr.get("enabled"));
            System.err.println("** " + dboPr.get("accountNonExpired"));
            System.err.println("** " + dboPr.get("credentialsNonExpired"));
            System.err.println("** " + dboPr.get("accountNonLocked"));
            System.err.println("** " + dboPr.get("authorities"));
            System.err.println("** " + (List<? extends GrantedAuthority>)dboPr.get("authorities"));

            return new LoggedUser((DBObject) principal);
        } else {
            System.err.println("getPrincipalObject  else");
            return principal;
        }
    }

    public static Collection<GrantedAuthority> getAuthorities(List<Map<String, String>> authorities) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>(authorities.size());
        for(Map<String, String> authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.get("role")));
        }
        return grantedAuthorities;
    }
}