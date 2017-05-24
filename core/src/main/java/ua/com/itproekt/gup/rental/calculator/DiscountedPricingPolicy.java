package ua.com.itproekt.gup.rental.calculator;

import java.math.BigDecimal;

class DiscountedPricingPolicy implements PricingPolicy {
    private int discountPeriod;
    private PricingPolicy pricingPolicy;

    DiscountedPricingPolicy(int discountPeriod, PricingPolicy pricingPolicy) {
        this.discountPeriod = discountPeriod;
        this.pricingPolicy = pricingPolicy;
    }

    public BigDecimal getPrice(int days) {
        if (days >= discountPeriod) {
            days -= discountPeriod - 1;
        }
        return pricingPolicy.getPrice(days);
    }
}
