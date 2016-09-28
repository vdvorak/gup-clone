package ua.com.itproekt.gup.service.offers.calendar;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
* @see http://stackoverflow.com/questions/3272454/in-java-get-all-weekend-dates-in-a-given-month
*/

public class PriceSchemeTest {

    private Logger logger = Logger.getLogger(PriceSchemeTest.class);

    private static String formatter = "d.MM.yyyy";
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter, Locale.ENGLISH);
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    public void printDays(int year, int month){
        System.out.print("\nweekdays: ");
        java.util.Calendar cal = new GregorianCalendar(year, month, 1);
        do {
            int day = cal.get(java.util.Calendar.DAY_OF_WEEK);
            if (day != java.util.Calendar.SATURDAY && day != java.util.Calendar.SUNDAY) {
                System.out.print(cal.get(java.util.Calendar.DAY_OF_MONTH) + "." + month + "." + year +  " ");
            }
            cal.add(java.util.Calendar.DAY_OF_YEAR, 1);
        }  while (cal.get(java.util.Calendar.MONTH) == month);

        System.out.println();
        System.out.print("weekends: ");
        cal = new GregorianCalendar(year, month, 1);
        do {
            int day = cal.get(java.util.Calendar.DAY_OF_WEEK);
            if (day == java.util.Calendar.SATURDAY || day == java.util.Calendar.SUNDAY) {
                System.out.print(cal.get(java.util.Calendar.DAY_OF_MONTH) + "." + month + "." + year +  " ");
            }
            cal.add(java.util.Calendar.DAY_OF_YEAR, 1);
        }  while (cal.get(java.util.Calendar.MONTH) == month);
    }

    public void printDays(Long[] aa){
        Arrays.sort(aa);
        for (Integer year=Integer.valueOf(convertDate(aa[0]).split("\\.")[2]); year<=Integer.valueOf(convertDate(aa[aa.length-1]).split("\\.")[2]); ++year){
            for (Integer month=Integer.valueOf(convertDate(aa[0]).split("\\.")[1]); month<=Integer.valueOf(convertDate(aa[aa.length-1]).split("\\.")[1]); ++month){
                printDays(year, month);
            }
        }
    }

    /**
     * Test(s) Output-Days
     */
    @Test
    public void testOutDays() {
        /////////////////////////////////////////////////////////////////////////
        Long aa[] = {1401570000000l,1401915600000l,1402174800000l,1402520400000l,1402779600000l,1403125200000l,1403384400000l,1403730000000l,1403989200000l,1404075600000l,1402002000000l,1402088400000l,1402606800000l,1402693200000l,1403211600000l,1403298000000l,1403816400000l,1403902800000l};
//        for (Long a : aa) System.out.print(convertDate(a) + " ");
//
//        System.out.println();
//        Arrays.sort(aa);
//        for (Long a : aa) System.out.print(convertDate(a) + " ");
//
//        System.out.println();
//        System.out.println("(min)" + convertDate(aa[0]) + " (max)" + convertDate(aa[aa.length-1]) + "");
//        System.out.println("(start-month)" + Integer.valueOf(convertDate(aa[0]).split("\\.")[1]) + " (last-month)" + Integer.valueOf(convertDate(aa[aa.length-1]).split("\\.")[1]) + "");
//        System.out.println("(start-year)" + Integer.valueOf(convertDate(aa[0]).split("\\.")[2]) + " (last-year)" + Integer.valueOf(convertDate(aa[aa.length-1]).split("\\.")[2]) + "");


        /////////////////////////////////////////////////////////////////////////
        printDays(aa);

        /////////////////////////////////////////////////////////////////////////
//        System.out.println(TestDays.valueOf("06.06.2014"));
        System.out.println(TestDays.ONE);


//        System.out.println("START:");
//        printWeekdays(Integer.valueOf(convertDate(aa[0]).split("\\.")[2]), Integer.valueOf(convertDate(aa[0]).split("\\.")[1]));
//        System.out.println("\nLAST:");
//        printWeekdays(Integer.valueOf(convertDate(aa[aa.length-1]).split("\\.")[2]), Integer.valueOf(convertDate(aa[aa.length-1]).split("\\.")[1]));
    }

    /**
     * (Long) >> <DAY.MONTH.YEAR>
     * 1472936400000 >> "30.06.2014"
     *
     * @param lDate
     * @return
     */
    private static String convertDate(long lDate) {
        Date date = new Date(lDate);
        String strDate = simpleDateFormat.format(date);
        return strDate;
    }

}
