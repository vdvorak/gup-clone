package ua.com.gup.rental.algorithm.calculator;

import java.math.BigDecimal;

public interface PricingPolicy {

    BigDecimal getPrice(int days);
}
