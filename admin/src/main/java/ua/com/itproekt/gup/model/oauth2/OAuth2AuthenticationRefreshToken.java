/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package ua.com.itproekt.gup.model.oauth2;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.io.Serializable;
import java.util.Date;


@Document(collection = "oauth2_refresh_token")
public class OAuth2AuthenticationRefreshToken implements Serializable {

    @Indexed
    private String id;
    @Indexed
    private final String tokenId;
    private final OAuth2RefreshToken oAuth2RefreshToken;
    private final OAuth2Authentication authentication;

    @Indexed(expireAfterSeconds = 60*60*24*31)
    private Date createdDate = new Date();

    public OAuth2AuthenticationRefreshToken(OAuth2RefreshToken oAuth2RefreshToken, OAuth2Authentication authentication) {
        this.oAuth2RefreshToken = oAuth2RefreshToken;
        this.authentication = authentication;
        this.tokenId = oAuth2RefreshToken.getValue();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTokenId() {
        return tokenId;
    }

    public OAuth2RefreshToken getoAuth2RefreshToken() {
        return oAuth2RefreshToken;
    }

    public OAuth2Authentication getAuthentication() {
        return authentication;
    }
}
