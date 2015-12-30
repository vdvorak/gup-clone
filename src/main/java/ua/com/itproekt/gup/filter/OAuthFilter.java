package ua.com.itproekt.gup.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class OAuthFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        System.err.println("* servletRequest: " + servletRequest);
//        if (servletRequest != null) {
            HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
            System.err.println("* httpServletRequest: " + httpServletRequest);
            FilteredRequest filteredRequest = new FilteredRequest(httpServletRequest);
            System.err.println("* filteredRequest: " + filteredRequest);

            System.err.println("** httpServletRequest.getCookies()" + Arrays.toString(httpServletRequest.getCookies()));
            if (httpServletRequest.getCookies() != null) {
                for (Cookie cookie : httpServletRequest.getCookies()) {
                    System.err.println("Cookie : " + cookie.getName() + ":" + cookie.getValue());

                    if ("authToken".equals(cookie.getName())) {
                        String[] paramValues = new String[]{cookie.getValue()};

                        Map<String, String[]> additionalParams = new HashMap<>();
                        additionalParams.put("access_token", paramValues);

                        filteredRequest.addParameters(additionalParams);
                    }
                }
            }

            chain.doFilter(filteredRequest, servletResponse);
            System.err.println("chain.doFilter(filteredRequest, servletResponse)  filteredRequest: " + filteredRequest);
//        } else {
//            chain.doFilter(servletRequest, servletResponse);
//        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
