package ua.com.gup.payment.model.uapay.order.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ua.com.gup.payment.model.uapay.PaymentStatusDTO;
import ua.com.gup.payment.model.uapay.UaPayResponse;
import ua.com.gup.payment.model.uapay.base.UaPayResponseData;
import ua.com.gup.payment.model.uapay.status.UaPayResponseStatus;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseViewOrderDTO extends UaPayResponseOrder{

    @JsonProperty("status")
    private UaPayResponseStatus status;

    @JsonProperty("data")
    private ResponseViewOrderDataDTO data;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private class ResponseViewOrderDataDTO extends UaPayResponseData {

        @JsonProperty("id")
        private String uaPayOrderId;
        @JsonProperty("name")
        private String clientName;
        @JsonProperty("description")
        private String description;
        @JsonProperty("amount")
        private Long amount;
        @JsonProperty("number")
        private Long number;
        @JsonProperty("status")
        private PaymentStatusDTO status;
        @JsonProperty("chequeLink")
        private String checkUrl;

    }
}

