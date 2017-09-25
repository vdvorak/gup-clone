/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package ua.com.gup.repository.oauth2;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.gup.domain.oauth2.OAuth2AuthenticationRefreshToken;


public interface OAuth2RefreshTokenRepository extends MongoRepository<OAuth2AuthenticationRefreshToken, String> {
    OAuth2AuthenticationRefreshToken findByTokenId(String tokenId);
}