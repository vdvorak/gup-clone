package ua.com.gup.domain.seosequence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
* @author
*/

@Document(collection = PublicProfileSequence.COLLECTION_NAME)
public class PublicProfileSequence {
    public static final String COLLECTION_NAME = "users.sequences";

    @Id
    private String id;
    private Long sequence;

    public PublicProfileSequence(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }
}
