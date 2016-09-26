package ua.com.itproekt.gup.service.offers.calendar;

import java.util.concurrent.ConcurrentLinkedQueue;

public class PriceScheme extends ConcurrentLinkedQueue<Price> {

    private Integer weekdayPrice = 100; // TODO read from file.properies !!!
    private Integer weekendPrice = 120; // TODO read from file.properies !!!
    private Integer[][] weekdays = {{1,5},{8,12},{15,19},{22,26},{29,31}}; // ...
    private Integer[][] weekends = {{6,7},{13,14},{20,21},{27,28}}; // ...

    public PriceScheme(){
        init();
    }

    public PriceScheme(Integer[][] weekdays, Integer[][] weekends){
        this.weekdays = weekdays;
        this.weekends = weekends;
        init();
    }

    public PriceScheme(Integer[][] weekdays, Integer[][] weekends, Integer weekdayPrice, Integer weekendPrice){
        this.weekdays = weekdays;
        this.weekends = weekends;
        this.weekdayPrice = weekdayPrice;
        this.weekendPrice = weekendPrice;
        init();
    }

    public void init(){
        addDays(weekdayPrice, weekdays);
        addDays(weekendPrice, weekends);
    }


    public void addDays(Integer price, Integer day) {
        Price newPrice = new Price(price);
        for (Price curPrice : this) {
            if (curPrice.remove(day)){
                newPrice.add(day);
            }
        }
        add(newPrice);
    }

    public void addDays(Integer price, Integer[] days) {
        Price newPrice = new Price(price);
        for (Price curPrice : this) {
            for (Integer day=days[0]; day<=days[1]; ++day) {
                if (curPrice.remove(day)){
                    newPrice.add(day);
                }
            }
        }
        add(newPrice);
    }

    public void addDays(Integer price, Integer[][] weekdays) {
        Price newPrice = new Price(price);
        for (Price thisPrice : this) {
            for (Integer[] days : weekdays) {
                for (Integer day=days[0]; day<=days[1]; ++day) {
                    if (thisPrice.remove(day)){
                        newPrice.add(day);
                    }
                }
            }
        }
        add(newPrice);
        for (Integer[] days : weekdays) {
            Price curPrice = new Price(price);
            for (Integer day=days[0]; day<=days[1]; ++day) {
                if (!newPrice.contains(day)) {
                    curPrice.add(day);
                }
            }
            add(curPrice);
        }
    }

    public boolean isDay(Integer day) {
        for (Price prices : this)
            if (prices.contains(day)) return true;
        return false;
    }

    public Integer getPrice(Integer[] days){
        Integer price = 0;
        for (Price prices : this) {
            for (Integer day=days[0]; day<=days[1]; ++day) if (prices.contains(day)) price += prices.get();
        }
        return price;
    }

    public Integer getPrice(Integer day){
        Integer price = 0;
        for (Price prices : this) {
            if (prices.contains(day)) price += prices.get();
        }
        return price;
    }

    @Override
    public String toString() {
        StringBuilder data = new StringBuilder();

        for (Price prices : this) {
            for (Integer price : prices) data.append(prices.get() + "(" + price + ") ");
            if (!prices.isEmpty()) data.append("\n");
        }

        return data.toString();
    }

}
