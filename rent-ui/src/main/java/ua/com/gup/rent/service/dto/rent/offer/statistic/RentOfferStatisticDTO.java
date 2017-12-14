package ua.com.gup.rent.service.dto.rent.offer.statistic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentOfferStatisticDTO {

    @ApiModelProperty(position = 10, example = "10")
    private Integer offerViews;

    @ApiModelProperty(position = 10, example = "20")
    private Integer offerPhonesViews;

}
