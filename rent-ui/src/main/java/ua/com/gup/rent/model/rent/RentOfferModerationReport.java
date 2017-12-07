package ua.com.gup.rent.model.rent;


import com.fasterxml.jackson.annotation.JsonFormat;
import ua.com.gup.rent.model.enumeration.RentOfferRefusalReason;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;


public class RentOfferModerationReport implements Serializable {

    private String moderatorId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private ZonedDateTime lastModifiedDate;

    private List<RentOfferRefusalReason> refusalReasons;

    private String description;

    public String getModeratorId() {
        return moderatorId;
    }

    public void setModeratorId(String moderatorId) {
        this.moderatorId = moderatorId;
    }

    public ZonedDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public List<RentOfferRefusalReason> getRefusalReasons() {
        return refusalReasons;
    }

    public void setRefusalReasons(List<RentOfferRefusalReason> refusalReasons) {
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
