package ua.com.itproekt.gup.rental.algorithm.calculator;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class PerDiemPricingPolicyTest {

    @Test
    public void testGetPrice() throws Exception {
        assertEquals(getExpected(Calculator.PREMIUM_FREE, 10), getActual(Calculator.PREMIUM_FREE, 10));
        assertEquals(getExpected(Calculator.REGULAR_FREE, 20), getActual(Calculator.REGULAR_FREE, 20));
    }

    private BigDecimal getExpected(double fee, int rentalDays) {
        return new BigDecimal(fee * rentalDays);
    }

    private BigDecimal getActual(double fee, int rentalDays) {
        PerDiemPricingPolicy perDiemPricingPolicy1 = new PerDiemPricingPolicy(fee);
        return perDiemPricingPolicy1.getPrice(rentalDays);
    }
}