package ua.com.gup.util;

import org.hibernate.validator.constraints.Range;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static org.junit.Assert.fail;


public class Validator3UtilTest {

    @Before
    public void setUp() {

    }

    @After
    public void tearDown(){

    }

    @NotNull(message="Имя должно быть задано")
    String firstname;

    @NotNull(message="Фамилия должна быть задана")
    @Size(min=3, message="Длина фамилии должна быть больше трех")
    String lastname;

    @NotNull(message="Имэйл должен быть задан")
    @Pattern(regexp = "^(?:[a-zA-Z0-9_'^&/+-])+(?:\\.(?:[a-zA-Z0-9_'^&/+-])+)" +
            "*@(?:(?:\\[?(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))\\.)" +
            "{3}(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\]?)|(?:[a-zA-Z0-9-]+\\.)" +
            "+(?:[a-zA-Z]){2,}\\.?)$",
            message = "заданный имэйл не может существовать")
    String email;

    @NotNull(message="Описание должно быть задано")
    @Range(min=1, max=100, message="Размер текста выходит за рамки допустимого")
    int age;

    @Test
    public void testValidator3(){
        Validator3UtilTest user = new Validator3UtilTest();

        user.firstname = "Вася";

//        user.lastname = "Пу";
        user.lastname = "Пупкин";

//        user.email = "вася пупкин@example.com";
        user.email = "vasya.poupkine@example.com";

//        user.age = 199;
        user.age = 99;

        if (!Validator3Util.validate(user)) fail();
    }

}


