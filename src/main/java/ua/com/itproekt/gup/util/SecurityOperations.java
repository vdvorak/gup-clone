package ua.com.itproekt.gup.util;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ua.com.itproekt.gup.model.login.LoggedUser;

public final class SecurityOperations {

    public static boolean isUserLoggedIn() {
        Authentication auth = getCtxAuthentication();
        return (auth != null && auth.isAuthenticated());
    }

    public static String getLoggedUserId() {
        return getLoggedUser().getProfileId();
    }

    @Deprecated
    public static String getLoggedUserEmail() {
        return getLoggedUser().getUsername();
    }

    public static LoggedUser getLoggedUser() {
        LoggedUser loggedUser = (LoggedUser) getCtxAuthentication().getPrincipal();
        if (loggedUser == null) {
            throw new AccessDeniedException("You don't have the appropriate privileges to access this resource");
        }
        return loggedUser;
    }

    public static String getCurrentUserEmail() {
        return getCtxAuthentication().getName(); //get logged in username
    }

    public static Authentication getCtxAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new AccessDeniedException("You don't have the appropriate privileges to access this resource");
        }

        return authentication;
    }
}
