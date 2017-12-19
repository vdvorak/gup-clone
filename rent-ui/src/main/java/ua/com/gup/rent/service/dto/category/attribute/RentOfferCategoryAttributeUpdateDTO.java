package ua.com.gup.rent.service.dto.category.attribute;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
//@Data
public class RentOfferCategoryAttributeUpdateDTO extends RentOfferCategoryAttributeCreateDTO {
    private String id;
    private int code;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RentOfferCategoryAttributeUpdateDTO)) return false;
        if (!super.equals(o)) return false;
        RentOfferCategoryAttributeUpdateDTO that = (RentOfferCategoryAttributeUpdateDTO) o;
        return getCode() == that.getCode() &&
                Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getCode());
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
