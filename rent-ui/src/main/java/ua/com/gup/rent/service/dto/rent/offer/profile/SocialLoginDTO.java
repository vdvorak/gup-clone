package ua.com.gup.rent.service.dto.rent.offer.profile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SocialLoginDTO {
    @JsonProperty("socWendor")
    private String socWendor;
    @JsonProperty("socWendor")
    private String tokenKey;
    @JsonProperty("uid")
    private String uid;
}
