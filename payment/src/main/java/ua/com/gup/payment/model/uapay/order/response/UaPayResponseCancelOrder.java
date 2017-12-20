package ua.com.gup.payment.model.uapay.order.response.cancel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ua.com.gup.payment.model.uapay.UaPayResponse;
import ua.com.gup.payment.model.uapay.order.response.UaPayResponseOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseCancelOrderDTO extends UaPayResponseOrder {
}
