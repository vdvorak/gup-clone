package ua.com.gup.domain.offer;


import ua.com.gup.domain.enumeration.OfferRefusalReason;

import java.time.LocalDateTime;
import java.util.List;


public class OfferModerationReport {

    private String moderatorId;

    private LocalDateTime lastModifiedDate;

    private List<OfferRefusalReason> refusalReasons;

    private String description;

    public String getModeratorId() {
        return moderatorId;
    }

    public void setModeratorId(String moderatorId) {
        this.moderatorId = moderatorId;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public List<OfferRefusalReason> getRefusalReasons() {
        return refusalReasons;
    }

    public void setRefusalReasons(List<OfferRefusalReason> refusalReasons) {
        this.refusalReasons = refusalReasons;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRefused() {
        return refusalReasons != null && refusalReasons.size() > 0;
    }
}
