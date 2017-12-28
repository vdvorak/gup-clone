package ua.com.gup.common.model.uapay.order.request.refund;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import ua.com.gup.common.model.uapay.base.UaPayRequestData;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestRefundUaPayOrderDataDTO extends UaPayRequestData{

    @JsonProperty("amount")
    private Long amount;

    public RequestRefundUaPayOrderDataDTO() {
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
