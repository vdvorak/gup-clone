package ua.com.itproekt.gup.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @see https://habrahabr.ru/post/123845/
 */

public class Validator2UtilTest {

    @Before
    public void setUp() {

    }

    @After
    public void tearDown(){

    }

    /**
     * Номер кредитки:
     * ICQ:
     * Набор из букв и цифр (латиница):
     * Набор из букв и цифр (латиница + кириллица):
     * Домен (например abcd.com):
     * IPv4: (подкорректировано@runcore)
     * IPv6:
     * Имя пользователя (с ограничением 2-20 символов, которыми могут быть буквы и цифры, первый символ обязательно буква):
     * Пароль (Строчные и прописные латинские буквы, цифры):
     * Пароль (Строчные и прописные латинские буквы, цифры, спецсимволы. Минимум 8 символов):
     * Дата в формате YYYY-MM-DD:
     * UPD. Более строгая проверка, предложенная@runcore:
     * Дата в формате DD/MM/YYYY:
     * Целые числа и числа с плавающей точкой (разделитель точка):
     * UUID:
     * Широта или долгота:
     * UPD. E-mail (от@kvf77):
     * UPD. URL на латинице. Если нужно распознавать и кириллические домены, необходимо изменить все «a-z0-9» на «а-яёa-z0-9» и добавить в список доменных зон «рф» (от@kvf77):
     * UPD. Время в формате HH:MM:SS (от@runcore):
     * UPD. Mac-адрес (от@tiahin):
     */
    @Test
    public void testValidator2(){
        Validator2Util validator = Validator2Util.DD__MM__YYYY;
        System.out.println("Validator: " + validator);
        System.out.println("description: " + validator.description());
        System.out.println("value: " + validator.value());
    }

    /**
     * корректность e-mail адреса
     */
    @Test
    public void testValidEmail(){
        Validator2Util validator = Validator2Util.UPD_EMAIL;

        assertTrue("c0nst@money.simply.net", validator.check("c0nst@money.simply.net"));
        assertTrue("Name.Sur_name@gmail.com", validator.check("Name.Sur_name@gmail.com"));
        assertTrue("somebody@dev.com.ua", validator.check("somebody@dev.com.ua"));
        assertTrue("user33@somewhere.in.the.net", validator.check("user33@somewhere.in.the.net"));
    }

}
