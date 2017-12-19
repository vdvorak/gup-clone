package ua.com.gup.rent.service.dto.category.attribute;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.ToString;

import java.util.Map;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@EqualsAndHashCode(of={"key"})
@ToString
public class RentOfferCategoryAttributeValueDTO {
    private String key;
    private Map<String, String> title;

    public RentOfferCategoryAttributeValueDTO() {
        this.key = null;
        this.title = null;
    }
    public RentOfferCategoryAttributeValueDTO(String key, Map<String, String> title) {
        this.key = key;
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof RentOfferCategoryAttributeValueDTO)) return false;
        RentOfferCategoryAttributeValueDTO that = (RentOfferCategoryAttributeValueDTO) o;
        return Objects.equals(getKey(), that.getKey()) &&
                Objects.equals(getTitle(), that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey(), getTitle());
    }

    public String getKey() {

        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Map<String, String> getTitle() {
        return title;
    }

    public void setTitle(Map<String, String> title) {
        this.title = title;
    }
}
