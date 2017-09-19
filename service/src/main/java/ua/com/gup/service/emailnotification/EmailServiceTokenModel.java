package ua.com.gup.service.emailnotification;


import org.apache.commons.codec.binary.Base64;

import java.io.Serializable;


public class EmailServiceTokenModel implements Serializable {

    private final String email;
    private final String token;
    private final VerificationTokenType tokenType;
    private final String hostNameUrl;

    public EmailServiceTokenModel(String email, VerificationToken token, String hostNameUrl)  {
        this.email = email;
        this.token = token.getToken();
        this.tokenType = token.getTokenType();
        this.hostNameUrl = hostNameUrl;
    }

    public EmailServiceTokenModel(String email, String token, VerificationTokenType verificationTokenType, String hostNameUrl)  {
        this.email = email;
        this.token = token;
        this.tokenType = verificationTokenType;
        this.hostNameUrl = hostNameUrl;
    }

    public String getEmail() {
        return email;
    }

    public String getEncodedToken() {
        return new String(Base64.encodeBase64(token.getBytes()));
    }

    public String getToken() {
        return token;
    }

    public VerificationTokenType getTokenType() {
        return tokenType;
    }

    public String getHostNameUrl() {
        return hostNameUrl;
    }
}

