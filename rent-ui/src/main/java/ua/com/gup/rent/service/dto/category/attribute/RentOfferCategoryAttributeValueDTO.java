package ua.com.gup.rent.service.dto.category.attribute;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@EqualsAndHashCode(of={"key"})
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RentOfferCategoryAttributeValueDTO {

    private String key;
    private Map<String, String> title;

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
}
