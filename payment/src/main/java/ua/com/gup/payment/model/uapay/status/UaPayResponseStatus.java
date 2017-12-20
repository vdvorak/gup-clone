package ua.com.gup.common.model.uapay.status;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum UaPayResponseStatus {

    FAILURE(0),
    SUCCESS(1);

    private Integer status;

    UaPayResponseStatus(Integer status) {
        this.status = status;
    }

}
