package ua.com.gup.mongo.model.profiles;


import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = LockRemoteIP.COLLECTION_NAME)
public class LockRemoteIP {
    public static final String COLLECTION_NAME = "lock_ip";

    @Id
    private String id;
    private String ip;

    private Long lastLoginDate;
    private Long lastTryLoginDate;
    private int countTryLoginDate;
    private boolean isUnlockAccount;



    public LockRemoteIP setLastTryLoginDateEqualsToCurrentDate() {
        synchronized (this) {
            final int LIMIT_ATTEMPTS = 5,
                      BLOCK_TIME_INTERVAL = 600000,
                      CHECK_LOGIN_TIME = 60000;

            Long ldt = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
            Long deltaLdt;

            if(lastTryLoginDate != null) {
                deltaLdt = ldt - lastTryLoginDate;
            }else {
                deltaLdt = ldt;
            }

            //TODO: account is unlock == TRUE & time interval less one-minut
            if (isUnlockAccount && (deltaLdt < CHECK_LOGIN_TIME)) {
                countTryLoginDate++;
                //TODO: it was more than a five attemps - then block account
                if (LIMIT_ATTEMPTS < countTryLoginDate) {
                    isUnlockAccount = false;
                }
                lastTryLoginDate = ldt;
            } else if (lastTryLoginDate == null) { //TODO: then account was after lock (account is unlock == FALSE|TRUE) - time interval more one-minut
                lastTryLoginDate = ldt;
                isUnlockAccount = true;
                countTryLoginDate = 1;
            } else if (BLOCK_TIME_INTERVAL < deltaLdt) {
                lastTryLoginDate = ldt;
                isUnlockAccount = true;
                countTryLoginDate = 1;
            }
        }
        return this;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Long lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Long getLastTryLoginDate() {
        return lastTryLoginDate;
    }

    public void setLastTryLoginDate(Long lastTryLoginDate) {
        this.lastTryLoginDate = lastTryLoginDate;
    }

    public int getCountTryLoginDate() {
        return countTryLoginDate;
    }

    public void setCountTryLoginDate(int countTryLoginDate) {
        this.countTryLoginDate = countTryLoginDate;
    }

    public boolean isUnlockAccount() {
        return isUnlockAccount;
    }

    public void setUnlockAccount(boolean isUnlockAccount) {
        this.isUnlockAccount = isUnlockAccount;
    }
}
