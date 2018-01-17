package ua.com.gup.common.model.mongo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Phone {
    private String phoneNumber;
    private Boolean hidden;

    public Phone(){
    }

    public Phone(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.hidden = false;
    }
}
