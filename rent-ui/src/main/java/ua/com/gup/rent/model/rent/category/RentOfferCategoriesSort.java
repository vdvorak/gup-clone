package ua.com.gup.rent.model.rent.category;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Victor Dvorak
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
//@Data
//@EqualsAndHashCode(of={"code_category,order_category"})
public class RentOfferCategoriesSort implements Serializable{
    private Integer code_category;
    private Integer order_category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RentOfferCategoriesSort)) return false;
        RentOfferCategoriesSort that = (RentOfferCategoriesSort) o;
        return Objects.equals(getCode_category(), that.getCode_category()) &&
                Objects.equals(getOrder_category(), that.getOrder_category());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode_category(), getOrder_category());
    }

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
}

