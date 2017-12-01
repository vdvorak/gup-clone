package ua.com.gup.rent.repository.security;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Repository
//@Primary
public class SecurityContextRepositoryImpl implements SecurityContextRepository {
    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder httpRequestResponseHolder) {
        System.out.println("test");
        return null;
    }

    @Override
    public void saveContext(SecurityContext securityContext, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        System.out.println("test");
    }

    @Override
    public boolean containsContext(HttpServletRequest httpServletRequest) {
        System.out.println("test");
        return false;
    }
}
