package ua.com.itproekt.gup.service.offers;


import ua.com.itproekt.gup.util.ConvertUtil;

import java.util.GregorianCalendar;

public class Test {

    public static void main(String[] args) {
        Integer year = 2017;
        Integer month = 6;
        Integer day = 1;
        java.util.Calendar cal = new GregorianCalendar(year, (month-1), day);
        System.out.println( cal.getTime() );

//        String date = "15.06.2017";
        String date = "1497484800000";
        System.out.println( ConvertUtil.toDate(Long.valueOf(date)) );

        year = Integer.valueOf(ConvertUtil.toDate(Long.valueOf(date)).split("\\.")[2]);
        month = Integer.valueOf(ConvertUtil.toDate(Long.valueOf(date)).split("\\.")[1]);
        day = Integer.valueOf(ConvertUtil.toDate(Long.valueOf(date)).split("\\.")[0]);
        cal = new GregorianCalendar(year, (month-1), day);
        System.out.println( cal.getTime() );
    }

}
