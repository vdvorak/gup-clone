package ua.com.itproekt.gup.service.offers;

import ua.com.itproekt.gup.service.offers.price.ARents2;
import ua.com.itproekt.gup.service.offers.price.Price;
import ua.com.itproekt.gup.service.offers.price.Rent2;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @see http://ru.stackoverflow.com/questions/27770/Зачем-нужны-статические-не-вложенные-классы
 * ********************************************************************************************
 * Вы не сможете скомпилировать static class A { }, если он не объявлен внутри другого класса. Просто такая конструкция не поддерживается в java.
 * Статические классы не могут быть созданы на верхнем уровне. Если речь о Static Nested Classes, то:
   class OuterClass {
       static class StaticNestedClass {
       }
       class InnerClass {
       }
   }
 *
 * Статические классы можно создавать без экземпляра класса(enclosing class), в котором он (статический класс) описан.
   OuterClass.StaticNestedClass nestedObject = new OuterClass.StaticNestedClass();
 *
 * Однако это не отменяет того факта, что статический класс (как паттерн) в java присутствует. Достаточно написать ненаследуемый класс с закрытым конструктором.
 *
 * Статические классы верхнего уровня нельзя создавать.
 * Что касается nested классов, то они имеют несколько различий по сравнению с Inner классами:
 * - Объекты данных классов не содержат ссылки на объект класса в котором он находится (enclosing class). Соответственно, меньше overhead'а по памяти.
 * - Создание объекта Nested класса не требует объекта enclosing класса, т.е. создается только один объект: new Enclosing.Nested()
 */
public class Rents2 extends ARents2 {

    private static volatile Rents2 instance;

    private Rents2(){
        super();
    }

    private Rents2(long[] availables){
        super(availables);
    }

    private Rents2(long[] availables, long[] rented){
        super(availables, rented);
    }

    public static Rents2 getInstance() {
        Rents2 localInstance = instance;
        if (localInstance == null) {
            synchronized (Rents2.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Rents2();
                }
            }
        }
        return localInstance;
    }

    public static Rents2 getInstance(long[] availables) {
        Rents2 localInstance = instance;
        if (localInstance == null) {
            synchronized (Rents2.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Rents2(availables);
                }
            }
        }
        return localInstance;
    }

    public static Rents2 getInstance(long[] availables, long[] rented) {
        Rents2 localInstance = instance;
        if (localInstance == null) {
            synchronized (Rents2.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Rents2(availables, rented);
                }
            }
        }
        return localInstance;
    }

}
