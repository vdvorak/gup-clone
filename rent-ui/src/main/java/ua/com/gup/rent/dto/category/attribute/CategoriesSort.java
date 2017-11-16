package ua.com.gup.rent.dto.category.attribute;

import java.io.Serializable;

/**
 * @author Victor Dvorak
 **/
public class CategoriesSort implements Serializable{

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

        CategoriesSort that = (CategoriesSort) o;

        if (!code_category.equals(that.code_category)) return false;
        return order_category.equals(that.order_category);
    }

    @Override
    public int hashCode() {
        int result = code_category.hashCode();
        result = 31 * result + order_category.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CategoriesSort{" +
                "code_category=" + code_category +
                ", order_category=" + order_category +
                '}';
    }
}

