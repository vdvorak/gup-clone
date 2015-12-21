package ua.com.itproekt.gup.util;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ua.com.itproekt.gup.model.login.LoggedUser;

public final class SecurityOperations {

    public static boolean isUserLoggedIn() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return !(auth instanceof AnonymousAuthenticationToken);
    }

    public static String getLoggedUserId() {
        return ((LoggedUser)SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal()).getProfileId();
    }

    public static String getLoggedUserEmail() {
        return ((LoggedUser)SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal()).getUsername();
    }
}
