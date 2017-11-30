package ua.com.gup.rent.dto.rentobject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.multipart.MultipartFile;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateRentObjectDTO extends RentObjectDTO {

    private Integer category;
    private ua.com.gup.rent.dto.RentPriceDTO price;
    private MultipartFile[] images;

    public CreateRentObjectDTO() {
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public ua.com.gup.rent.dto.RentPriceDTO getPrice() {
        return price;
    }

    public void setPrice(ua.com.gup.rent.dto.RentPriceDTO price) {
        this.price = price;
    }

    public MultipartFile[] getImages() {
        return images;
    }

    public void setImages(MultipartFile[] images) {
        this.images = images;
    }
}
