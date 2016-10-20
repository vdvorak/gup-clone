package ua.com.itproekt.gup.service.offers.price;

import java.util.Arrays;

/**
 * Используется тип списка 'ConcurrentLinkedDeque' потому-что в многопоточном режиме будут часто выполнятся операции с добавлением-удалением элементов списка
 */
public class Rent { //TODO: ConcurrentLinkedDeque<Rent>

    private Boolean rent; //FIXME: the field duplicate with Deque<Rent>..
    private String[] days;

    public Boolean getRent() {
        return rent;
    }

    public void setRent(Boolean rent) {
        this.rent = rent;
    }

    public String[] getDays() {
        return days;
    }

    public void setDays(String[] days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "MonthOfRent{" +
                "rent=" + rent +
                ", days=" + Arrays.toString(days) +
                '}';
    }

}
