package ua.com.gup.config.oauth2;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.Collection;

public interface GUPTokenStore extends TokenStore {


    Collection<OAuth2AccessToken> findAccessTokensByUserName(String userName);

}
