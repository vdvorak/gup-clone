package ua.com.gup.util;

/**
 * Created by Fairy on 23.11.2015.
 */
public class InputValidator {
    private static String LOGIN_PATTERN = "[\\w]{3,19}";
    private static String PASSWORD_PATTERN = "[\\w]{4,15}";
    private static String EMAIL_PATTERN = "^\\w+[\\w-\\.]*\\@\\w+((-\\w+)|(\\w*))\\.[a-z]{2,3}$";
    private static String DATE_PATTERN = "[2][0]\\d{2}[-]([0][1-9]|[1][0-2])[-]([0-2][\\d]|[3][0,1])";
    private static String TIME_PATTERN = "([0][\\d]|[1][0-2]):([0-5][0-9]):([0-5][0-9])";

    public static boolean validateLogin(String login){
        return login.matches(LOGIN_PATTERN);
    }

    public static boolean validatePassword(String password){
        return password.matches(PASSWORD_PATTERN);
    }

    public static boolean validateEmail(String email){
        return email.matches(EMAIL_PATTERN);
    }

    public static boolean validateDate(String date){ return date.matches(DATE_PATTERN); }

    public static boolean validateTime(String time){ return time.matches(TIME_PATTERN);}
}