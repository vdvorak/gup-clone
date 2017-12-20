package ua.com.gup.common.model.uapay;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Card {
    private String id;
    private String securityCode;
    private String pan;
    private String expiresAt;
}
