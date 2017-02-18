package ua.com.itproekt.gup.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * @see https://habrahabr.ru/post/260773/
 * @see http://www.javaportal.ru/java/articles/regexp.html
 */

public class ValidatorUtilTest {

    @Before
    public void setUp() {

    }

    @After
    public void tearDown(){

    }

    /**
     * будем искать URL
     */
    @Test
    public void testValidURL(){
        assertTrue("http://habrahabr.ru/post/260767/", ValidatorUtil.checkURL("http://habrahabr.ru/post/260767/"));
    }

    /**
     * IP адрес
     */
    @Test
    public void testValidIP(){
        assertTrue("192.168.0.3", ValidatorUtil.checkIP("192.168.0.3"));
        assertFalse("192.168.0.3g", ValidatorUtil.checkIP("192.168.0.3g"));
    }

    /**
     * корректность e-mail адреса
     */
    @Test
    public void testValidEmail1(){
        assertTrue("c0nst@money.simply.net", ValidatorUtil.checkEmail("c0nst@money.simply.net"));
        assertTrue("Name.Sur_name@gmail.com", ValidatorUtil.checkEmail("Name.Sur_name@gmail.com"));
        assertFalse("somebody@dev.com.ua", ValidatorUtil.checkEmail("somebody@dev.com.ua"));
        assertFalse("user33@somewhere.in.the.net", ValidatorUtil.checkEmail("user33@somewhere.in.the.net"));
    }

    @Test
    public void testValidEmail2(){
        assertTrue("mail@mail.ru", ValidatorUtil.checkEmail2("mail@mail.ru"));
        assertTrue("mail@mail.org", ValidatorUtil.checkEmail2("mail@mail.org"));
        assertTrue("mail@mail.info", ValidatorUtil.checkEmail2("mail@mail.info"));
        assertTrue("mail@mail.", ValidatorUtil.checkEmail2("mail@mail."));
        assertTrue("mail@mail", ValidatorUtil.checkEmail2("mail@mail"));
        assertFalse("", ValidatorUtil.checkEmail2(""));
        assertFalse("sgfdsg", ValidatorUtil.checkEmail2("sgfdsg"));
        assertTrue("m.a.i.l@mail.ru", ValidatorUtil.checkEmail2("m.a.i.l@mail.ru"));
        assertFalse("_mail@mail.ru", ValidatorUtil.checkEmail2("_mail@mail.ru"));
        assertFalse("mail_@mail.ru", ValidatorUtil.checkEmail2("mail_@mail.ru"));
        assertFalse("mail@_mail.ru", ValidatorUtil.checkEmail2("mail@_mail.ru"));
        assertTrue("mail@mail_.ru", ValidatorUtil.checkEmail2("mail@mail_.ru"));
        assertFalse("1mail@mail.ru", ValidatorUtil.checkEmail2("1mail@mail.ru"));
        assertTrue("mail1@mail.ru", ValidatorUtil.checkEmail2("mail1@mail.ru"));
        assertTrue("mail@mail1.ru", ValidatorUtil.checkEmail2("mail@mail1.ru"));
        assertTrue("m___ail@mail.ru", ValidatorUtil.checkEmail2("m___ail@mail.ru"));
        assertTrue("C_fdhsfk4@mai32l.ru", ValidatorUtil.checkEmail2("C_fdhsfk4@mai32l.ru"));
        assertFalse("ma*il@mail.ru", ValidatorUtil.checkEmail2("ma*il@mail.ru"));
        assertFalse("mail@ma^il.ru", ValidatorUtil.checkEmail2("mail@ma^il.ru"));
        assertTrue("mail@mail.my.my1.ru", ValidatorUtil.checkEmail2("mail@mail.my.my1.ru"));
        assertFalse("@mail.my.my1.ru", ValidatorUtil.checkEmail2("@mail.my.my1.ru"));
        assertFalse("", ValidatorUtil.checkEmail2("mail@mail.my.my1.u"));
        assertFalse("mail@.ru", ValidatorUtil.checkEmail2("mail@.ru"));
        assertFalse("m l@ddd.ru", ValidatorUtil.checkEmail2("m l@ddd.ru"));
        assertFalse("mail@d d.r u", ValidatorUtil.checkEmail2("mail@d d.r u"));
    }

}
