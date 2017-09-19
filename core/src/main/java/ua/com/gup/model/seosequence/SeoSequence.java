package ua.com.itproekt.gup.model.seosequence;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sequence")
public class SeoSequence {

    @Id
    private String id;
    private long seq;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getSeoKey() {
        return seq;
    }

    public void setSeoKey(Long seoKey) {
        this.seq = seoKey;
    }

    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }

    @Override
    public String toString() {
        return "SeoSequence{" +
                "id='" + id + '\'' +
                ", seoKey=" + seq +
                '}';
    }

}
