package ua.com.gup.common.model.mongo.offer;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import ua.com.gup.common.model.enumeration.CommonRefusalReason;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

@Data
public class OfferModerationReport implements Serializable {

    private String moderatorId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private ZonedDateTime lastModifiedDate;
    private List<CommonRefusalReason> refusalReasons;
    private String description;

    public boolean isRefused() {
        return refusalReasons != null && refusalReasons.size() > 0;
    }
}
