/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.gup.server.security.offer;

import java.util.Set;
import ua.com.gup.mongo.model.enumeration.UserRole;

public class OfferDeletePermissionEvaluator extends OfferPermissionEvaluator {

    public OfferDeletePermissionEvaluator(String offerId, String userId, Set<UserRole> authorities) {
        super(offerId, userId, authorities);
    }

    @Override
    public Boolean hasAccess() {
        return isOwner();
    }
}
