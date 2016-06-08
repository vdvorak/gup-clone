package ua.com.itproekt.gup.oauth2;

import org.springframework.http.HttpEntity;
import org.springframework.security.oauth2.provider.error.OAuth2ExceptionRenderer;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by Zver on 26.02.2016.
 */
public class GupOAuth2ExceptionRenderer implements OAuth2ExceptionRenderer {
    @Override
    public void handleHttpEntityResponse(HttpEntity<?> httpEntity, ServletWebRequest servletWebRequest) throws Exception {

    }
}
