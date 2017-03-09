package ua.com.itproekt.gup.dao.offers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.com.itproekt.gup.util.CurrencyConvertUtil;

import java.io.IOException;

public class CurrencyConvertUtilTest {

    private CurrencyConvertUtil convert;

    @Before
    public void setUp() {
        convert = new CurrencyConvertUtil();
    }

    @After
    public void tearDown(){

    }

    @Test
    public void testCurrencyConvert() throws IOException {
        System.out.println("Current Currency = " + convert.toCurrencyConver(10, 1, 2));
        System.out.println("Conver           =   " + convert.toConver(10, 1, 2));

        System.out.println();
        System.out.println("Conver = " + convert.toConver(10, 1, 0)); //20,0000
        System.out.println("Conver = " + convert.toConver(10, 1, 1)); //NaN
        System.out.println("Conver = " + convert.toConver(10, 1, 2)); //20,0000
        System.out.println("Conver = " + convert.toConver(10, 1, 3)); //66,6667
        System.out.println("Conver = " + convert.toConver(10, 1, 4)); //NaN

//        System.out.println("Conver = " + convert.toConver(5, 1, 0)); //10,0000
//        System.out.println("Conver = " + convert.toConver(5, 1, 1)); //NaN
//        System.out.println("Conver = " + convert.toConver(5, 1, 2)); //10,0000
//        System.out.println("Conver = " + convert.toConver(5, 1, 3)); //33,3333
//        System.out.println("Conver = " + convert.toConver(5, 1, 4)); //NaN
//
//        System.out.println("Conver = " + convert.toConver(5, 2, 0)); //5,0000
//        System.out.println("Conver = " + convert.toConver(5, 2, 2)); //NaN
//        System.out.println("Conver = " + convert.toConver(5, 2, 3)); //16,6667
    }
}
