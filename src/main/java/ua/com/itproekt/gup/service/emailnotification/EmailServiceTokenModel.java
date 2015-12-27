package ua.com.itproekt.gup.service.emailnotification;


import org.apache.commons.codec.binary.Base64;
import ua.com.itproekt.gup.model.profiles.verification.VerificationToken;
import ua.com.itproekt.gup.model.profiles.verification.VerificationTokenType;

import java.io.Serializable;

/**
 * @version 1.0
 * @author: Iain Porter
 * @since 13/05/2013
 */
public class EmailServiceTokenModel implements Serializable {

    private final String emailAddress;
    private final String token;
    private final VerificationTokenType tokenType;
    private final String hostNameUrl;


    public EmailServiceTokenModel(String emailAddress, VerificationToken token, String hostNameUrl)  {
        this.emailAddress = emailAddress;
        this.token = token.getToken();
        this.tokenType = token.getTokenType();
        this.hostNameUrl = hostNameUrl;
    }

    public String getEmailAddress() {
        return emailAddress;
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

