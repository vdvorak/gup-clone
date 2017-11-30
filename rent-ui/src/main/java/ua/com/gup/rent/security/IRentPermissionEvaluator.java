package ua.com.gup.rent.security;

import org.springframework.security.core.Authentication;

public interface IRentPermissionEvaluator {

    boolean hasPermission(Authentication auth, String targetId, Object permission);
}
