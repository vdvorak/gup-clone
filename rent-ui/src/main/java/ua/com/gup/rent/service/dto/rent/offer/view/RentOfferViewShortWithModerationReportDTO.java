package ua.com.gup.rent.service.dto.rent.offer.view;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ua.com.gup.rent.model.rent.RentOfferModerationReport;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferViewShortWithModerationReportDTO extends RentOfferViewShortDTO {
    @ApiModelProperty(position = 130)
    private RentOfferModerationReport moderationReport;
}
