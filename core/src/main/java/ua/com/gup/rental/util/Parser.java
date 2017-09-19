package ua.com.itproekt.gup.rental.util;


public class Parser {
    public static Integer toInteger(String text) {
        try {
            return new Integer(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
