package ua.com.gup.dto.operation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserBanOperationDTO extends OperationDTO {
    private String privateExplanation;
    private String publicExplanation;
}
