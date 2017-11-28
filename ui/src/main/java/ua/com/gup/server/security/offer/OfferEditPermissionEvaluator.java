/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.gup.server.security.offer;

import java.util.Set;
import ua.com.gup.mongo.model.enumeration.OfferStatus;
import ua.com.gup.mongo.model.enumeration.UserRole;

public class OfferEditPermissionEvaluator extends OfferPermissionEvaluator {

    public OfferEditPermissionEvaluator(String offerId, String userId, Set<UserRole> authorities) {
        super(offerId, userId, authorities);
    }

    @Override
    public Boolean hasAccess() {

        if (getAuthorities().contains(UserRole.ROLE_USER)) {
            //if offer on moderation user can't edit
            if (offerService.existsByIdAndStatus(getObjectId(), OfferStatus.ON_MODERATION)) {
                return false;
            }
            return isOwner();

        } else if (getAuthorities().contains(UserRole.ROLE_MODERATOR)) {
            return isModeratorOwner();
        }

        return false;
    }
    
}
