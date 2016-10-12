package ua.com.itproekt.gup.service.offers;

import com.google.gson.Gson;
import ua.com.itproekt.gup.service.offers.CalendarStatusService;
import ua.com.itproekt.gup.service.offers.calendar.Status;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class RentStatusService extends ConcurrentLinkedQueue<Status> {

    private static volatile Boolean initDate;
    private static String formatter = "d.MM.yyyy";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);
    private Boolean weekdayStatus,weekendStatus;
    private Boolean[][] weekdays,weekends;
    private ArrayList<Boolean> listWeekdays,listWeekends;
    private Gson gson;

    /**
     * #3. One cost (per day); Two dates (start/stop):
     *     ---------------------------------------------------------------
     *     - Only during this period (initial - final date) established a special tax;
     *       -- it can set a special price of only one day (start/end dates are the same)
     *       -- possible through the default constructor (without parameters)
     *
     * #4. One cost (per day); Undated (empty array):
     *     ----------------------------------------------------------
     *     - During the default period (three full months) established a special tax;
     *       -- default period (three full months) is determined based on the current date
     *       -- possible through the default constructor (without parameters)
     */
    protected RentStatusService(){}

    /**
     * #1. One cost (per day); Two dates (start/stop):
     *     --------------------------------------------------------------
     *     - During the default period (three full months) is established taking into account the cost of weekdays and weekends;
     *       -- default period (three full months) is determined based on the specified dates (start/end)
     *       -- through constructor with parameters
     *     - During this period (start-end dates) established a special tax;
     *       -- possible through the default constructor (without parameters)
     *
     * #2. Two costs (weekdays/weekend); Undated (empty array):
     *     ---------------------------------------------------------
     *     - During the default period (three full months) is established taking into account the cost of weekdays and weekends;
     *       -- default period (three full months) is determined based on the current date
     *       -- through constructor with parameters
     */
    protected RentStatusService(Boolean weekdayStatus, Boolean weekendStatus){
        this.weekdayStatus = weekdayStatus;
        this.weekendStatus = weekendStatus;
        initDate = null;
        listWeekdays = new ArrayList<Boolean>();
        listWeekends = new ArrayList<Boolean>();
        gson = new Gson();
    }

    /**
     * #1. Setting costs of used constructor with parameters;
     *     -- (optional)
     * #2. Initialization-days period, used a method addPrices(), according to the previously specified value;
     *     -- it can be used with the default constructor (without parameters)
     */
    public void addStatuses(Boolean status, Long[] days) {
        Status newStatus;
        switch (days.length) {
            case 0:
                java.util.Calendar calendar = java.util.Calendar.getInstance();
                initDate(calendar.getTimeInMillis());
                break;
            case 1:
                initDate(days[0]);
                synchronized (CalendarStatusService.class){
                    newStatus = new Status(status);
                    for (Status curStatus : this)
                        if (curStatus.remove(days[0])) newStatus.add(days[0]);
                    add(newStatus);
                }
                break;
            case 2:
                initDate(days);
                synchronized (CalendarStatusService.class){
                    newStatus = new Status(status);
                    java.util.Calendar lastDate = new GregorianCalendar(Integer.valueOf(convertDate(days[1]).split("\\.")[2]), (Integer.valueOf(convertDate(days[1]).split("\\.")[1])-1), Integer.valueOf(convertDate(days[1]).split("\\.")[0]));
                    for (java.util.Calendar currDate = new GregorianCalendar(Integer.valueOf(convertDate(days[0]).split("\\.")[2]), (Integer.valueOf(convertDate(days[0]).split("\\.")[1])-1), Integer.valueOf(convertDate(days[0]).split("\\.")[0])); currDate.getTimeInMillis()<=lastDate.getTimeInMillis(); currDate.add(java.util.Calendar.DATE, 1)) {
                        for (Status currStatus : this)
                            if (currStatus.remove(currDate.getTimeInMillis())) newStatus.add(currDate.getTimeInMillis());
                    }
                    add(newStatus);
                }
                break;
            default:
                break;
        }
    }

    public Integer delStatuses(Long[] days) {
        Integer del;
        synchronized (CalendarStatusService.class){
            del = 0;
            switch (days.length) {
                case 1:
                    for (Status curStatus : this)
                        if (curStatus.remove(days[0])) del++;
                    break;
                case 2:
                    java.util.Calendar lastDate = new GregorianCalendar(Integer.valueOf(convertDate(days[1]).split("\\.")[2]), (Integer.valueOf(convertDate(days[1]).split("\\.")[1])-1), Integer.valueOf(convertDate(days[1]).split("\\.")[0]));
                    for (java.util.Calendar currDate = new GregorianCalendar(Integer.valueOf(convertDate(days[0]).split("\\.")[2]), (Integer.valueOf(convertDate(days[0]).split("\\.")[1])-1), Integer.valueOf(convertDate(days[0]).split("\\.")[0])); currDate.getTimeInMillis()<=lastDate.getTimeInMillis(); currDate.add(java.util.Calendar.DATE, 1)){
                        for (Status curStatus : this)
                            if (curStatus.remove(currDate.getTimeInMillis())) del++;
                    }
                    break;
                default:
                    break;
            }
        }
        return del;
    }

    public Boolean isStatus(Long day) {
        for (Status statuses : this)
            if (statuses.contains(day)) return true;
        return false;
    }

    public Boolean getStatus(Long day){
        Boolean status = false;
        for (Status statuses : this)
            if (statuses.contains(day)) status = true;
        return status;
    }

    public Boolean getStatus(Long[] days){
        Boolean status = false;
        for (Status statuses : this) {
            for (Long day : days)
                if (statuses.contains(day)) status = true;
        }
        return status;
    }

    @Override
    public String toString() {
        StringBuilder data = new StringBuilder();
        for (Status statuses : this) {
            for (Long status : statuses) data.append(statuses.get() + "(" + convertDate(status) + ") ");
            if (!statuses.isEmpty()) data.append("\n");
        }
        return data.toString();
    }

    public String toJson() {
        return gson.toJson(this);
    }


    private void init(){
        synchronized (CalendarStatusService.class){
            if (initDate==null){
                weekdays = new Boolean[1][listWeekdays.size()];
                weekends = new Boolean[1][listWeekends.size()];
                weekdays[0] = listWeekdays.toArray(new Boolean[listWeekdays.size()]);
                weekends[0] = listWeekends.toArray(new Boolean[listWeekends.size()]);

//                Status weekdaysStatus = new Status(weekdayStatus);
//                for (Boolean lWeekday:weekdays[0]) weekdaysStatus.add(lWeekday); //FIXME: ConcurrentLinkedQueue add(E e)
//                add(weekdaysStatus);
//                Status weekendsStatus = new Status(weekendStatus);
//                for (Boolean lWeekend:weekends[0]) weekendsStatus.add(lWeekend); //FIXME: ConcurrentLinkedQueue add(E e)
//                add(weekendsStatus);
            }
        }
    }

    private void initDate(Integer month, Integer year){
        java.util.Calendar cal = new GregorianCalendar(year, (month-1), 1);
        do {
            String strMonth = month<10 ? "0" + month : String.valueOf(month),
                    strDay = cal.get(java.util.Calendar.DAY_OF_MONTH)<10 ? "0"+cal.get(java.util.Calendar.DAY_OF_MONTH) : String.valueOf(cal.get(java.util.Calendar.DAY_OF_MONTH));
            int day = cal.get(java.util.Calendar.DAY_OF_WEEK);
//            if (day != java.util.Calendar.SATURDAY && day != java.util.Calendar.SUNDAY)
//                listWeekdays.add(convertDate(strDay + "." + strMonth + "." + year)); //FIXME: ConcurrentLinkedQueue add(E e)
//            else
//                listWeekends.add(convertDate(strDay + "." + strMonth + "." + year)); //FIXME: ConcurrentLinkedQueue add(E e)
            cal.add(java.util.Calendar.DAY_OF_YEAR, 1);
        } while (cal.get(java.util.Calendar.MONTH) == (month-1));
    }

    private void initDate(Long day){
        synchronized (CalendarStatusService.class) {
            if (initDate == null) {
                initDate(Integer.valueOf(convertDate(day).split("\\.")[1]), Integer.valueOf(convertDate(day).split("\\.")[2]));
                init();

                initDate = new Boolean(true);
            }
        }
    }

    private void initDate(Long[] days){
        synchronized (CalendarStatusService.class) {
            if (initDate == null) {
                Arrays.sort(days);
                java.util.Calendar lastCal = new GregorianCalendar(Integer.valueOf(convertDate(days[days.length-1]).split("\\.")[2]), (Integer.valueOf(convertDate(days[days.length-1]).split("\\.")[1])-1), 1);
                for (java.util.Calendar currCal = new GregorianCalendar(Integer.valueOf(convertDate(days[0]).split("\\.")[2]), (Integer.valueOf(convertDate(days[0]).split("\\.")[1])-1), 1); currCal.getTimeInMillis()<=lastCal.getTimeInMillis(); currCal.add(java.util.Calendar.MONTH, 1))
                    initDate(Integer.valueOf(String.valueOf(simpleDateFormat.format(currCal.getTime())).split("\\.")[1]), Integer.valueOf(String.valueOf(simpleDateFormat.format(currCal.getTime())).split("\\.")[2]));
                init();

                initDate = new Boolean(true);
            }
        }
    }

    private Long convertDate(String day) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy", Locale.ENGLISH);
        LocalDate localDate = LocalDate.parse(day, formatter);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return date.getTime();
    }

    private String convertDate(Long day) {
        Date date = new Date(day);
        String strDate = simpleDateFormat.format(date);
        return strDate;
    }

    private Long[] convertDate(String[] days) {
        Long[] lDate = new Long[days.length];
        for (int date=0; date<days.length; ++date)
            lDate[date] = convertDate(days[date]);
        return lDate;
    }
}
