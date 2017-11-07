package ua.com.gup.mongo.composition.domain.category;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = CategorySort.COLLECTION_NAME)
public class CategorySort implements Serializable {
    public static final String COLLECTION_NAME = "category_sort";

    @Id
    private String id;
    private int category_code;
    private int order;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

        CategorySort categorySort = (CategorySort) o;
        if ( category_code != categorySort.category_code) {
            return false;
        }

        return id.equalsIgnoreCase(categorySort.id);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + category_code;
        return result;
    }
}
