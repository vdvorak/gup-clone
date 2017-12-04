package ua.com.gup.rent.model.rent.category.attribute;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class RentCategoryAttributeBaseValue implements Serializable {

    public RentCategoryAttributeBaseValue(){
        privateAttr = false;
    }

    private int code;
    private Map<String, String> title = new HashMap<>();
    private Map<String, String> unit = new HashMap<>();
    private RentCategoryAttributeType type;
    private boolean privateAttr;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Map<String, String> getTitle() {
        return title;
    }

    public void setTitle(Map<String, String> title) {
        this.title = title;
    }

    public Map<String, String> getUnit() {
        return unit;
    }

    public void setUnit(Map<String, String> unit) {
        this.unit = unit;
    }

    public RentCategoryAttributeType getType() {
        return type;
    }

    public void setType(RentCategoryAttributeType type) {
        this.type = type;
    }

    public boolean isPrivateAttr() {
        return privateAttr;
    }

    public void setPrivateAttr(boolean privateAttr) {
        this.privateAttr = privateAttr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RentCategoryAttributeBaseValue that = (RentCategoryAttributeBaseValue) o;

        if (code != that.code) return false;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        int result = code;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}