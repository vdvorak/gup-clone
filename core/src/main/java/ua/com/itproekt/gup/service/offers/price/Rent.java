package ua.com.itproekt.gup.service.offers.price;

import java.util.Arrays;

/**
 * Используется тип списка 'ConcurrentLinkedDeque' потому-что в многопоточном режиме будут часто выполнятся операции с добавлением-удалением элементов списка
 */
public class Rent {

    private String[] days;

    public String[] getDays() {
        return days;
    }

    public void setDays(String[] days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "Rent{" +
                ", days=" + Arrays.toString(days) +
                '}';
    }

}
