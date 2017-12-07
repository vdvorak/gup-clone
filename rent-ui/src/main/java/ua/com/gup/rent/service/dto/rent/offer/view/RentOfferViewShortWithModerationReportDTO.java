package ua.com.gup.rent.service.dto.rent.offer.view;

import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.rent.model.rent.RentOfferModerationReport;

public class RentOfferViewShortWithModerationReportDTO extends RentOfferViewShortDTO {

    @ApiModelProperty(position = 130)
    private RentOfferModerationReport moderationReport;

    public RentOfferModerationReport getModerationReport() {
        return moderationReport;
    }

    public void setModerationReport(RentOfferModerationReport moderationReport) {
        this.moderationReport = moderationReport;
    }
}
