package ua.com.itproekt.gup.rental.algorithm.calculator;

import ua.com.itproekt.gup.rental.algorithm.type.Types;

import java.math.BigDecimal;

public class Calculator {

    public static final double PREMIUM_FREE = 40; //TODO: PREMIUM
    public static final double REGULAR_FREE = 30; //TODO: REGULAR
    private static final PricingPolicy NEW_RELEASE_PRICING = new PerDiemPricingPolicy(PREMIUM_FREE);
    private static final PricingPolicy DEFAULT_PRICING_POLICY = new PerDiemPricingPolicy(REGULAR_FREE);
    private static final PricingPolicy RELEASE_PRICING = new DiscountedPricingPolicy(1, NEW_RELEASE_PRICING);
    private static final PricingPolicy REGULAR_PRICING = new DiscountedPricingPolicy(3, DEFAULT_PRICING_POLICY);
    private static final PricingPolicy OLD_PRICING = new DiscountedPricingPolicy(5, DEFAULT_PRICING_POLICY);

    public static final int BONUS_POINTS_FOR_NEW_RELEASE = 2; //TODO: BONUS-RELEASE
    public static final int BONUS_POINTS_FOR_OTHER = 1; //TODO: BONUS-OTHER
    private static final BonusPolicy RELEASE_BONUS_CHARGING = new TypedBonusPolicy(BONUS_POINTS_FOR_NEW_RELEASE);
    private static final BonusPolicy OTHER_BONUS_CHARGING = new TypedBonusPolicy(BONUS_POINTS_FOR_OTHER);


    public static BigDecimal calculatePrice(Types type, int days) {
        PricingPolicy pricingPolicy = getPricingPolicy(type);
        return pricingPolicy != null ? pricingPolicy.getPrice(days) : BigDecimal.ZERO;
    }

    public static Integer calculateChargedBonus(int customerBonus, Types type) {
        BonusPolicy bonusPolicy = getBonusPolicy(type);
        return bonusPolicy != null ? bonusPolicy.getBonus(customerBonus) : 0;
    }

    private static PricingPolicy getPricingPolicy(Types type) {
        switch (type) {
            case NEW_RELEASES:
                return RELEASE_PRICING;
            case REGULAR:
                return REGULAR_PRICING;
            case OLD:
                return OLD_PRICING;
        }
        return null;
    }

    private static BonusPolicy getBonusPolicy(Types type) {
        switch (type) {
            case NEW_RELEASES:
                return RELEASE_BONUS_CHARGING;
            default:
                return OTHER_BONUS_CHARGING;
        }
    }
}
