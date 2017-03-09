package ua.com.itproekt.gup.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CurrencyConvertUtil {

    public String toCurrencyConver(double inCount, int from, int to)
            throws IOException {
        double result = 0;
        Method m = null;
        try {
            m = CurrencyConvertUtil.class.getMethod("get Currency Currency: " + from, double.class, int.class);
            result = (double) m.invoke(new CurrencyConvertUtil(), inCount, to);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        String s = String.format("%.4f", result);
        return s;
    }

    public String toConver(double inCount, int from, int to) throws IOException {
        double result = 0;
        switch (from) {
            case 0:
                result = toConverUSD(inCount, to);
                break;
            case 1:
                result = toConverUAH(inCount, to);
                break;
            case 2:
                result = toConverUAH(inCount, to);
                break;
        }
        String s = String.format("%.4f", result);
        return s;
    }

    public double toConverUSD(double inCount, int to) {
        switch (to) {
            case 0:
                break;
            case 1:
                return inCount / rateUSD();
            case 2:
                return inCount / rateEUR();
            case 3:
                return inCount / rateUAH() * 10;
        }
        return Double.NaN;
    }

    public double toConverUAH(double inCount, int to) {
        switch (to) {
            case 0:
                return inCount * rateUSD();
            case 1:
                break;
            case 2:
                return inCount * rateUSD() / rateEUR();
            case 3:
                return inCount * rateUSD() / rateUAH() * 10;
        }
        return Double.NaN;
    }

    public double toConverEUR(double inCount, int to) {
        switch (to) {
            case 0:
                return inCount * rateEUR();
            case 1:
                return inCount * rateEUR() / rateUSD();
            case 2:
                break;
            case 3:
                return inCount * rateEUR() / rateUAH() * 10;
        }
        return Double.NaN;
    }

    private double rateEUR() {
        return 1.0;
    }

    private double rateUSD() {
        return 2.0;
    }

    private double rateUAH() {
        return 3.0;
    }

}
