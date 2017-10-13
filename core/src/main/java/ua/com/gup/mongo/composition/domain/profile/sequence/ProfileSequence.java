package ua.com.gup.mongo.composition.domain.profile.sequence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
* @author
*/

@Document(collection = ProfileSequence.COLLECTION_NAME)
public class ProfileSequence {
    public static final String COLLECTION_NAME = "users.sequences";

    @Id
    private String id;
    private Long sequence;

    public ProfileSequence() {
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
