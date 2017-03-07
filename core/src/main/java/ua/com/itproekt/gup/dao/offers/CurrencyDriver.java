package ua.com.itproekt.gup.dao.offers;

import ua.com.itproekt.gup.model.offer.Currency;
import ua.com.itproekt.gup.util.CurrencyLocaleUtil;

/**
 * The currency driver
 */

public final class CurrencyDriver {
    private Long fromPriceUSD,
            toPriceUSD,
            fromPriceUAH,
            toPriceUAH,
            fromPriceEUR,
            toPriceEUR;

    private CurrencyDriver(){

    }

    public CurrencyDriver(Long fromPriceUSD, Long toPriceUSD, Long fromPriceUAH, Long toPriceUAH, Long fromPriceEUR, Long toPriceEUR){
        this.fromPriceUSD = fromPriceUSD;
        this.toPriceUSD = toPriceUSD;
        this.fromPriceUAH = fromPriceUAH;
        this.toPriceUAH = toPriceUAH;
        this.fromPriceEUR = fromPriceEUR;
        this.toPriceEUR = toPriceEUR;
    }

    public CurrencyDriver(CurrencyDriver driver){
        this.fromPriceUSD = driver.fromPriceUSD;
        this.toPriceUSD = driver.toPriceUSD;
        this.fromPriceUAH = driver.fromPriceUAH;
        this.toPriceUAH = driver.toPriceUAH;
        this.fromPriceEUR = driver.fromPriceEUR;
        this.toPriceEUR = driver.toPriceEUR;
    }

    public CurrencyDriver(Long fromPrice, Long toPrice, Currency currency){
        CurrencyDriver driver;
        if (currency == Currency.USD) {
            driver = new CurrencyDriver(fromPrice,
                    Long.valueOf(CurrencyLocaleUtil.getAmountAsFormattedString(0.00, Double.valueOf(fromPrice), "UAH")),
                    Long.valueOf(CurrencyLocaleUtil.getAmountAsFormattedString(0.00, Double.valueOf(fromPrice), "EUR")),
                    toPrice,
                    Long.valueOf(CurrencyLocaleUtil.getAmountAsFormattedString(0.00, Double.valueOf(toPrice), "UAH")),
                    Long.valueOf(CurrencyLocaleUtil.getAmountAsFormattedString(0.00, Double.valueOf(toPrice), "EUR")));
        } else if (currency == Currency.UAH) {
            driver = new CurrencyDriver(Long.valueOf(CurrencyLocaleUtil.getAmountAsFormattedString(0.00, Double.valueOf(fromPrice), "USD")),
                    fromPrice,
                    Long.valueOf(CurrencyLocaleUtil.getAmountAsFormattedString(0.00, Double.valueOf(fromPrice), "EUR")),
                    Long.valueOf(CurrencyLocaleUtil.getAmountAsFormattedString(0.00, Double.valueOf(toPrice), "USD")),
                    toPrice,
                    Long.valueOf(CurrencyLocaleUtil.getAmountAsFormattedString(0.00, Double.valueOf(toPrice), "EUR")));
        } else {
            driver = new CurrencyDriver(Long.valueOf(CurrencyLocaleUtil.getAmountAsFormattedString(0.00, Double.valueOf(fromPrice), "USD")),
                    Long.valueOf(CurrencyLocaleUtil.getAmountAsFormattedString(0.00, Double.valueOf(fromPrice), "UAH")),
                    fromPrice,
                    Long.valueOf(CurrencyLocaleUtil.getAmountAsFormattedString(0.00, Double.valueOf(toPrice), "USD")),
                    Long.valueOf(CurrencyLocaleUtil.getAmountAsFormattedString(0.00, Double.valueOf(toPrice), "UAH")),
                    toPrice);
        }
        this.fromPriceUSD = driver.fromPriceUSD;
        this.toPriceUSD = driver.toPriceUSD;
        this.fromPriceUAH = driver.fromPriceUAH;
        this.toPriceUAH = driver.toPriceUAH;
        this.fromPriceEUR = driver.fromPriceEUR;
        this.toPriceEUR = driver.toPriceEUR;
    }

    public boolean convert(Currency currency){
        if (0<fromPriceUSD
            && 0<toPriceUSD
            && 0<fromPriceUAH
            && 0<toPriceUAH
            && 0<fromPriceEUR
            && 0<toPriceEUR) {
            if (currency == Currency.USD) {
                Long fromPrice = fromPriceUSD;
                Long toPrice   = toPriceUSD;
                fromPriceUSD   = fromPriceUAH;
                toPriceUSD     = toPriceUAH;
                fromPriceUAH   = fromPriceEUR;
                toPriceUAH     = toPriceEUR;
                fromPriceEUR   = fromPrice;
                toPriceEUR     = toPrice;
            } else if (currency == Currency.UAH) {
                Long fromPrice = fromPriceUAH;
                Long toPrice   = toPriceUAH;
                fromPriceUAH   = fromPriceUSD;
                toPriceUAH     = toPriceUSD;
                fromPriceUSD   = fromPriceEUR;
                toPriceUSD     = toPriceEUR;
                fromPriceEUR   = fromPrice;
                toPriceEUR     = toPrice;
            } else {
                Long fromPrice = fromPriceEUR;
                Long toPrice   = toPriceEUR;
                fromPriceEUR   = fromPriceUSD;
                toPriceEUR     = toPriceUSD;
                fromPriceUSD   = fromPriceUAH;
                toPriceUSD     = toPriceUAH;
                fromPriceUAH   = fromPrice;
                toPriceUAH     = toPrice;
            }
            return true;
        }
        return false;
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
        return "CurrencyDriver{" +
                "fromPriceUSD=" + fromPriceUSD +
                ", toPriceUSD=" + toPriceUSD +
                ", fromPriceUAH=" + fromPriceUAH +
                ", toPriceUAH=" + toPriceUAH +
                ", fromPriceEUR=" + fromPriceEUR +
                ", toPriceEUR=" + toPriceEUR +
                '}';
    }
}
