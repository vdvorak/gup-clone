package ua.com.gup.common.model.uapay.order.response.cancel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ua.com.gup.common.model.uapay.status.UaPayResponseStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseCancelOrderDTO {

    @JsonProperty("status")
    private UaPayResponseStatus status;

    public ResponseCancelOrderDTO() {
    }

    public UaPayResponseStatus getStatus() {
        return status;
    }

    public void setStatus(UaPayResponseStatus status) {
        this.status = status;
    }
}
