package ua.com.gup.common.model.mongo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class BanInfo {

    @JsonProperty("banDate")
    @NotNull
    private Date banDate;
    @JsonProperty("public")
    @NotNull
    private String publicExplanation;
    @JsonProperty("private")
    @NotNull
    private String privateExplanation;


}
