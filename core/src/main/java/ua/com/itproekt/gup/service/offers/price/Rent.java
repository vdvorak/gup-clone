package ua.com.itproekt.gup.service.offers.price;

import ua.com.itproekt.gup.model.order.Order;
import ua.com.itproekt.gup.util.ConvertUtil;

public class Rent implements Comparable<Rent>, Cloneable {

    private Long day;
    private RentUser user;
    private Boolean confirm;
    private Boolean prepaid;
    private Long dayPrepaid;
    private Long orderDate;
    private Long updateDate;
    private RentStatus rentStatus;
    private OrderStatus orderStatus;
    private Integer salesRemained;
    private Order order;

    public Rent(){}

    public Rent(Long day, RentUser user, Boolean confirm, Boolean prepaid, Long dayPrepaid, Long orderDate, Long updateDate, RentStatus rentStatus, OrderStatus orderStatus, Integer salesRemained, Order order){
        this.day = day;
        this.user = user;
        this.confirm = confirm;
        this.prepaid = prepaid;
        this.dayPrepaid = dayPrepaid;
        this.orderDate = orderDate;
        this.updateDate = updateDate;
        this.rentStatus = rentStatus;
        this.orderStatus = orderStatus;
        this.salesRemained = salesRemained;
        this.order = order;
    }

    public Rent(Rent rent){
        this.day = rent.getDay();
        this.user = rent.getUser();
        this.confirm = rent.isConfirm();
        this.prepaid = rent.isPrepaid();
        this.dayPrepaid = rent.getDayPrepaid();
        this.orderDate = rent.getOrderDate();
        this.updateDate = rent.getUpdateDate();
        this.rentStatus = rent.getRentStatus();
        this.orderStatus = rent.getOrderStatus();
        this.salesRemained = rent.getSalesRemained();
        this.order = rent.getOrder();
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

    /**
     * Дата создания заказа (одна-единственная)
     */
    public Long getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Long orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Дата обновление заказа (чтобы следить за активностью выполнения...)
     */
    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * Отображает ход выполнения заказа (начиная с подачи заявки и заканчивая выдачей товара...)
     */
    public RentStatus getRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(RentStatus rentStatus) {
        this.rentStatus = rentStatus;
    }

    /**
     * Это предыдущее состояние заказа (чтобы избежать похожих-дублей заявок)
     */
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * Предложенный товаров может существовать как во множественном количестве так и в единственном количестве
     */
    public Integer getSalesRemained() {
        return salesRemained;
    }

    public void setSalesRemained(Integer salesRemained) {
        this.salesRemained = salesRemained;
    }

    /**
     * Хранит короткую информацию о заказе
     */
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public int compareTo(Rent that) {
        return day.compareTo(that.getDay());
    }

    @Override
    public Rent clone() throws CloneNotSupportedException {
        return this.clone();
    }

    @Override
    public String toString() {
//        return "\"day\": \"" + ConvertUtil.toDate(day) + "\"" +
//                ", \"user\": " + user +
//                ", \"confirm\": " + confirm +
//                ", \"prepaid\": " + prepaid +
//                ", \"dayPrepaid\": " +((dayPrepaid==null) ? dayPrepaid : "\"" + ConvertUtil.toDate(dayPrepaid) + "\"") +
//                ", \"orderDate\": " +((orderDate==null) ? orderDate : "\"" + ConvertUtil.toDate(orderDate) + "\"") +
//                ", \"updateDate\": " +((updateDate==null) ? updateDate : "\"" + ConvertUtil.toDate(updateDate) + "\"") +
//                ", \"rentStatus\": \"" + rentStatus + "\"" +
//                ", \"orderStatus\": \"" + orderStatus + "\"" +
//                ", \"salesRemained\": \"" + salesRemained + "\"" +
//                ", \"order\": " + order;
        return "\"day\": \"" + day + "\"" +
                ", \"user\": " + user +
                ", \"confirm\": " + confirm +
                ", \"prepaid\": " + prepaid +
                ", \"dayPrepaid\": " +((dayPrepaid==null) ? dayPrepaid : "\"" + dayPrepaid + "\"") +
                ", \"orderDate\": " +((orderDate==null) ? orderDate : "\"" + orderDate + "\"") +
                ", \"updateDate\": " +((updateDate==null) ? updateDate : "\"" + updateDate + "\"") +
                ", \"rentStatus\": \"" + rentStatus + "\"" +
                ", \"orderStatus\": \"" + orderStatus + "\"" +
                ", \"salesRemained\": \"" + salesRemained + "\"" +
                ", \"order\": " + order;
//        return "{" +
//                ConvertUtil.toDate(day) +
//                '}';
    }

    @Override
    public boolean equals(Object that) {
//        if (this == that) return true;
//        if (that == null || getClass() != that.getClass()) return false;

        Rent rent2 = (Rent) that;
        if (!day.equals(rent2.day)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return day.hashCode();
    }
}
