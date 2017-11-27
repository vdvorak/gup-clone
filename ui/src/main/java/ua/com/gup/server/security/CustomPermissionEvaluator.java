package ua.com.gup.server.security;

import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import ua.com.gup.mongo.composition.domain.offer.Offer;
import ua.com.gup.mongo.model.enumeration.UserRole;
import ua.com.gup.mongo.model.login.LoggedUser;
import ua.com.gup.service.security.UserSecurityAccessService;
import ua.com.gup.util.security.SecurityUtils;

public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private UserSecurityAccessService accessService;

    @Override
    public boolean hasPermission(Authentication a, Object o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasPermission(Authentication a, Serializable objectId, String collectionName, Object o) {
        if (SecurityUtils.isCurrentUserInRole(UserRole.ROLE_ADMIN)) {
            return true;
        }
        switch (collectionName) {
            case Offer.COLLECTION_NAME:
                String userId = ((LoggedUser) a.getPrincipal()).getProfileId();
                return accessService.isOwner(userId, (String) objectId, Offer.COLLECTION_NAME, "authorId");
            default:
                return false;
        }

    }

}
