package ua.com.itproekt.gup.model.nace;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

/**
 * Created by Комп2 on 27.02.2016.
 */
@Document(collection = "nace")
public class NACE {
    private String id;
    private String name;
    private Set<String> transcript;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getTranscript() {
        return transcript;
    }

    public void setTranscript(Set<String> transcript) {
        this.transcript = transcript;
    }
}

