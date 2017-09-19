package ua.com.itproekt.gup.server.api.rest.offers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.com.itproekt.gup.model.offer.paidservices.Marked;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class OfferPaidRestControllerTest {

    private OfferPaidRestController offerPaidRestController;

    @Before
    public void setUp() {
        offerPaidRestController = new OfferPaidRestController();
    }

    @After
    public void tearDown() {
        offerPaidRestController = null;
    }

    @Test
    public void testDate() {
        long addLastDate1 = 1473973200000l;
        long addLastDate2 = 1475787600000l; // 1457301600000l  1475787600000l
        int addMonth = Marked.THREE.period();

        try {
            System.out.println("(add)date: '" + convertDate(offerPaidRestController.addDate(addMonth)) + "' (add)last-date: '" + convertDate(offerPaidRestController.addDate(addLastDate1, addMonth)) + "'");
        } catch (ParseException e) { System.err.println("Incorrect Data Format"); }

        try {
            System.out.println("(add)date: '" + convertDate(offerPaidRestController.addDate(addMonth)) + "' (add)last-date: '" + convertDate(offerPaidRestController.addDate(addLastDate2, addMonth)) + "'");
        } catch (ParseException e) { System.err.println("Incorrect Data Format"); }
    }


    /**
     * <DAY.MONTH.YEAR> >> (Long)
     * ("30.06.2014" >> 1472936400000)
     *
     * @param strDate
     * @return
     */
    private Long convertDate(String strDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy", Locale.ENGLISH);
        LocalDate localDate = LocalDate.parse(strDate, formatter);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return date.getTime();
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
        String strDate = OfferPaidRestController.simpleDateFormat.format(date);
        return strDate;
    }

}
