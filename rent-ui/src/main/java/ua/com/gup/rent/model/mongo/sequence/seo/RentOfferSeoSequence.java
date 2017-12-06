package ua.com.gup.rent.model.mongo.sequence.seo;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rent.seo.sequence")
public class RentOfferSeoSequence {

    @Id
    private String id;
    private long seq;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }

    public Long getSeoKey() {
        return seq;
    }

    public void setSeoKey(Long seoKey) {
        this.seq = seoKey;
    }

    @Override
    public String toString() {
        return "RentOffer Seo Sequence{" +
                "id='" + id + '\'' +
                ", seoKey=" + seq +
                '}';
    }
}
