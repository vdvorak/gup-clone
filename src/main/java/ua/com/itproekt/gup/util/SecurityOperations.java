package ua.com.itproekt.gup.util;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import ua.com.itproekt.gup.model.login.LoggedUser;
import ua.com.itproekt.gup.model.profiles.Profile;

public final class SecurityOperations {

    public static boolean isUserLoggedIn() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (auth != null && auth.isAuthenticated());
    }

    public static String getLoggedUserId() {
        return getLoggedUser().getProfileId();
    }

    public static String getLoggedUserEmail() {
        return getLoggedUser().getUsername();
    }

    public static LoggedUser getLoggedUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) return null;
        Authentication authentication = context.getAuthentication();
        if (authentication == null) return null;
        LoggedUser loggedUser = (LoggedUser) authentication.getPrincipal();
        if (loggedUser == null) {
            throw new AccessDeniedException("You don't have the appropriate privileges to access this resource");
        }
        return loggedUser;
    }

    public static String getCurrentUserEmail() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) return null;
        Authentication authentication = context.getAuthentication();
        if (authentication == null) return null;
        return authentication.getName(); //get logged in username
    }
}
