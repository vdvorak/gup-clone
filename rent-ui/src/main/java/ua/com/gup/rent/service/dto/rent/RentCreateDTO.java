package ua.com.gup.rent.service.dto.rent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.multipart.MultipartFile;
import ua.com.gup.rent.service.dto.period.RentPeriodDTO;
import ua.com.gup.rent.service.dto.price.RentPriceDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentCreateDTO extends RentDTO {

    private Integer category;
    private RentPriceDTO price;
    private MultipartFile[] images;
    private RentPeriodDTO period;

    public RentCreateDTO() {
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public RentPriceDTO getPrice() {
        return price;
    }

    public void setPrice(RentPriceDTO price) {
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
