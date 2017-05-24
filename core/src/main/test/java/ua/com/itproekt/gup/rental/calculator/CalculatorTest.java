package ua.com.itproekt.gup.rental.calculator;

import ua.com.itproekt.gup.rental.item.ItemType;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;

public class CalculatorTest {

    @Test
    public void testCalculatePrice() throws Exception {
        for (ItemType itemType : ItemType.values()) {
            switch (itemType) {
                case NEW_RELEASES:
                    assertEquals(new BigDecimal(400), Calculator.calculatePrice(itemType, 10));
                    break;
                case REGULAR:
                    assertEquals(new BigDecimal(30), Calculator.calculatePrice(itemType, 3));
                    assertEquals(new BigDecimal(240), Calculator.calculatePrice(itemType, 10));
                    break;
                case OLD:
                    assertEquals(new BigDecimal(30), Calculator.calculatePrice(itemType, 5));
                    assertEquals(new BigDecimal(180), Calculator.calculatePrice(itemType, 10));
                    break;
            }
        }
    }

    @Test
    public void testCalculateChargedBonus() throws Exception {
        for (ItemType itemType : ItemType.values()) {
            switch (itemType) {
                case NEW_RELEASES:
                    assertEquals(12, (int) Calculator.calculateChargedBonus(10, itemType));
                    break;
                case REGULAR:
                    assertEquals(11, (int) Calculator.calculateChargedBonus(10, itemType));
                    break;
                case OLD:
                    assertEquals(11, (int) Calculator.calculateChargedBonus(10, itemType));
                    break;
            }
        }
    }
}