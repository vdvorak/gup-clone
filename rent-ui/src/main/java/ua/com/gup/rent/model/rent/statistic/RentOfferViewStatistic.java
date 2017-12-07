package ua.com.gup.rent.model.rent.statistic;

public class RentOfferViewStatistic {

    private Integer[] totalOfferViews;
    //    private List<Integer> uniqueOfferViewsCount;
    private Integer[] totalOfferPhonesViews;
//    private List<Integer> uniqueOfferPhonesViewsCount;

    public RentOfferViewStatistic() {
    }

    public Integer[] getTotalOfferViews() {
        if (totalOfferViews == null) {
            totalOfferViews = new Integer[]{};
        }
        return totalOfferViews;
    }

    public void setTotalOfferViews(Integer[] totalOfferViews) {
        this.totalOfferViews = totalOfferViews;
    }

//    public List<Integer> getUniqueOfferViewsCount() {
//        if (uniqueOfferViewsCount == null) {
//            uniqueOfferViewsCount = new ArrayList<>();
//        }
//        return uniqueOfferViewsCount;
//    }

//    public void setUniqueOfferViewsCount(List<Integer> uniqueOfferViewsCount) {
//        this.uniqueOfferViewsCount = uniqueOfferViewsCount;
//    }

    public Integer[] getTotalOfferPhonesViews() {
        if (totalOfferPhonesViews == null) {
            totalOfferPhonesViews = new Integer[]{};
        }
        return totalOfferPhonesViews;
    }

    public void setTotalOfferPhonesViews(Integer[] totalOfferPhonesViews) {
        this.totalOfferPhonesViews = totalOfferPhonesViews;
    }

//    public List<Integer> getUniqueOfferPhonesViewsCount() {
//        if (uniqueOfferPhonesViewsCount == null) {
//            uniqueOfferPhonesViewsCount = new ArrayList<>();
//        }
//        return uniqueOfferPhonesViewsCount;
//    }
//
//    public void setUniqueOfferPhonesViewsCount(List<Integer> uniqueOfferPhonesViewsCount) {
//        this.uniqueOfferPhonesViewsCount = uniqueOfferPhonesViewsCount;
//    }


}
