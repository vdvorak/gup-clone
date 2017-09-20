package ua.com.gup.model.profiles;

/**
 * Class contains information relating to moderator activity to the profile.
 *
 * @author Kobylyatskyy Alexander
 */
public class ModerationReports {

    String moderatorId; // moderator ID whose last modified profile
    Long lastModifiedDate; // last modified date when moderator modified profile
    // ToDo ждём причины отказа


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

    @Override
    public String toString() {
        return "ModerationReports{" +
                "moderatorId='" + moderatorId + '\'' +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
