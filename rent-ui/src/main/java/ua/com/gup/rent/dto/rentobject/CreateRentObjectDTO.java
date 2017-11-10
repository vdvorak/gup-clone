package ua.com.gup.rent.dto.rentobject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import ua.com.gup.rent.dto.RentImageDTO;
import ua.com.gup.rent.dto.PriceDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateRentObjectDTO extends RentObjectDTO {

    private Integer category;
    private PriceDTO price;
    private RentImageDTO[] images;

    public CreateRentObjectDTO() {
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public PriceDTO getPrice() {
        return price;
    }

    public void setPrice(PriceDTO price) {
        this.price = price;
    }

    public RentImageDTO[] getImages() {
        return images;
    }

    public void setImages(RentImageDTO[] images) {
        this.images = images;
    }
}
