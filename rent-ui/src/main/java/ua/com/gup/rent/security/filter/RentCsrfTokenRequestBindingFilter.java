package ua.com.gup.rent.security.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RentCsrfTokenRequestBindingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(new CSRFRequestWrapper(request), response);
    }

    public class CSRFRequestWrapper extends HttpServletRequestWrapper {

        private static final String ANGULAR_COOKIE_NAME = "XSRF-TOKEN";
        private Cookie XSRF_COOKIE;

        public CSRFRequestWrapper(HttpServletRequest request) {
            super(request);
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (ANGULAR_COOKIE_NAME.equals(cookie.getName())) {
                        XSRF_COOKIE = cookie;
                        break;
                    }
                }
            }
        }

        public String getHeader(String name) {
            if ("X-XSRF-TOKEN".equals(name)) {
                return XSRF_COOKIE != null ? XSRF_COOKIE.getValue() : null;
            }
            return super.getHeader(name);
        }
    }
}
