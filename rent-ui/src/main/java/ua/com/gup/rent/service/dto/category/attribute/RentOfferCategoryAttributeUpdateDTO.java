package ua.com.gup.rent.service.dto.category.attribute;

public class RentOfferCategoryAttributeUpdateDTO extends RentOfferCategoryAttributeCreateDTO {

    private String id;

    private int code;

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
