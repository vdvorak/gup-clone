package ua.com.gup.rent.model.mongo.category.attribute;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.gup.common.model.enumeration.CommonCategoryAttributeType;
import ua.com.gup.rent.model.rent.category.RentOfferCategoriesSort;
import ua.com.gup.rent.model.rent.category.attribute.RentOfferCategoryAttributeValue;
import ua.com.gup.rent.model.rent.category.attribute.validator.RentOfferCategoryAttributeValidator;

import java.io.Serializable;
import java.util.*;

@Document(collection = RentOfferCategoryAttribute.COLLECTION_NAME)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class RentOfferCategoryAttribute implements Serializable {
    public static final String COLLECTION_NAME = "rent.category_attribute";
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private int code;
    private boolean active;
    private String key;
    private Map<String, String> title = new HashMap<>();
    private Map<String, String> unit = new HashMap<>();
    private Set<Integer> categories = new HashSet<>();
    private LinkedHashSet<RentOfferCategoriesSort> categories_sort = new LinkedHashSet<>();
    private CommonCategoryAttributeType type;
    private RentOfferCategoryAttributeValidator validator;
    private LinkedHashSet<RentOfferCategoryAttributeValue> values = new LinkedHashSet<>();
    private boolean privateAttr;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RentOfferCategoryAttribute that = (RentOfferCategoryAttribute) o;

        if (code != that.code) return false;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + code;
        return result;
    }
}