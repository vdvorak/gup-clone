package ua.com.gup.rent.model.rent.statistic;

import ua.com.gup.rent.util.RentOfferDateUtil;

import java.time.LocalDate;

public abstract class RentOfferBaseStatistic {

    protected RentOfferViewStatistic viewStatistic;

    public RentOfferBaseStatistic() {
    }

    public RentOfferViewStatistic getViewStatistic() {
        if (viewStatistic == null) {
            viewStatistic = new RentOfferViewStatistic();
        }
        return viewStatistic;
    }

    public void setViewStatistic(RentOfferViewStatistic viewStatistic) {
        this.viewStatistic = viewStatistic;
    }

    public void incrementTodayViewStatistic(LocalDate offerDtCreate) {
        LocalDate today = LocalDate.now();
        int numOfDaysBetween = (int) RentOfferDateUtil.calculateDaysDiffBetweenDates(offerDtCreate, today);
        Integer[] offerViewsArray = getViewStatistic().getTotalOfferViews();
        if (offerViewsArray.length <= numOfDaysBetween || offerViewsArray.length == 0) {
            Integer[] newViews = new Integer[numOfDaysBetween + 1];
            System.arraycopy(offerViewsArray, 0, newViews, 0, offerViewsArray.length);
            newViews[numOfDaysBetween] = 1;
            getViewStatistic().setTotalOfferViews(newViews);
        } else {
            Integer currentValue = offerViewsArray[numOfDaysBetween];
            offerViewsArray[numOfDaysBetween] = currentValue + 1;
        }
    }


    public void incrementTodayViewPhoneStatistic(LocalDate offerDtCreate) {
        LocalDate today = LocalDate.now();
        int numOfDaysBetween = (int) RentOfferDateUtil.calculateDaysDiffBetweenDates(offerDtCreate, today);
        Integer[] phoneViewsArray = getViewStatistic().getTotalOfferPhonesViews();
        if (phoneViewsArray.length <= numOfDaysBetween || phoneViewsArray.length == 0) {
            Integer[] newPhoneViewsArray = new Integer[numOfDaysBetween + 1];
            System.arraycopy(phoneViewsArray, 0, newPhoneViewsArray, 0, phoneViewsArray.length);
            newPhoneViewsArray[numOfDaysBetween] = 1;
            getViewStatistic().setTotalOfferPhonesViews(newPhoneViewsArray);
        } else {
            Integer currentValue = phoneViewsArray[numOfDaysBetween];
            phoneViewsArray[numOfDaysBetween] = currentValue + 1;
        }
    }

    public Integer getOfferViewsCountByDate(LocalDate offerDtCreate, LocalDate date) {
        int numOfDaysBetween = (int) RentOfferDateUtil.calculateDaysDiffBetweenDates(offerDtCreate, date);
        Integer[] offerViewsArray = getViewStatistic().getTotalOfferViews();
        if (numOfDaysBetween < 0 || numOfDaysBetween >= offerViewsArray.length) {
            return 0;
        }
        Integer offerViewsAtDay = offerViewsArray[numOfDaysBetween];
        return offerViewsAtDay == null ? 0 : offerViewsAtDay;
    }

    public Integer getOfferPhonesViewsCountByDate(LocalDate offerDtCreate, LocalDate date) {
        int numOfDaysBetween = (int) RentOfferDateUtil.calculateDaysDiffBetweenDates(offerDtCreate, date);
        Integer[] phoneViewsArray = getViewStatistic().getTotalOfferPhonesViews();
        if (numOfDaysBetween < 0 || numOfDaysBetween >= phoneViewsArray.length) {
            return 0;
        }
        Integer phoneViewsAtDay = phoneViewsArray[numOfDaysBetween];
        return phoneViewsAtDay == null ? 0 : phoneViewsAtDay;
    }

    public Integer getTotalOfferViewsCount() {
        int count = 0;
        Integer[] totalOfferViews = getViewStatistic().getTotalOfferViews();
        for (Integer dayOfferViews : totalOfferViews) {
            count += dayOfferViews != null ? dayOfferViews : 0;
        }
        return count;
    }

    public Integer getTotalOfferPhonesViewsCount() {
        int count = 0;
        Integer[] totalOfferPhonesViews = getViewStatistic().getTotalOfferPhonesViews();
        for (Integer dayPhonesViews : totalOfferPhonesViews) {
            count += dayPhonesViews != null ? dayPhonesViews : 0;
        }
        return count;
    }
}