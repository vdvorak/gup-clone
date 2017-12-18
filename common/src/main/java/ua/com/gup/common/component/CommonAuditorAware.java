package ua.com.gup.common.component;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ua.com.gup.common.GupLoggedUser;


public abstract class CommonAuditorAware implements AuditorAware<GupLoggedUser> {

    @Override
    public GupLoggedUser getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        return ((GupLoggedUser) authentication.getPrincipal());
    }
}
