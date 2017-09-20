package ua.com.gup.rental.algorithm.calculator;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class DiscountedPricingPolicyTest {


    @Test
    public void testGetPrice() throws Exception {
        assertEquals(getExpected(Calculator.PREMIUM_FREE, 10, 1), getActual(Calculator.PREMIUM_FREE, 10, 1));
        assertEquals(getExpected(Calculator.PREMIUM_FREE, 1, 1), getActual(Calculator.PREMIUM_FREE, 1, 1));
        assertEquals(getExpected(Calculator.PREMIUM_FREE, 0, 1), getActual(Calculator.PREMIUM_FREE, 0, 1));

        assertEquals(getExpected(Calculator.REGULAR_FREE, 10, 3), getActual(Calculator.REGULAR_FREE, 10, 3));
        assertEquals(getExpected(Calculator.REGULAR_FREE, 3, 3), getActual(Calculator.REGULAR_FREE, 3, 3));
        assertEquals(getExpected(Calculator.REGULAR_FREE, 0, 3), getActual(Calculator.REGULAR_FREE, 0, 3));

        assertEquals(getExpected(Calculator.REGULAR_FREE, 10, 5), getActual(Calculator.REGULAR_FREE, 10, 5));
        assertEquals(getExpected(Calculator.REGULAR_FREE, 5, 5), getActual(Calculator.REGULAR_FREE, 5, 5));
        assertEquals(getExpected(Calculator.REGULAR_FREE, 0, 5), getActual(Calculator.REGULAR_FREE, 0, 5));
    }

    private BigDecimal getExpected(double fee, int days, int discountPeriod) {
        if (days >= discountPeriod) {
            days -= discountPeriod - 1;
        }
        return new BigDecimal(fee * days);
    }

    private BigDecimal getActual(double fee, int days, int discountPeriod) {
        DiscountedPricingPolicy discountedPricingPolicy1 = new DiscountedPricingPolicy(discountPeriod, new PerDiemPricingPolicy(fee));
        return discountedPricingPolicy1.getPrice(days);
    }
}