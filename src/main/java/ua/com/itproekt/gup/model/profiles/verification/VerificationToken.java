package ua.com.itproekt.gup.model.profiles.verification;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Zver on 25.12.2015.
 */
@Document
public class VerificationToken {
    @Id
    private String id;
    private String userId;
    private final String token;
    private VerificationTokenType tokenType;
    private boolean verified;
    private Date expiryDate;

    public VerificationToken() {
        this.token = UUID.randomUUID().toString();
        this.expiryDate = calculateExpiryDate(DEFAULT_EXPIRY_TIME_IN_MINS);
    }

    public VerificationToken(String userId, VerificationTokenType tokenType, int expirationTimeInMinutes) {
        this();
        this.userId = userId;
        this.tokenType = tokenType;
        this.expiryDate = calculateExpiryDate(expirationTimeInMinutes);
    }

    public VerificationTokenType getTokenType() {
        return tokenType;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public String getToken() {
        return token;
    }

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        DateTime now = new DateTime();
        return now.plusMinutes(expiryTimeInMinutes).toDate();
    }

    public boolean hasExpired() {
        DateTime tokenDate = new DateTime(getExpiryDate());
        return tokenDate.isBeforeNow();
    }
}
