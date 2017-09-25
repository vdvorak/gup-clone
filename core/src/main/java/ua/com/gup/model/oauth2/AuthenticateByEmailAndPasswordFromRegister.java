package ua.com.gup.model.oauth2;

import org.springframework.security.oauth2.common.OAuth2AccessToken;

import javax.servlet.http.HttpServletResponse;

public class AuthenticateByEmailAndPasswordFromRegister {

    public AuthenticateByEmailAndPasswordFromRegister(){
    }

    public AuthenticateByEmailAndPasswordFromRegister(HttpServletResponse httpServletResponse, OAuth2AccessToken oAuth2AccessToken){
        this.httpServletResponse = httpServletResponse;
        this.oAuth2AccessToken = oAuth2AccessToken;
    }

    private HttpServletResponse httpServletResponse;
    private OAuth2AccessToken oAuth2AccessToken;

    public HttpServletResponse getHttpServletResponse() {
        return httpServletResponse;
    }

    public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    public OAuth2AccessToken getOAuth2AccessToken() {
        return oAuth2AccessToken;
    }

    public void setOAuth2AccessToken(OAuth2AccessToken oAuth2AccessToken) {
        this.oAuth2AccessToken = oAuth2AccessToken;
    }

    @Override
    public String toString() {
        return "AuthenticateByEmailAndPasswordFromRegister{" +
                "httpServletResponse=" + httpServletResponse +
                ", oAuth2AccessToken=" + oAuth2AccessToken +
                '}';
    }
}
