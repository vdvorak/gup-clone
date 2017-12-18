package ua.com.gup.common.model.uapay.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ua.com.gup.common.model.uapay.Card;
import ua.com.gup.common.model.uapay.Notify;
import ua.com.gup.common.model.uapay.base.UaPayRequestData;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class DirectPaymentData extends UaPayRequestData {
    private String externalId;
    private Card cardFrom;
    private Card cardTo;
    private Notify notify;
    private Long amount;
    private Integer currency;
    private String description;

    public DirectPaymentData() {
        this.currency = 980; //UAH code
    }


}
