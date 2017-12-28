package ua.com.gup.dto.offer.view;

import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.common.dto.CommonAddressDTO;
import ua.com.gup.dto.offer.OfferLandsDTO;

public class OfferViewShortDTO extends OfferViewBaseDTO {

    @ApiModelProperty(position = 60)
    private CommonAddressDTO address;

    @ApiModelProperty(position = 150)
    private OfferLandsDTO lands;

    public CommonAddressDTO getAddress() {
        return address;
    }

    public void setAddress(CommonAddressDTO address) {
        this.address = address;
    }

    public OfferLandsDTO getLands() {
        return lands;
    }

    public void setLands(OfferLandsDTO lands) {
        this.lands = lands;
    }
}
