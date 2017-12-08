/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.gup.server.security.offer;

import ua.com.gup.common.model.enumeration.CommonStatus;
import ua.com.gup.common.model.enumeration.CommonUserRole;

import java.util.Set;

public class OfferEditPermissionEvaluator extends OfferPermissionEvaluator {

    public OfferEditPermissionEvaluator(String offerId, String userId, Set<CommonUserRole> authorities) {
        super(offerId, userId, authorities);
    }

    @Override
    public Boolean hasAccess() {

        if (getAuthorities().contains(CommonUserRole.ROLE_USER)) {
            //if offer on moderation user can't edit
            if (offerService.existsByIdAndStatus(getObjectId(), CommonStatus.ON_MODERATION)) {
                return false;
            }
            return isOwner();

        } else if (getAuthorities().contains(CommonUserRole.ROLE_MODERATOR)) {
            return isModeratorOwner();
        }

        return false;
    }
    
}
