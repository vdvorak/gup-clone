package ua.com.gup.rent.service.dto.rent.offer.view;

import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferAddressDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferLandsDTO;

public class RentOfferViewShortDTO extends RentOfferViewBaseDTO {

    @ApiModelProperty(position = 60)
    private RentOfferAddressDTO address;

    @ApiModelProperty(position = 150)
    private RentOfferLandsDTO lands;

    public RentOfferAddressDTO getAddress() {
        return address;
    }

    public void setAddress(RentOfferAddressDTO address) {
        this.address = address;
    }

    public RentOfferLandsDTO getLands() {
        return lands;
    }

    public void setLands(RentOfferLandsDTO lands) {
        this.lands = lands;
    }
}
