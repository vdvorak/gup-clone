package ua.com.itproekt.gup.rental.algorithm.staff;

import ua.com.itproekt.gup.rental.algorithm.calculator.Calculator;
import ua.com.itproekt.gup.rental.algorithm.type.Types;
import ua.com.itproekt.gup.rental.model.Person;
import ua.com.itproekt.gup.rental.resource.Resource;
import ua.com.itproekt.gup.rental.resource.ResourceBundleType;

public class Customer extends Person implements BonusChargable {
    private int bonus;

    public Customer(String name) {
        super(name);
        this.bonus = 0;
    }

    public Customer(String name, int bonus) {
        super(name);
        this.bonus = bonus;
    }

    public int getBonus() {
        return bonus;
    }

    @Override
    public void chargeBonus(Types type) {
        this.bonus = Calculator.calculateChargedBonus(bonus, type);
    }

    @Override
    public void dischargeBonus(int bonusPoints) {
        if (this.bonus >= bonusPoints)
            this.bonus -= bonusPoints;
        else
            System.out.println(Resource.getInstance(ResourceBundleType.ERRORS.getBundle()).getString("cannot.discharge.bonus.points"));
    }

    @Override
    public String toString() {
        return Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("customer", getName(), getBonus());
    }
}
