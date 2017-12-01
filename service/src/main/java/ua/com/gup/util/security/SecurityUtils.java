package ua.com.gup.util.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import ua.com.gup.common.GupLoggedUser;
import ua.com.gup.mongo.model.enumeration.UserRole;

/**
 * Utility class for Spring Security.
 */

/**
 * @author vdvorak
 * @deprecated this class  used maybe anywhere but in future maybe delete!
 */
@Deprecated
public final class SecurityUtils {

    private SecurityUtils() {
    }

    /**
     * Get the id of the current user.
     *
     * @return the id of the current user
     */
    public static String getCurrentUserId() {
        if(isAuthenticated()){
            GupLoggedUser user = (GupLoggedUser) getCtxAuthentication().getPrincipal();
            return user.getId();
        }
        return null;
    }

    /**
     * Get the LoggedUser of the current user.
     *
     * @return the LoggedUser of the current user
     */

    public static GupLoggedUser getLoggedUser() {
        Authentication auth = getCtxAuthentication();
        return !isAuthenticated() ? null : (GupLoggedUser) auth.getPrincipal();
    }

    /**
     * Get the authentication.
     *
     * @return the Authentication
     */
    public static Authentication getCtxAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * Check if a user is authenticated.
     *
     * @return true if the user is authenticated, false otherwise
     */
    public static boolean isAuthenticated() {
        return !isCurrentUserInRole(UserRole.ROLE_ANONYMOUS);
    }

    /**
     * If the current user has a specific authority (security role).
     * <p>
     * <p>The name of this method comes from the isUserInRole() method in the Servlet API</p>
     *
     * @param authority the authority to check
     * @return true if the current user has the authority, false otherwise
     */
    public static boolean isCurrentUserInRole(String authority) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            return authentication.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(authority));
        }
        return false;
    }

    /**
     * If the current user has a specific authority (security role).
     * <p>
     * <p>The name of this method comes from the isUserInRole() method in the Servlet API</p>
     *
     * @param userRole the authority to check
     * @return true if the current user has the authority, false otherwise
     */
    public static boolean isCurrentUserInRole(UserRole userRole) {
        return isCurrentUserInRole(userRole.name());
    }

}
