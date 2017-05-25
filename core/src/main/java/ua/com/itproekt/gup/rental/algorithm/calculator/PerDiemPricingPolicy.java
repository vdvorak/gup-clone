package ua.com.itproekt.gup.rental.algorithm.calculator;

import java.math.BigDecimal;

public class PerDiemPricingPolicy implements PricingPolicy {
    private double perDiem;

    PerDiemPricingPolicy(double perDiem) {
        this.perDiem = perDiem;
    }

    public BigDecimal getPrice(int rentalDays) {
        return new BigDecimal(perDiem * rentalDays);
    }
}
