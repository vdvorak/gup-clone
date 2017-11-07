package ua.com.gup.config.oauth2;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.Collection;

public interface GUPTokenStore extends TokenStore {
    
    default Collection<OAuth2AccessToken> findAccessTokensByUserName(String userName) {
        return null;
    }

    @Override
    default OAuth2Authentication readAuthentication(OAuth2AccessToken oAuth2AccessToken) {
        return null;
    }

    @Override
    default OAuth2Authentication readAuthentication(String s) {
        return null;
    }

    @Override
    default void storeAccessToken(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {

    }

    @Override
    default OAuth2AccessToken readAccessToken(String s) {
        return null;
    }

    @Override
    default void removeAccessToken(OAuth2AccessToken oAuth2AccessToken) {

    }

    @Override
    default void storeRefreshToken(OAuth2RefreshToken oAuth2RefreshToken, OAuth2Authentication oAuth2Authentication) {

    }

    @Override
    default OAuth2RefreshToken readRefreshToken(String s) {
        return null;
    }

    @Override
    default OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken oAuth2RefreshToken) {
        return null;
    }

    @Override
    default void removeRefreshToken(OAuth2RefreshToken oAuth2RefreshToken) {

    }

    @Override
    default void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken oAuth2RefreshToken) {

    }

    @Override
    default OAuth2AccessToken getAccessToken(OAuth2Authentication oAuth2Authentication) {
        return null;
    }

    @Override
    default Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String s, String s1) {
        return null;
    }

    @Override
    default Collection<OAuth2AccessToken> findTokensByClientId(String s) {
        return null;
    }
}
