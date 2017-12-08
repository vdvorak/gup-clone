package ua.com.gup.dto.offer;


import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.common.model.enumeration.CommonRefusalReason;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class OfferModerationReportDTO implements Serializable {

    @ApiModelProperty(position = 0, example = "58ff0d6c821847a4bc8c5bff")
    @NotNull
    private String id;

    @ApiModelProperty(position = 30)
    private Integer category;

    @ApiModelProperty(position = 130)
    private List<CommonRefusalReason> refusalReasons;

    private String description;

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

    public List<CommonRefusalReason> getRefusalReasons() {
        return refusalReasons;
    }

    public void setRefusalReasons(List<CommonRefusalReason> refusalReasons) {
        this.refusalReasons = refusalReasons;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
