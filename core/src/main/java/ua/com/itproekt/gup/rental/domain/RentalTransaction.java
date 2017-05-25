package ua.com.itproekt.gup.rental.domain;

import ua.com.itproekt.gup.rental.algorithm.calculator.Calculator;
import ua.com.itproekt.gup.rental.algorithm.type.Type;
import ua.com.itproekt.gup.rental.resource.Resource;
import ua.com.itproekt.gup.rental.resource.ResourceBundleType;
import ua.com.itproekt.gup.rental.algorithm.staff.Customer;
import ua.com.itproekt.gup.rental.util.Converter;

import java.math.BigDecimal;

public class RentalTransaction {

    public static final int BONUS_CHARGE_PER_DIEM = 25;

    private Customer customer;
    private Type type;
    private int days;
    private BigDecimal price;
    private int usedBonusPoints;

    public RentalTransaction(Customer customer, Type type, int days, boolean useBonus) {
        this.customer = customer;
        if (useBonus) this.customer.dischargeBonus(days * BONUS_CHARGE_PER_DIEM);
        this.customer.chargeBonus(type.getType());
        this.type = type;
        this.days = days;
        this.price = useBonus ? BigDecimal.ZERO : Calculator.calculatePrice(type.getType(), days);
        this.usedBonusPoints = useBonus ? days * BONUS_CHARGE_PER_DIEM : 0;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Type getItem() {
        return type;
    }

    public int getDays() {
        return days;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getUsedBonusPoints() {
        return usedBonusPoints;
    }

    @Override
    public String toString() {
        return Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("rental.transaction", customer.toString(), type.toString(), Converter.toDays(days)) +
                (usedBonusPoints > 0 ?
                        Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("paid.with.bonus.points", usedBonusPoints) :
                        Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("total.price", Converter.toCurrency(price)));
    }
}
