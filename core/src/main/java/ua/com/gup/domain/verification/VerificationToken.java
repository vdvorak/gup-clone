package ua.com.gup.domain.verification;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.gup.model.VerificationTokenType;

import java.util.Date;
import java.util.UUID;


@Document(collection = "verification.token")
public class VerificationToken {
    @Transient
    @Value("${verification.token.defaultTimeToLive.inMinutes}")
    private int DEFAULT_EXPIRY_TIME_IN_MINS;
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

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        DateTime now = new DateTime();
        return now.plusMinutes(expiryTimeInMinutes).toDate();
    }

    public boolean hasExpired() {
        DateTime tokenDate = new DateTime(getExpiryDate());
        return tokenDate.isBeforeNow();
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setTokenType(VerificationTokenType tokenType) {
        this.tokenType = tokenType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
