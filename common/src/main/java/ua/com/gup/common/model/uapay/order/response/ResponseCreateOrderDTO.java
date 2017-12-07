package ua.com.gup.common.model.uapay.order.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ua.com.gup.common.model.uapay.status.UaPayResponseStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseCreateOrderDTO {

    @JsonProperty("status")
    private UaPayResponseStatus status;

    @JsonProperty("data")
    private ResponseCreateOrderDataDTO data;

    public ResponseCreateOrderDTO() {
    }

    public UaPayResponseStatus getStatus() {
        return status;
    }

    public void setStatus(UaPayResponseStatus status) {
        this.status = status;
    }

    public ResponseCreateOrderDataDTO getData() {
        return data;
    }

    public void setData(ResponseCreateOrderDataDTO data) {
        this.data = data;
    }
}
