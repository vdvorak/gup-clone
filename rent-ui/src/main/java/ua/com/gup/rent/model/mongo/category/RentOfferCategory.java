package ua.com.gup.rent.model.mongo.category;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Document(collection = RentOfferCategory.COLLECTION_NAME)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class RentOfferCategory implements Serializable {
    public static final String COLLECTION_NAME = "rent.category";
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private int parent;
    @Indexed(unique = true)
    private int code;
    private boolean active;
    @Indexed(unique = true)
    private String key;
    @Size(min = 1, max = 8)
    private String color;
    @Indexed(unique = true)
    private int order;
    private Map<String, String> title = new HashMap<>();
    private Map<String, String> seoTitle = new HashMap<>();
    private Map<String, String> description = new HashMap<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RentOfferCategory)) return false;
        RentOfferCategory that = (RentOfferCategory) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
