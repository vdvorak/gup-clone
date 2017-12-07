package ua.com.gup.common.model.uapay.order.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ua.com.gup.common.model.uapay.status.UaPayResponseStatus;
import ua.com.gup.common.model.uapay.PaymentStatusDTO;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseViewOrderDTO {

    @JsonProperty("status")
    private UaPayResponseStatus status;

    @JsonProperty("data")
    private ResponseViewOrderDataDTO data;

    public ResponseViewOrderDTO() {
    }

    public UaPayResponseStatus getStatus() {
        return status;
    }

    public void setStatus(UaPayResponseStatus status) {
        this.status = status;
    }

    public ResponseViewOrderDataDTO getData() {
        return data;
    }

    public void setData(ResponseViewOrderDataDTO data) {
        this.data = data;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private class ResponseViewOrderDataDTO {

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


        public ResponseViewOrderDataDTO() {
        }

        public String getUaPayOrderId() {
            return uaPayOrderId;
        }

        public void setUaPayOrderId(String uaPayOrderId) {
            this.uaPayOrderId = uaPayOrderId;
        }

        public String getClientName() {
            return clientName;
        }

        public void setClientName(String clientName) {
            this.clientName = clientName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Long getAmount() {
            return amount;
        }

        public void setAmount(Long amount) {
            this.amount = amount;
        }

        public Long getNumber() {
            return number;
        }

        public void setNumber(Long number) {
            this.number = number;
        }

        public PaymentStatusDTO getStatus() {
            return status;
        }

        public void setStatus(PaymentStatusDTO status) {
            this.status = status;
        }

        public String getCheckUrl() {
            return checkUrl;
        }

        public void setCheckUrl(String checkUrl) {
            this.checkUrl = checkUrl;
        }
    }
}

