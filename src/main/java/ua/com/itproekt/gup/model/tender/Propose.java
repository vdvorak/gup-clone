package ua.com.itproekt.gup.model.tender;

import java.time.LocalDateTime;
import java.time.ZoneOffset;


public class Propose {
    String authorId;
    String body;
    Long time;
    Boolean hidden;

    public Propose() {
        time = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        hidden = true;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public void setTimeLocalDateTime(LocalDateTime time) {
        this.time = time.toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    public Boolean getHidden() {
        return hidden;
    }

    public boolean isHidden() {return hidden;}

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }
}
