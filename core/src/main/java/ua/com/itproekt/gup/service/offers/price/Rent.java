package ua.com.itproekt.gup.service.offers.price;

public abstract class Rent {

    public Rent(){}
    public Rent(Long day, Boolean isPrepaid, Long dayPrepaid, RentUser user){
        setDay(day);
        setIsPrepaid(isPrepaid);
        setDayPrepaid(dayPrepaid);
        setUser(user);
    }

    abstract Long getDay();

    abstract void setDay(Long day);

    abstract Boolean getIsPrepaid();

    abstract void setIsPrepaid(Boolean isPrepaid);

    abstract Long getDayPrepaid();

    abstract void setDayPrepaid(Long dayPrepaid);

    abstract RentUser getUser();

    abstract void setUser(RentUser user);
}
