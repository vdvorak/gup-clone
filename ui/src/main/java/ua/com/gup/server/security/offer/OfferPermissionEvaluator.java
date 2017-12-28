/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.gup.server.security.offer;

import java.util.Set;

import ua.com.gup.common.model.enumeration.CommonUserRole;
import ua.com.gup.server.security.ObjectPermissionEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.gup.mongo.composition.domain.offer.Offer;
import ua.com.gup.server.service.OfferService;

public abstract class OfferPermissionEvaluator extends ObjectPermissionEvaluator {

    protected static final String OWNER_PROPERTY = "authorId";
    @Autowired
    protected OfferService offerService;

    protected String offerId;

    public OfferPermissionEvaluator(String offerId, String userId, Set<CommonUserRole> authorities ) {
        super(userId, authorities);
        this.offerId = offerId;
    }

    @Override
    public String getObjectId() {
        return offerId;
    }

    @Override
    public String getCollectionName() {
        return Offer.COLLECTION_NAME;
    }

    @Override
    public String getOwnerPropertyName() {
        return OWNER_PROPERTY;
    }

    /*
        Check if moderator belongs to offer.
        Just a stub. 
    **/
    protected boolean isModeratorOwner() {
        return true;
    }

}
