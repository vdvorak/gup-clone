package ua.com.gup.rent.util;

public class SpELUtil {
    public static String name(Class clazz) {
        return clazz == null ? null : clazz.getName();
    }
}
