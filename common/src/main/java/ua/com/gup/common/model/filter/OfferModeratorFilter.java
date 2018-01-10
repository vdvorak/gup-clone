package ua.com.gup.common.model.filter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ua.com.gup.common.model.enumeration.CommonStatus;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ApiModel(description = "Offer moderator filter model")
@Data
public class OfferModeratorFilter implements Serializable {

    @ApiModelProperty(position = 10, example = "title")
    private String title;

    //@NotNull
    @ApiModelProperty(position = 20, example = "[NEW, ACCEPTED, PAID_FOR_RENT, REJECTED, NOT_PAID, IGNORING, ON_THE_JOB, SUCCESS, FAILURE, ACTIVE, ON_MODERATION, DEACTIVATED, ARCHIVED]")
    private CommonStatus status;

//    @ApiModelProperty(position = 30, example = "Dimka")
//    private String authorUsername;

    @ApiModelProperty(position = 40, example = "id00000")
    private String authorId;


}