package ua.com.itproekt.gup.model.offer.paidservices;


import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class PaidServices {

    private Long isMarked; // marked offer forever
    private Long isUrgent;   // marked offer with "urgent"
    private Long isCheaper;  // marked offer with "cheaper"
    private Long lastPaidUpdateDate; // if offer must be on the top, value of this variable must be from the future

    public PaidServices setLastUpdateDateToCurrentDate() {
        this.lastPaidUpdateDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return this;
    }


    public Long getIsMarked() {
        return isMarked;
    }

    public PaidServices setIsMarked(Long isMarked) {
        this.isMarked = isMarked;
        return this;
    }

    public Long getIsUrgent() {
        return isUrgent;
    }

    public PaidServices setIsUrgent(Long isUrgent) {
        this.isUrgent = isUrgent;
        return this;
    }

    public Long getIsCheaper() {
        return isCheaper;
    }

    public PaidServices setIsCheaper(Long isCheaper) {
        this.isCheaper = isCheaper;
        return this;
    }

    public Long getLastPaidUpdateDate() {
        return lastPaidUpdateDate;
    }

    public PaidServices setLastPaidUpdateDate(Long lastPaidUpdateDate) {
        this.lastPaidUpdateDate = lastPaidUpdateDate;
        return this;
    }

    @Override
    public String toString() {
        return "PaidServices{" +
                "isMarked=" + isMarked +
                ", isUrgent=" + isUrgent +
                ", isCheaper=" + isCheaper +
                ", lastPaidUpdateDate=" + lastPaidUpdateDate +
                '}';
    }
}
