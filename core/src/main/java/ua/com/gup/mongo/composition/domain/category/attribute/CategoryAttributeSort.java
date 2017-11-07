package ua.com.gup.mongo.composition.domain.category.attribute;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = CategoryAttributeSort.COLLECTION_NAME)
public class CategoryAttributeSort implements Serializable {

    public static final String COLLECTION_NAME = "category_attribute_sort";

    @Id
    private String id;
    private int attribute_code;
    private int category_code;
    private int order;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAttribute_code() {
        return attribute_code;
    }

    public void setAttribute_code(int attribute_code) {
        this.attribute_code = attribute_code;
    }

    public int getCategory_code() {
        return category_code;
    }

    public void setCategory_code(int category_code) {
        this.category_code = category_code;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryAttributeSort categoryAttributeSort = (CategoryAttributeSort) o;

        if (attribute_code != categoryAttributeSort.attribute_code) {
            return false;
        }
        return id != null ? id.equals(categoryAttributeSort.id) : categoryAttributeSort.id == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result +  attribute_code;
        return result;
    }
}
