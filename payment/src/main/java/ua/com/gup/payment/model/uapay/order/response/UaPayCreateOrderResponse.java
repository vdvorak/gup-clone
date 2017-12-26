package ua.com.gup.payment.model.uapay.order.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ua.com.gup.payment.model.uapay.base.UaPayResponseData;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UaPayResponseCreateOrder extends UaPayResponseOrder<UaPayResponseCreateOrder.ResponseCreateOrderDataDTO> {


    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    class ResponseCreateOrderDataDTO extends UaPayResponseData {
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


    }
}
