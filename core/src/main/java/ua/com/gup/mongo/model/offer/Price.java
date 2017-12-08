package ua.com.gup.mongo.model.offer;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;
import ua.com.gup.common.model.enumeration.CommonCurrency;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Price {

    @LastModifiedDate
    @Field("last_modified_date")
    private LocalDateTime lastModifiedDate = LocalDateTime.now();

    private BigDecimal amount;

    private CommonCurrency currency;

    @Indexed
    private double baseAmount;

    private CommonCurrency baseCurrency;

    private Boolean priceWithVAT;

    private Boolean bargainingPossible;

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public CommonCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(CommonCurrency currency) {
        this.currency = currency;
    }

    public double getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(double baseAmount) {
        this.baseAmount = baseAmount;
    }

    public CommonCurrency getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(CommonCurrency baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public Boolean getPriceWithVAT() {
        return priceWithVAT;
    }

    public void setPriceWithVAT(Boolean priceWithVAT) {
        this.priceWithVAT = priceWithVAT;
    }

    public Boolean getBargainingPossible() {
        return bargainingPossible;
    }

    public void setBargainingPossible(Boolean bargainingPossible) {
        this.bargainingPossible = bargainingPossible;
    }
}
