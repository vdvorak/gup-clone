package ua.com.gup.config.filter;

import org.springframework.http.HttpHeaders;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GZIPFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String acceptEncoding = httpRequest.getHeader(HttpHeaders.ACCEPT_ENCODING);
        if (acceptEncoding != null) {
            //searching for 'gzip' in ACCEPT_ENCODING header
            if (acceptEncoding.indexOf("gzip") >= 0) {
                GZIPResponseWrapper gzipResponse = new GZIPResponseWrapper(httpResponse);
                chain.doFilter(request, gzipResponse);
                gzipResponse.finish();
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
