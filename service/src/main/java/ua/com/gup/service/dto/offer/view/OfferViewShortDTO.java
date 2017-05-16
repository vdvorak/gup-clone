package ua.com.gup.service.dto.offer.view;

import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.service.dto.offer.OfferAddressShortDTO;

public class OfferViewShortDTO extends OfferViewBaseDTO {

    @ApiModelProperty(position = 60)
    private OfferAddressShortDTO address;

    public OfferAddressShortDTO getAddress() {
        return address;
    }

    public void setAddress(OfferAddressShortDTO address) {
        this.address = address;
    }
}
