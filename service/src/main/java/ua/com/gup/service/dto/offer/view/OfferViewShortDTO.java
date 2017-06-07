package ua.com.gup.service.dto.offer.view;

import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.domain.offer.OfferStatistic;
import ua.com.gup.service.dto.offer.OfferAddressShortDTO;

public class OfferViewShortDTO extends OfferViewBaseDTO {

    @ApiModelProperty(position = 60)
    private OfferAddressShortDTO address;

    @ApiModelProperty(position = 140)
    private OfferStatistic statistic;

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
}
