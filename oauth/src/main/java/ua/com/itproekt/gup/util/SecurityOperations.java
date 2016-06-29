//package ua.com.itproekt.gup.util;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import ua.com.itproekt.gup.model.login.LoggedUser;
//
//public final class SecurityOperations {
//
//    public static boolean isUserLoggedIn() {
//        Authentication auth = getCtxAuthentication();
//        return (auth != null && auth.isAuthenticated());
//    }
//
//    public static String getLoggedUserId() {
//        return (getLoggedUser() == null) ? null : getLoggedUser().getProfileId();
//    }
//
//    @Deprecated
//    public static String getLoggedUserEmail() {
//        return (getLoggedUser() == null) ? null : getLoggedUser().getUsername();
//    }
//
//    public static LoggedUser getLoggedUser() {
//        Authentication auth = getCtxAuthentication();
//        return (auth == null) ? null : (LoggedUser) auth.getPrincipal();
//    }
//
//    public static String getCurrentUserEmail() {
//        return (getCtxAuthentication() == null) ? null : getCtxAuthentication().getName(); //get logged in username
//    }
//
//    public static Authentication getCtxAuthentication() {
//        return SecurityContextHolder.getContext().getAuthentication();
//    }
//}
