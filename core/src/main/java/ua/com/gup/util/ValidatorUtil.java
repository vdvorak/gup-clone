package ua.com.gup.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {

    private static final String    domenURL = "^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$";
    private static final String     domenIP = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
    private static final String  domenEmail = "[a-zA-Z]{1}[a-zA-Z\\d\\u002E\\u005F]+@([a-zA-Z]+\\u002E){1,2}((net)|(com)|(org))";
    private static final String domenEmail1 = "[a-z][a-z[0-9]\u005F\u002E\u002D]*[a-z||0-9]";
    private static final String domenEmail2 = "(net||org||ru||info)";


    public static final boolean checkURL(String url){
        Pattern pattern = Pattern.compile(domenURL);
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }

    public static final boolean checkIP(String ip){
        Pattern pattern = Pattern.compile(domenIP);
        Matcher matcher = pattern.matcher(ip);
        return matcher.matches();
    }

    public static final boolean checkEmail(String email) {
        Pattern pattern = Pattern.compile(domenEmail);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static final boolean checkEmail2(String email) {
        Pattern pattern = Pattern.compile(domenEmail1 + "@" + domenEmail1 + "\u002E" + domenEmail2);
        Matcher matcher = pattern.matcher(email.toLowerCase());
        return matcher.matches();
    }

}
