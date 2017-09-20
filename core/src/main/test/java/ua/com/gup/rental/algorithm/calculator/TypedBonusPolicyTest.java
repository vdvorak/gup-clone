package ua.com.gup.rental.algorithm.calculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TypedBonusPolicyTest {

    @Test
    public void testGetBonus() throws Exception {
        assertEquals(getExpected(Calculator.BONUS_POINTS_FOR_NEW_RELEASE, 10), getActual(Calculator.BONUS_POINTS_FOR_NEW_RELEASE, 10));
        assertEquals(getExpected(Calculator.BONUS_POINTS_FOR_OTHER, 10), getActual(Calculator.BONUS_POINTS_FOR_OTHER, 10));
    }

    private int getExpected(int typedBonusPoints, int currentBonusPoints) {
        return typedBonusPoints + currentBonusPoints;
    }

    private int getActual(int typedBonusPoints, int currentBonusPoints) {
        TypedBonusPolicy typedBonusPolicy = new TypedBonusPolicy(typedBonusPoints);
        return typedBonusPolicy.getBonus(currentBonusPoints);
    }
}