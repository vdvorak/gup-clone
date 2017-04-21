package ua.com.gup.service.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DTO for creation the Offer entity.
 */
@ApiModel(description = "Offer create DTO")
public class OfferCreateDTO extends OfferBaseDTO {

    @NotNull
    @Size(min = 2, max = 70, message = "The length of field 'title' should be in range 2-70")
    @ApiModelProperty(value = "Offer title, size[2;70]", required = true)
    private String title;

    private AddressDTO address;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}
