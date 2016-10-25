package ua.com.itproekt.gup.service.offers.price;

/**
 * Используется тип списка 'ConcurrentLinkedDeque' потому-что в многопоточном режиме будут часто выполнятся операции с добавлением-удалением элементов списка
 */
public class Rent {

    /**
     * Хранит массив дат (на которые установливается аренда услуги)
     */
    private Long[] rents;

    public Rent(){}

    public Rent(Long[] rents){
        this.rents = rents;
    }

    public Long[] get() {
        return rents;
    }

    public void set(Long[] rents) {
        this.rents = rents;
    }

}
