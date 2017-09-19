package ua.com.gup.rental.algorithm.calculator;

import ua.com.gup.rental.algorithm.type.Types;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;

public class CalculatorTest {

    @Test
    public void testCalculatePrice() throws Exception {
        for (Types types : Types.values()) {
            switch (types) {
                case NEW_RELEASES:
                    assertEquals(new BigDecimal(400), Calculator.calculatePrice(types, 10));
                    break;
                case REGULAR:
                    assertEquals(new BigDecimal(30), Calculator.calculatePrice(types, 3));
                    assertEquals(new BigDecimal(240), Calculator.calculatePrice(types, 10));
                    break;
                case OLD:
                    assertEquals(new BigDecimal(30), Calculator.calculatePrice(types, 5));
                    assertEquals(new BigDecimal(180), Calculator.calculatePrice(types, 10));
                    break;
            }
        }
    }

    @Test
    public void testCalculateChargedBonus() throws Exception {
        for (Types types : Types.values()) {
            switch (types) {
                case NEW_RELEASES:
                    assertEquals(12, (int) Calculator.calculateChargedBonus(10, types));
                    break;
                case REGULAR:
                    assertEquals(11, (int) Calculator.calculateChargedBonus(10, types));
                    break;
                case OLD:
                    assertEquals(11, (int) Calculator.calculateChargedBonus(10, types));
                    break;
            }
        }
    }
}