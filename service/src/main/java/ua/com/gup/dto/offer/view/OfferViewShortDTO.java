package ua.com.gup.dto.offer.view;

import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.dto.offer.OfferAddressDTO;
import ua.com.gup.dto.offer.OfferLandsDTO;

public class OfferViewShortDTO extends OfferViewBaseDTO {

    @ApiModelProperty(position = 60)
    private OfferAddressDTO address;

    @ApiModelProperty(position = 150)
    private OfferLandsDTO lands;

    public OfferAddressDTO getAddress() {
        return address;
    }

    public void setAddress(OfferAddressDTO address) {
        this.address = address;
    }

    public OfferLandsDTO getLands() {
        return lands;
    }

    public void setLands(OfferLandsDTO lands) {
        this.lands = lands;
    }
}
