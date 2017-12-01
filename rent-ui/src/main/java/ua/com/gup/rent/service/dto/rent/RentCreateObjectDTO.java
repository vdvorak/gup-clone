package ua.com.gup.rent.dto.rentobject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.multipart.MultipartFile;
import ua.com.gup.rent.dto.rentobject.price.RentObjectPriceDTO;
import ua.com.gup.rent.dto.rentobject.period.RentPeriodDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateRentObjectDTO extends RentObjectDTO {

    private Integer category;
    private RentObjectPriceDTO price;
    private MultipartFile[] images;
    private RentPeriodDTO period;

    public CreateRentObjectDTO() {
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public RentObjectPriceDTO getPrice() {
        return price;
    }

    public void setPrice(RentObjectPriceDTO price) {
        this.price = price;
    }

    public MultipartFile[] getImages() {
        return images;
    }

    public void setImages(MultipartFile[] images) {
        this.images = images;
    }

    public RentPeriodDTO getPeriod() {
        return period;
    }

    public void setPeriod(RentPeriodDTO period) {
        this.period = period;
    }
}
