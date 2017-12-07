package ua.com.gup.common.model.uapay.order.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseCreateOrderDataDTO {
    /**
     * Идентификатор заказа
     */
    @JsonProperty("id")
    private String uaPayOrderId;

    /**
     * Ссылка для перехода на страницу оплаты
     */
    @JsonProperty("paymentPageUrl")
    private String paymentPageUrl;

    public ResponseCreateOrderDataDTO() {
    }

    public String getUaPayOrderId() {
        return uaPayOrderId;
    }

    public void setUaPayOrderId(String uaPayOrderId) {
        this.uaPayOrderId = uaPayOrderId;
    }

    public String getPaymentPageUrl() {
        return paymentPageUrl;
    }

    public void setPaymentPageUrl(String paymentPageUrl) {
        this.paymentPageUrl = paymentPageUrl;
    }
}
