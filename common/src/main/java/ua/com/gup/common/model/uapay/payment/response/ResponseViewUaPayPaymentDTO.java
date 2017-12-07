package ua.com.gup.common.model.uapay.payment.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ua.com.gup.common.model.uapay.status.UaPayResponseStatus;
import ua.com.gup.common.model.uapay.PaymentStatusDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseViewUaPayPaymentDTO {
    @JsonProperty("status")
    private UaPayResponseStatus status;
    @JsonProperty("data")
    private ResponseViewPaymentDataDTO data;

    public ResponseViewUaPayPaymentDTO() {
    }

    public UaPayResponseStatus getStatus() {
        return status;
    }

    public void setStatus(UaPayResponseStatus status) {
        this.status = status;
    }

    public ResponseViewPaymentDataDTO getData() {
        return data;
    }

    public void setData(ResponseViewPaymentDataDTO data) {
        this.data = data;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private class ResponseViewPaymentDataDTO {

        @JsonProperty("id")
        private String id;

//        @JsonProperty("name")
//        private String clientName;
//        @JsonProperty("description")
//        private String description;
        @JsonProperty("amount")
        private Long amount;
//        @JsonProperty("number")
//        private Long number;
        @JsonProperty("status")
        private PaymentStatusDTO status;
//        @JsonProperty("chequeLink")
//        private String checkUrl;


        public ResponseViewPaymentDataDTO() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

//        public String getClientName() {
//            return clientName;
//        }
//
//        public void setClientName(String clientName) {
//            this.clientName = clientName;
//        }
//
//        public String getDescription() {
//            return description;
//        }
//
//        public void setDescription(String description) {
//            this.description = description;
//        }

        public Long getAmount() {
            return amount;
        }

        public void setAmount(Long amount) {
            this.amount = amount;
        }

//        public Long getNumber() {
//            return number;
//        }
//
//        public void setNumber(Long number) {
//            this.number = number;
//        }

        public PaymentStatusDTO getStatus() {
            return status;
        }

        public void setStatus(PaymentStatusDTO status) {
            this.status = status;
        }

//        public String getCheckUrl() {
//            return checkUrl;
//        }
//
//        public void setCheckUrl(String checkUrl) {
//            this.checkUrl = checkUrl;
//        }
    }
}
