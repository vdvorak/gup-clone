package ua.com.gup.service.dto;


import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.domain.OfferCategory;
import ua.com.gup.domain.OfferModerationReport;

import javax.validation.constraints.NotNull;
import java.util.LinkedList;

public class OfferModeratorDTO {

    @ApiModelProperty(position = 0, example = "58ff0d6c821847a4bc8c5bff")
    @NotNull
    private String id;

    @ApiModelProperty(position = 30)
    private LinkedList<OfferCategory> categories;

    @ApiModelProperty(position = 130)
    private OfferModerationReport moderationReport;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LinkedList<OfferCategory> getCategories() {
        return categories;
    }

    public void setCategories(LinkedList<OfferCategory> categories) {
        this.categories = categories;
    }

    public OfferModerationReport getModerationReport() {
        return moderationReport;
    }

    public void setModerationReport(OfferModerationReport moderationReport) {
        this.moderationReport = moderationReport;
    }
}
