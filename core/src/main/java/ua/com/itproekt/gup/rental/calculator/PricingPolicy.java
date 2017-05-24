package ua.com.itproekt.gup.rental.calculator;

import java.math.BigDecimal;

public interface PricingPolicy {

    BigDecimal getPrice(int days);
}
