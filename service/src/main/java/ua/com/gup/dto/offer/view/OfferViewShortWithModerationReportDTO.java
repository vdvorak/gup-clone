package ua.com.gup.dto.offer.view;

import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.model.offer.OfferModerationReport;

public class OfferViewShortWithModerationReportDTO extends OfferViewShortDTO {

    @ApiModelProperty(position = 130)
    private OfferModerationReport moderationReport;

    public OfferModerationReport getModerationReport() {
        return moderationReport;
    }

    public void setModerationReport(OfferModerationReport moderationReport) {
        this.moderationReport = moderationReport;
    }
}
