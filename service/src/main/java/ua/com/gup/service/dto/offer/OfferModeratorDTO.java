package ua.com.gup.service.dto.offer;


import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.domain.offer.OfferModerationReport;

import javax.validation.constraints.NotNull;

public class OfferModeratorDTO {

    @ApiModelProperty(position = 0, example = "58ff0d6c821847a4bc8c5bff")
    @NotNull
    private String id;

    @ApiModelProperty(position = 30)
    private Integer category;

    @ApiModelProperty(position = 130)
    private OfferModerationReport moderationReport;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public OfferModerationReport getModerationReport() {
        return moderationReport;
    }

    public void setModerationReport(OfferModerationReport moderationReport) {
        this.moderationReport = moderationReport;
    }
}
