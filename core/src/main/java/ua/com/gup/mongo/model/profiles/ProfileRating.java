package ua.com.gup.mongo.model.profiles;

import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * The type Profile rating is a part of Profile entity.
 */
public class ProfileRating {
    private String profileRatingId;
    private Long createdDate;
    private Integer earnPoints;
    private String shortDescription;
    private String longDescription;
    private String idAttachedFile;

    /**
     * Instantiates a new Profile rating ID.
     */
    public ProfileRating() {
        this.profileRatingId = new ObjectId().toString();
    }

    /**
     * Sets created date equals to current date.
     *
     * @return the created date equals to current date
     */
    public ProfileRating setCreatedDateEqualsToCurrentDate() {
        this.createdDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return this;
    }

    /**
     * Sets profile rating id.
     *
     * @param profileRatingId the profile rating id
     * @return the profile rating id
     */
    public ProfileRating setProfileRatingId(String profileRatingId) {
        this.profileRatingId = profileRatingId;
        return this;
    }

    /**
     * Sets created date.
     *
     * @param createdDate the created date
     * @return the created date
     */
    public ProfileRating setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    /**
     * Sets earn points.
     *
     * @param earnPoints the earn points
     * @return the earn points
     */
    public ProfileRating setEarnPoints(Integer earnPoints) {
        this.earnPoints = earnPoints;
        return this;
    }

    /**
     * Sets short description.
     *
     * @param shortDescription the short description
     * @return the short description
     */
    public ProfileRating setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
        return this;
    }

    /**
     * Sets long description.
     *
     * @param longDescription the long description
     * @return the long description
     */
    public ProfileRating setLongDescription(String longDescription) {
        this.longDescription = longDescription;
        return this;
    }

    /**
     * Sets id attached file.
     *
     * @param idAttachedFile the id attached file
     * @return the id attached file
     */
    public ProfileRating setIdAttachedFile(String idAttachedFile) {
        this.idAttachedFile = idAttachedFile;
        return this;
    }

    /**
     * Gets profile rating id.
     *
     * @return the profile rating id
     */
    public String getProfileRatingId() {
        return profileRatingId;
    }

    /**
     * Gets created date.
     *
     * @return the created date
     */
    public Long getCreatedDate() {
        return createdDate;
    }

    /**
     * Gets earn points.
     *
     * @return the earn points
     */
    public Integer getEarnPoints() {
        return earnPoints;
    }

    /**
     * Gets short description.
     *
     * @return the short description
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Gets long description.
     *
     * @return the long description
     */
    public String getLongDescription() {
        return longDescription;
    }

    /**
     * Gets id attached file.
     *
     * @return the id attached file
     */
    public String getIdAttachedFile() {
        return idAttachedFile;
    }

    @Override
    public String toString() {
        return "ProfileRating{" +
                "profileRatingId='" + profileRatingId + '\'' +
                ", createdDate=" + createdDate +
                ", earnPoints=" + earnPoints +
                ", shortDescription='" + shortDescription + '\'' +
                ", longDescription='" + longDescription + '\'' +
                ", idAttachedFile='" + idAttachedFile + '\'' +
                '}';
    }
}