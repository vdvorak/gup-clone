package ua.com.gup.util;


public class Base36Convertor {
    public static String encode(final long value) {
        return Long.toString(value, 36);
    }

    public static long decode(final String value) {
        return Long.parseLong(value, 36);
    }
}
