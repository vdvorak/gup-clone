package ua.com.gup.payment.model.uapay.order.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseCreateOrderDTO extends UaPayResponseOrder {


    public ResponseCreateOrderDataDTO getData() {
        return (ResponseCreateOrderDataDTO) data;
    }

}
