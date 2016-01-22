package ua.com.itproekt.gup.model.tender.doer;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Комп2 on 09.11.2015.
 */
@Document(collection = "doerRecall")
public class Recall {

    public enum Mark{LIKE,DISLIKE}
    private String id;
    private String authorId;
    private String body;
    private long createTime;
    private Mark mark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Recall{" +
                "id='" + id + '\'' +
                ", authorId='" + authorId + '\'' +
                ", body='" + body + '\'' +
                ", createTime=" + createTime +
                ", mark=" + mark +
                '}';
    }
}
