package ua.com.itproekt.gup.model.offer;

import java.util.List;
import java.util.Set;

/**
 * Class contains information relating to moderator activity to the offer.
 *
 * @author Kobylyatskyy Alexander
 */
public class OfferModerationReports {

    String moderatorId; // moderator ID whose last modified profile
    Long lastModifiedDate; // last modified date when moderator modified profile
    ModerationStatus moderationStatus; // moderation status

    List<OfferRefusalReason> offerRefusalReasons; // the reason why moderator refuse offer

    Set<OfferModifiedField> offerModifiedFieldLIst; // the list of the last modified fields





    public String getModeratorId() {
        return moderatorId;
    }

    public void setModeratorId(String moderatorId) {
        this.moderatorId = moderatorId;
    }

    public Long getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Long lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public List<OfferRefusalReason> getOfferRefusalReasonses() {
        return offerRefusalReasons;
    }

    public void setOfferRefusalReasonses(List<OfferRefusalReason> offerRefusalReasonses) {
        this.offerRefusalReasons = offerRefusalReasonses;
    }

    public ModerationStatus getModerationStatus() {
        return moderationStatus;
    }

    public void setModerationStatus(ModerationStatus moderationStatus) {
        this.moderationStatus = moderationStatus;
    }

    public List<OfferRefusalReason> getOfferRefusalReasons() {
        return offerRefusalReasons;
    }

    public void setOfferRefusalReasons(List<OfferRefusalReason> offerRefusalReasons) {
        this.offerRefusalReasons = offerRefusalReasons;
    }

    public Set<OfferModifiedField> getOfferModifiedFieldLIst() {
        return offerModifiedFieldLIst;
    }

    public void setOfferModifiedFieldLIst(Set<OfferModifiedField> offerModifiedFieldLIst) {
        this.offerModifiedFieldLIst = offerModifiedFieldLIst;
    }

    @Override
    public String toString() {
        return "OfferModerationReports{" +
                "moderatorId='" + moderatorId + '\'' +
                ", lastModifiedDate=" + lastModifiedDate +
                ", moderationStatus=" + moderationStatus +
                ", offerRefusalReasons=" + offerRefusalReasons +
                ", offerModifiedFieldLIst=" + offerModifiedFieldLIst +
                '}';
    }
}
