package ua.com.gup.rent.model.rent.category;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Victor Dvorak
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
//@Data
//@EqualsAndHashCode(of={"code_category,order_category"})
@ToString
public class RentOfferCategoriesSort implements Serializable {
    private Integer code_category;
    private Integer order_category;

    public Integer getCode_category() {
        return code_category;
    }

    public void setCode_category(Integer code_category) {
        this.code_category = code_category;
    }

    public Integer getOrder_category() {
        return order_category;
    }

    public void setOrder_category(Integer order_category) {
        this.order_category = order_category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RentOfferCategoriesSort that = (RentOfferCategoriesSort) o;

        if (code_category.equals(that.code_category) && order_category.equals(that.order_category)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = code_category.hashCode();
        result = 31 * result + order_category.hashCode();
        return result;
    }
}

