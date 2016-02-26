package ua.com.itproekt.gup.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Zver on 26.02.2016.
 */
public final class LogUtil {

    public static String getExceptionStackTrace(Exception ex) {
        StringWriter stack = new StringWriter();
        ex.printStackTrace(new PrintWriter(stack));
        return stack.toString();
    }
}
