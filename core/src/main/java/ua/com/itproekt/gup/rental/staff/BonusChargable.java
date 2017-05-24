package ua.com.itproekt.gup.rental.staff;

import ua.com.itproekt.gup.rental.item.ItemType;

public interface BonusChargable {

    void chargeBonus(ItemType type);

    void dischargeBonus(int bonusPoints);
}
