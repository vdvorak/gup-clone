package ua.com.gup.config.oauth2;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import ua.com.gup.mongo.composition.domain.oauth2.OAuth2AuthenticationAccessToken;
import ua.com.gup.mongo.composition.domain.oauth2.OAuth2AuthenticationRefreshToken;
import ua.com.gup.repository.oauth2.OAuth2AccessTokenRepository;
import ua.com.gup.repository.oauth2.OAuth2RefreshTokenRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TokenStoreService implements GUPTokenStore {
    private final static Logger LOG = Logger.getLogger(TokenStoreService.class);

    @Autowired
    private OAuth2AccessTokenRepository oAuth2AccessTokenRepository;
    @Autowired
    private OAuth2RefreshTokenRepository oAuth2RefreshTokenRepository;

    private final AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();

    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
        return readAuthentication(token.getValue());
    }

    @Override
    public OAuth2Authentication readAuthentication(String tokenId) {
        return oAuth2AccessTokenRepository.findByTokenId(tokenId).getAuthentication();
    }

    @Override
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        OAuth2AuthenticationAccessToken oAuth2AuthenticationAccessToken = new OAuth2AuthenticationAccessToken(token,
                authentication, authenticationKeyGenerator.extractKey(authentication));
        oAuth2AccessTokenRepository.save(oAuth2AuthenticationAccessToken);
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenId) {
        OAuth2AuthenticationAccessToken token = oAuth2AccessTokenRepository.findByTokenId(tokenId);
        LOG.info(" AccessToken  : " + token);
        return (token != null) ? token.getoAuth2AccessToken() : null;
    }

    @Override
    public void removeAccessToken(OAuth2AccessToken accessToken) {
        OAuth2AuthenticationAccessToken authenticationAccessToken = oAuth2AccessTokenRepository.findByTokenId(accessToken.getValue());
        oAuth2AccessTokenRepository.delete(authenticationAccessToken);
    }

    @Override
    public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
        oAuth2RefreshTokenRepository.save(new OAuth2AuthenticationRefreshToken(refreshToken, authentication));
    }

    @Override
    public OAuth2RefreshToken readRefreshToken(String readRefreshToken) {
        OAuth2AuthenticationRefreshToken refreshToken = oAuth2RefreshTokenRepository.findByTokenId(readRefreshToken);
        return (refreshToken != null) ? refreshToken.getoAuth2RefreshToken() : null;
    }

    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
        return oAuth2RefreshTokenRepository.findByTokenId(token.getValue()).getAuthentication();
    }

    @Override
    public void removeRefreshToken(OAuth2RefreshToken refreshToken) {
        OAuth2AuthenticationRefreshToken token = oAuth2RefreshTokenRepository.findByTokenId(refreshToken.getValue());
        LOG.info("remove RefreshToken  : " + token);
        if (token != null) {
            oAuth2RefreshTokenRepository.delete(token);
        }
    }

    @Override
    public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
        List<OAuth2AuthenticationAccessToken> tokens = oAuth2AccessTokenRepository.findByRefreshToken(refreshToken.getValue());
        LOG.info("remove AccessTokenUsingRefreshToken OAuth2AuthenticationAccessToken  : " + tokens);
        if (tokens != null) {
            for (OAuth2AuthenticationAccessToken oAuth2AuthenticationAccessToken : tokens) {
                oAuth2AccessTokenRepository.delete(oAuth2AuthenticationAccessToken);
            }
        }
    }

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
        String authenticationId = authenticationKeyGenerator.extractKey(authentication);
        if (null == authenticationId) {
            return null;
        }
        OAuth2AuthenticationAccessToken token = oAuth2AccessTokenRepository.findByAuthenticationId(authenticationId);
        return token == null ? null : token.getoAuth2AccessToken();
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
        List<OAuth2AuthenticationAccessToken> accessTokens = oAuth2AccessTokenRepository.findByClientId(clientId);
        return extractAccessTokens(accessTokens);
    }

    @Override
    public Collection<OAuth2AccessToken> findAccessTokensByUserName(String userName) {
        List<OAuth2AuthenticationAccessToken> accessTokens = oAuth2AccessTokenRepository.findByUserName(userName);
        return extractAccessTokens(accessTokens);
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
        List<OAuth2AuthenticationAccessToken> accessTokens = oAuth2AccessTokenRepository.findByClientIdAndUserName(clientId, userName);
        return extractAccessTokens(accessTokens);
    }

    private Collection<OAuth2AccessToken> extractAccessTokens(List<OAuth2AuthenticationAccessToken> tokens) {
        return tokens.stream()
                .map(OAuth2AuthenticationAccessToken::getoAuth2AccessToken)
                .collect(Collectors.toList());
    }

}
