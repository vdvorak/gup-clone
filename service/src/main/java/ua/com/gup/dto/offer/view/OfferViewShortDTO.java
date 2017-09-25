package ua.com.gup.dto.offer.view;

import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.model.offer.OfferStatistic;
import ua.com.gup.dto.offer.OfferAddressShortDTO;
import ua.com.gup.dto.offer.OfferLandsDTO;

public class OfferViewShortDTO extends OfferViewBaseDTO {

    @ApiModelProperty(position = 60)
    private OfferAddressShortDTO address;

    @ApiModelProperty(position = 140)
    private OfferStatistic statistic;

    @ApiModelProperty(position = 150)
    private OfferLandsDTO lands;

    public OfferAddressShortDTO getAddress() {
        return address;
    }

    public void setAddress(OfferAddressShortDTO address) {
        this.address = address;
    }

    public OfferStatistic getStatistic() {
        return statistic;
    }

    public void setStatistic(OfferStatistic statistic) {
        this.statistic = statistic;
    }

    public OfferLandsDTO getLands() {
        return lands;
    }

    public void setLands(OfferLandsDTO lands) {
        this.lands = lands;
    }
}
