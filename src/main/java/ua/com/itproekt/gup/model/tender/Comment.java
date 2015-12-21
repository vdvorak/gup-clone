package ua.com.itproekt.gup.model.tender;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;


public class Comment {
    String id;
    String fatherId;
    List<String> childId;
    String globalFather;
    Long time;
    String body;

    public Comment() {
        time = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public List<String> getChildId() {
        return childId;
    }

    public void setChildId(List<String> childId) {
        this.childId = childId;
    }

    public String getGlobalFather() {
        return globalFather;
    }

    public void setGlobalFather(String globalFather) {
        this.globalFather = globalFather;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", fatherId='" + fatherId + '\'' +
                ", childId='" + childId + '\'' +
                ", globalFather='" + globalFather + '\'' +
                ", time=" + time +
                ", body='" + body + '\'' +
                '}';
    }
}
