package ua.com.gup.common.model.uapay.order.request.create;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import ua.com.gup.common.model.uapay.base.UaPayRequestData;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestCreateUaPayOrderDataDTO extends UaPayRequestData {

    /**
     * Тип списания : холд/списание ("HOLD"  or "PAY")
     */
    @JsonProperty("type")
    private OrderTypeDTO type;
    /**
     * Уникальный номер платежа в нашей системе
     */
    @JsonProperty("externalId")
    private String externalId;

    /**
     * Назначение платежа
     */
    @JsonProperty("description")
    private String description;

    /**
     * Сумма платежа в копейках
     */
    @JsonProperty("amount")
    private Long amount;

    /**
     * Код валюты (в данный момент только 980 — код гривни)
     */
    @JsonProperty("currency")
    private Integer currency;


    /**
     * URL для информирования об изменении статуса
     */
    @JsonProperty("callbackUrl")
    private String callbackUrl;


    /**
     * URL для возврата пользователя на страницу сайта
     */
    @JsonProperty("userRedirectUrl")
    private String userRedirectUrl;

    /**
     * Поле, в которое записывается дополнительная сервисная информация от продавца.
     * Актуальна в случае получения продавцом реестров с этой информацией.
     */
    @JsonProperty("extraInfo")
    private String extraInfo;


    public RequestCreateUaPayOrderDataDTO(OrderTypeDTO type,
                                          String externalId, String description,
                                          Long amount, String callbackUrl, String userRedirectUrl) {
        this.type = type;
        this.externalId = externalId;
        this.description = description;
        this.amount = amount;
        this.callbackUrl = callbackUrl;
        this.userRedirectUrl = userRedirectUrl;
        currency = 980;
    }

    public OrderTypeDTO getType() {
        return type;
    }

    public void setType(OrderTypeDTO type) {
        this.type = type;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
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

    public Integer getCurrency() {
        return currency;
    }

    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getUserRedirectUrl() {
        return userRedirectUrl;
    }

    public void setUserRedirectUrl(String userRedirectUrl) {
        this.userRedirectUrl = userRedirectUrl;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }
}
