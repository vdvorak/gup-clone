/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.gup.server.security.offer;

import ua.com.gup.common.model.enumeration.CommonUserRole;

import java.util.Set;

public class OfferDeletePermissionEvaluator extends OfferPermissionEvaluator {

    public OfferDeletePermissionEvaluator(String offerId, String userId, Set<CommonUserRole> authorities) {
        super(offerId, userId, authorities);
    }

    @Override
    public Boolean hasAccess() {
        return isOwner();
    }
}
