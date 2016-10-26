package ua.com.itproekt.gup.service.offers.price;

public class Rent2 implements Comparable<Rent2>, Cloneable {

    private Long day;
    private RentUser user;
    private Boolean confirm;
    private Boolean prepaid;
    private Long dayPrepaid;

    public Rent2(){}

    public Rent2(Long day, RentUser user, Boolean confirm, Boolean prepaid, Long dayPrepaid){
        this.day = day;
        this.user = user;
        this.confirm = confirm;
        this.prepaid = prepaid;
        this.dayPrepaid = dayPrepaid;
    }

    public Rent2(Rent2 rent){
        this.day = rent.getDay();
        this.user = rent.getUser();
        this.confirm = rent.isConfirm();
        this.prepaid = rent.isPrepaid();
        this.dayPrepaid = rent.getDayPrepaid();
    }

    /**
     * Хранит дату (на которую установливается аренда услуги)
     */
    public Long getDay() {
        return day;
    }

    public void setDay(Long day) {
        this.day = day;
    }

    /**
     * Хранит короткую информацию о клиенте кто арендует
     */
    public RentUser getUser() {
        return user;
    }

    public void setUser(RentUser user) {
        this.user = user;
    }

    /**
     * Подтверждает состояние-готовности выполнение любой операции
     */
    public Boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(Boolean confirm) {
        this.confirm = confirm;
    }

    /**
     * Если владелец желает воспользоваться услугой-GUP предоплата от заказчика для бронирования
     */
    public Boolean isPrepaid() {
        return prepaid;
    }

    public void setPrepaid(Boolean prepaid) {
        this.prepaid = prepaid;
    }

    /**
     * Хранит дату (когда истекает срок предоплаты)
     */
    public Long getDayPrepaid() {
        return dayPrepaid;
    }

    public void setDayPrepaid(Long dayPrepaid) {
        this.dayPrepaid = dayPrepaid;
    }

    @Override
    public int compareTo(Rent2 that) {
        return day.compareTo(that.getDay());
    }

    @Override
    public Rent2 clone() throws CloneNotSupportedException {
        return this.clone();
    }

    @Override
    public String toString() {
        return "{" +
                "day=" + day +
                ", user=" + user +
                ", confirm=" + confirm +
                ", prepaid=" + prepaid +
                ", dayPrepaid=" + dayPrepaid +
                '}';
    }

}
