package ua.com.gup.rent.security;

import org.springframework.security.core.Authentication;

public interface IPermissionEvaluator {

    boolean hasPermission(Authentication auth, String targetId, Object permission);
}
