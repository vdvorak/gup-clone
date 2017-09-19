package ua.com.gup.util;

import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public final class Oauth2Util {
    public final static String ACCESS_TOKEN_COOKIE_NAME = "authToken";
    public final static String REFRESH_TOKEN_COOKIE_NAME = "refreshToken";
    public final static String SPRING_ACCESS_TOKEN_PARAM_NAME = "access_token";
    public final static int ACCESS_TOKEN_COOKIE_EXPIRES_IN_SECONDS = (int) TimeUnit.MINUTES.toSeconds(10);
    public final static int REFRESH_TOKEN_COOKIE_EXPIRES_IN_SECONDS = (int)TimeUnit.DAYS.toSeconds(30);

    public static OAuth2Request getOAuth2Request() {
        Map<String, String> requestParameters = new HashMap<>();
        String clientId = "7b5a38705d7b3562655925406a652e32";
        Set<String> scope = new HashSet<>();
        return new OAuth2Request(requestParameters, clientId, null, true, scope, null, null, null, null);
    }

    public static TokenRequest getTokenRequest() {
        Set<String> scope = new HashSet<>();
        HashMap<String, String> requestParameters = new HashMap<>();
        String clientId = "7b5a38705d7b3562655925406a652e32";
        String grantType = "password";

        return new TokenRequest(requestParameters, clientId, scope, grantType);
    }
}
