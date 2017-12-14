package ua.com.gup.rent.model.rent.category.attribute;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(of={"key"})
@Data
public class RentOfferCategoryAttributeValue implements Serializable {
    private String key;
    private Map<String, String> title;
    private Set<Integer> exceptCategory;
}
