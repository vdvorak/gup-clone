package ua.com.gup.rent.service.dto.rent.offer.view;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferAddressDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferLandsDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferViewShortDTO extends RentOfferViewBaseDTO {
    @ApiModelProperty(position = 60)
    private RentOfferAddressDTO address;
    @ApiModelProperty(position = 150)
    private RentOfferLandsDTO lands;

}
