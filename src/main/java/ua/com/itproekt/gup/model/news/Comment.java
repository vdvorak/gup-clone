package ua.com.itproekt.gup.model.news;

import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;

public class Comment {
    private String cId;
    private Integer totalLikes;
    private Set<String> likedIds;
    private Long createdDate;

//    @Size(min = 5, max = 500)
    private String comment;
    private String fromId;
    private String toId;

    public Comment() {
        this.cId = new ObjectId().toString();
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + cId +
                ", comment='" + comment + '\'' +
                ", totalLikes=" + totalLikes +
                ", likedIds=" + likedIds +
                ", createdDate=" + createdDate +
                ", fromId='" + fromId + '\'' +
                ", toId='" + toId + '\'' +
                '}';
    }

    public Comment setCreatedDateEqualsToCurrentDate() {
        this.createdDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return this;
    }
    // -------------------------------------

    public Set<String> getLikedIds() {
        return likedIds;
    }

    public Comment setLikedIds(Set<String> likedIds) {
        this.likedIds = likedIds;
        return this;
    }

    public String getcId() {
        return cId;
    }

    public Comment setcId(String cId) {
        this.cId = cId;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Comment setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public Comment setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public String getFromId() {
        return fromId;
    }

    public Comment setFromId(String fromId) {
        this.fromId = fromId;
        return this;
    }

    public String getToId() {
        return toId;
    }

    public Comment setToId(String toId) {
        this.toId = toId;
        return this;
    }

    public Integer getTotalLikes() {
        return totalLikes;
    }

    public Comment setTotalLikes(Integer totalLikes) {
        this.totalLikes = totalLikes;
        return this;
    }
}
