/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.itproekt.gup.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.itproekt.gup.model.OAuth2AuthenticationRefreshToken;


public interface OAuth2RefreshTokenRepository extends MongoRepository<OAuth2AuthenticationRefreshToken, String> {
    OAuth2AuthenticationRefreshToken findByTokenId(String tokenId);
}