package ua.com.itproekt.gup.model.offer.paidservices;


import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class PaidServices {

    private Boolean isMarked; // marked offer forever
    private Boolean isUrgent; // marked offer with "urgent"
    private Boolean isCheaper; // marked offer with "cheaper"

    private Long lastPaidUpdateDate; // if offer must be on the top, value of this variable must be from the future

    public PaidServices setLastUpdateDateToCurrentDate() {
        this.lastPaidUpdateDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return this;
    }


    public Boolean getIsMarked() {
        return isMarked;
    }

    public PaidServices setIsMarked(Boolean isMarked) {
        this.isMarked = isMarked;
        return this;
    }

    public Boolean getIsUrgent() {
        return isUrgent;
    }

    public PaidServices setIsUrgent(Boolean isUrgent) {
        this.isUrgent = isUrgent;
        return this;
    }

    public Boolean getIsCheaper() {
        return isCheaper;
    }

    public PaidServices setIsCheaper(Boolean isCheaper) {
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
