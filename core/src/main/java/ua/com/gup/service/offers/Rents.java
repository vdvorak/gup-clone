package ua.com.gup.service.offers;

import ua.com.gup.service.offers.price.Rent;
import ua.com.gup.service.offers.price.ARents;

import java.util.List;

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
public class Rents extends ARents {

    private static volatile Rents instance;

    private Rents(){
        super();
    }

    private Rents(long[] lAvailables){
        super(lAvailables);
    }

    private Rents(List<Rent> lAvailables, List<Rent> lRented, List<Rent> lExpired){
        super(lAvailables, lRented, lExpired);
    }

    public static Rents getInstance(long[] availables) {
        Rents localInstance = instance;
        if (localInstance == null) {
            synchronized (Rents.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Rents(availables);
                }
            }
        }
        return localInstance;
    }

    public static Rents getInstance(List<Rent> lAvailables, List<Rent> lRented, List<Rent> lExpired) {
        Rents localInstance = instance;
        if (localInstance == null) {
            synchronized (Rents.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Rents(lAvailables, lRented, lExpired);
                }
            }
        }
        return localInstance;
    }

}
