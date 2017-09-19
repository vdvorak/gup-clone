package ua.com.gup.rental.algorithm.staff;

import ua.com.gup.rental.algorithm.type.Types;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class CustomerTest {

    @Test
    public void testChargeBonus() throws Exception {
        for (Types types : Types.values()) {

            Customer customer = new Customer("58efaf104c8e83648c2f1e1e");
            customer.chargeBonus(types);

            switch (types) {
                case NEW_RELEASES:
                    assertEquals(2, customer.getBonus());
                    break;
                case REGULAR:
                case OLD:
                    assertEquals(1, customer.getBonus());
                    break;
            }
        }
    }

    @Test
    public void testDischargeBonus() throws Exception {
        assertEquals(getDischargeBonusExpected(10, 5), getDischargeBonusActual(10, 5));
        assertEquals(getDischargeBonusExpected(10, 15), getDischargeBonusActual(10, 15));
    }

    private int getDischargeBonusExpected(int currentCustomerBonusPoints, int minusBonusPoints) {
        if (currentCustomerBonusPoints >= minusBonusPoints)
            currentCustomerBonusPoints -= minusBonusPoints;
        return currentCustomerBonusPoints;
    }

    private int getDischargeBonusActual(int currentCustomerBonusPoints, int minusBonusPoints) {
        Customer customer1 = new Customer("Test", currentCustomerBonusPoints);
        customer1.dischargeBonus(minusBonusPoints);
        return customer1.getBonus();
    }
}