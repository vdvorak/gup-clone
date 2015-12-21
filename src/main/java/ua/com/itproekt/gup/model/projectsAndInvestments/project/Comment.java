package ua.com.itproekt.gup.model.projectsAndInvestments.project;

import org.bson.types.ObjectId;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Comment {
    private String cId;
    @Size(min = 5, max = 1000)
    private String comment;
    private String fromId;
    private String toId;
    private Long createdDate;

    public Comment() {
        this.cId = new ObjectId().toString();
    }

    public Comment setCreatedDateEqualsToCurrentDate() {
        this.createdDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return this;
    }

    //-------------------------------------

    public String getcId() {
        return cId;
    }

    public Comment setId(String cId) {
        this.cId = cId;
        return this;
    }

    public String getToId() {
        return toId;
    }

    public Comment setToId(String toId) {
        this.toId = toId;
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
}
