package ua.com.gup.rent.service.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ua.com.gup.common.GupLoggedUser;

/**
 * @author victor.dvorak
 **/
@Component
public class SpringSecurityUserAuditorAware implements AuditorAware<GupLoggedUser> {
    @Override
    public GupLoggedUser getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        return ((GupLoggedUser) authentication.getPrincipal());
    }
}

