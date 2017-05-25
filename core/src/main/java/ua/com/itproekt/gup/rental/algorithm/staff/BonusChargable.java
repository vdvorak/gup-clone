package ua.com.itproekt.gup.rental.algorithm.staff;

import ua.com.itproekt.gup.rental.algorithm.type.Types;

public interface BonusChargable {

    void chargeBonus(Types type);

    void dischargeBonus(int bonusPoints);
}
