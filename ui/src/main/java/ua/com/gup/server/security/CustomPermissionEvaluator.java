package ua.com.gup.server.security;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import ua.com.gup.mongo.composition.domain.offer.Offer;
import ua.com.gup.mongo.model.enumeration.UserRole;
import ua.com.gup.mongo.model.login.LoggedUser;
import ua.com.gup.server.security.offer.OfferChangeStatusPermissionEvaluator;
import ua.com.gup.server.security.offer.OfferDeletePermissionEvaluator;
import ua.com.gup.server.security.offer.OfferEditPermissionEvaluator;
import ua.com.gup.service.security.UserSecurityAccessService;

public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    protected UserSecurityAccessService accessService;

    @Override
    public boolean hasPermission(Authentication a, Object targetDomainObject, Object permission) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasPermission(Authentication a, Serializable objectId, String collectionName, Object o) {

        Set<UserRole> currentUserRoles = getCurrentUserRoles(a);

        if (currentUserRoles.contains(UserRole.ROLE_ANONYMOUS)) {
            return false;
        }

        if (currentUserRoles.contains(UserRole.ROLE_ADMIN)) {
            return true;
        }

        String objId = (String) objectId;
        LoggedUser user = (LoggedUser) a.getPrincipal();
        String userId = user.getProfileId();

        switch (collectionName) {
            case Offer.COLLECTION_NAME:

                ObjectPermissionEvaluater permissionEvaluater = null;
                Permission permission = Permission.valueOf((String) o);
                switch (permission) {
                    case CHANGE_STATUS:
                        permissionEvaluater = new OfferChangeStatusPermissionEvaluator(objId, userId, currentUserRoles);
                        break;
                    case EDIT:
                        permissionEvaluater = new OfferEditPermissionEvaluator(objId, userId, currentUserRoles);
                        break;
                    case DELETE:
                        permissionEvaluater = new OfferDeletePermissionEvaluator(objId, userId, currentUserRoles);
                        break;
                    default:
                        throw new UnsupportedOperationException("No permission validator: " + collectionName + "." + o);
                }
                permissionEvaluater.setAccessService(accessService);
                return permissionEvaluater.hasAccess();
            default:
                throw new UnsupportedOperationException("No permission validator:" + collectionName);
        }

    }

    private Set<UserRole> getCurrentUserRoles(Authentication a) {
        return a.getAuthorities().stream().map(u -> {
            return UserRole.valueOf(u.getAuthority());
        }).collect(Collectors.toSet());
    }
}
