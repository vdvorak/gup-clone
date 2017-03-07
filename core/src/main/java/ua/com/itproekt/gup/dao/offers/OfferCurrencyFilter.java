package ua.com.itproekt.gup.dao.offers;

import ua.com.itproekt.gup.model.offer.Currency;
import ua.com.itproekt.gup.util.CurrencyLocaleUtil;

public class OfferCurrencyFilter {

    private Long fromPriceUSD,
            toPriceUSD,
            fromPriceUAH,
            toPriceUAH,
            fromPriceEUR,
            toPriceEUR;

    public OfferCurrencyFilter(){

    }

    public OfferCurrencyFilter(Long fromPriceUSD, Long toPriceUSD, Long fromPriceUAH, Long toPriceUAH, Long fromPriceEUR, Long toPriceEUR){
        this.fromPriceUSD = fromPriceUSD;
        this.toPriceUSD = toPriceUSD;
        this.fromPriceUAH = fromPriceUAH;
        this.toPriceUAH = toPriceUAH;
        this.fromPriceEUR = fromPriceEUR;
        this.toPriceEUR = toPriceEUR;
    }

    public OfferCurrencyFilter(OfferCurrencyFilter offerCurrencyFilter){
        this.fromPriceUSD = offerCurrencyFilter.fromPriceUSD;
        this.toPriceUSD = offerCurrencyFilter.toPriceUSD;
        this.fromPriceUAH = offerCurrencyFilter.fromPriceUAH;
        this.toPriceUAH = offerCurrencyFilter.toPriceUAH;
        this.fromPriceEUR = offerCurrencyFilter.fromPriceEUR;
        this.toPriceEUR = offerCurrencyFilter.toPriceEUR;
    }

    public OfferCurrencyFilter (Long fromPrice, Long toPrice, Currency currency){
        OfferCurrencyFilter offerCurrencyFilter;
        if (currency == Currency.USD) {
            offerCurrencyFilter = new OfferCurrencyFilter(fromPrice,
                    Long.valueOf(CurrencyLocaleUtil.getAmountAsFormattedString(0.00, Double.valueOf(fromPrice), "UAH")),
                    Long.valueOf(CurrencyLocaleUtil.getAmountAsFormattedString(0.00, Double.valueOf(fromPrice), "EUR")),
                    toPrice,
                    Long.valueOf(CurrencyLocaleUtil.getAmountAsFormattedString(0.00, Double.valueOf(toPrice), "UAH")),
                    Long.valueOf(CurrencyLocaleUtil.getAmountAsFormattedString(0.00, Double.valueOf(toPrice), "EUR")));
        } else if (currency == Currency.UAH) {
            offerCurrencyFilter = new OfferCurrencyFilter(Long.valueOf(CurrencyLocaleUtil.getAmountAsFormattedString(0.00, Double.valueOf(fromPrice), "USD")),
                    fromPrice,
                    Long.valueOf(CurrencyLocaleUtil.getAmountAsFormattedString(0.00, Double.valueOf(fromPrice), "EUR")),
                    Long.valueOf(CurrencyLocaleUtil.getAmountAsFormattedString(0.00, Double.valueOf(toPrice), "USD")),
                    toPrice,
                    Long.valueOf(CurrencyLocaleUtil.getAmountAsFormattedString(0.00, Double.valueOf(toPrice), "EUR")));
        } else {
            offerCurrencyFilter = new OfferCurrencyFilter(Long.valueOf(CurrencyLocaleUtil.getAmountAsFormattedString(0.00, Double.valueOf(fromPrice), "USD")),
                    Long.valueOf(CurrencyLocaleUtil.getAmountAsFormattedString(0.00, Double.valueOf(fromPrice), "UAH")),
                    fromPrice,
                    Long.valueOf(CurrencyLocaleUtil.getAmountAsFormattedString(0.00, Double.valueOf(toPrice), "USD")),
                    Long.valueOf(CurrencyLocaleUtil.getAmountAsFormattedString(0.00, Double.valueOf(toPrice), "UAH")),
                    toPrice);
        }
        this.fromPriceUSD = offerCurrencyFilter.fromPriceUSD;
        this.toPriceUSD = offerCurrencyFilter.toPriceUSD;
        this.fromPriceUAH = offerCurrencyFilter.fromPriceUAH;
        this.toPriceUAH = offerCurrencyFilter.toPriceUAH;
        this.fromPriceEUR = offerCurrencyFilter.fromPriceEUR;
        this.toPriceEUR = offerCurrencyFilter.toPriceEUR;
    }

    public Long getFromPriceUSD() {
        return fromPriceUSD;
    }

    public void setFromPriceUSD(Long fromPriceUSD) {
        this.fromPriceUSD = fromPriceUSD;
    }

    public Long getToPriceUSD() {
        return toPriceUSD;
    }

    public void setToPriceUSD(Long toPriceUSD) {
        this.toPriceUSD = toPriceUSD;
    }

    public Long getFromPriceUAH() {
        return fromPriceUAH;
    }

    public void setFromPriceUAH(Long fromPriceUAH) {
        this.fromPriceUAH = fromPriceUAH;
    }

    public Long getToPriceUAH() {
        return toPriceUAH;
    }

    public void setToPriceUAH(Long toPriceUAH) {
        this.toPriceUAH = toPriceUAH;
    }

    public Long getFromPriceEUR() {
        return fromPriceEUR;
    }

    public void setFromPriceEUR(Long fromPriceEUR) {
        this.fromPriceEUR = fromPriceEUR;
    }

    public Long getToPriceEUR() {
        return toPriceEUR;
    }

    public void setToPriceEUR(Long toPriceEUR) {
        this.toPriceEUR = toPriceEUR;
    }

    @Override
    public String toString() {
        return "OfferCurrencyFilter{" +
                "fromPriceUSD=" + fromPriceUSD +
                ", toPriceUSD=" + toPriceUSD +
                ", fromPriceUAH=" + fromPriceUAH +
                ", toPriceUAH=" + toPriceUAH +
                ", fromPriceEUR=" + fromPriceEUR +
                ", toPriceEUR=" + toPriceEUR +
                '}';
    }
}
