package ua.com.gup.common.model.complaint;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ComplaintFilter {
    @ApiModelProperty(position = 1, example = "id000")
    private String authorId;
    @ApiModelProperty(position = 2, example = "Dmitriy")
    private String username;
    @ApiModelProperty(position = 3, example = "ndjquwh3123")
    private String offerId;
    @ApiModelProperty(position = 4, example = "[NEW, OPENED, ACCEPTED, DECLINED]")
    private List<ComplaintOfferStatus> statuses;
    @ApiModelProperty(position = 5, example = "[TOPIC_MISMATCH, PROFANITY, CONTENT_PROHIBITED, CONTENT_MISMATCH, IRRELEVANT, AGENCY, FRAUD, OTHER]")
    List<ComplaintOfferType> types;

    public List<ComplaintOfferStatus> getStatuses() {
        if (statuses == null) {
            statuses = new ArrayList();
        }
        return statuses;
    }

    public List<ComplaintOfferType> getTypes() {
        if (types == null) {
            types = new ArrayList();
        }
        return types;
    }
}
