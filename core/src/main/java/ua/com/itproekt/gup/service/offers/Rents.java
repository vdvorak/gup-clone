package ua.com.itproekt.gup.service.offers;

import ua.com.itproekt.gup.service.offers.price.Rent;
import ua.com.itproekt.gup.service.offers.price.ARents;

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

    private Rents(long[] availables){
        super(availables);
    }

//    private Rents(long[] availables, long[] rented){
//        super(availables, rented);
//    }

    private Rents(List<Rent> availables, List<Rent> rented, List<Rent> expired){
        super(availables, rented, expired);
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

//    public static Rents getInstance(long[] availables, long[] rented) {
//        Rents localInstance = instance;
//        if (localInstance == null) {
//            synchronized (Rents.class) {
//                localInstance = instance;
//                if (localInstance == null) {
//                    instance = localInstance = new Rents(availables, rented);
//                }
//            }
//        }
//        return localInstance;
//    }

    public static Rents getInstance(List<Rent> availables, List<Rent> rented, List<Rent> expired) {
        Rents localInstance = instance;
        if (localInstance == null) {
            synchronized (Rents.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Rents(availables, rented, expired);
                }
            }
        }
        return localInstance;
    }

//    ///////////////////////////////////////////////////////////////////////
//    // http://javaconceptoftheday.com/how-to-modify-an-arraylist/
//    ///////////////////////////////////////////////////////////////////////
//    public static void main(String[] args) {
////        ArrayList<String> list = new ArrayList<String>();
////
////        list.add("ONE");     //Adds "ONE" at the end of the list
////        list.add("TWO");     //Adds "TWO" at the end of the list
////        list.add("THREE");   //Adds "THREE" at the end of the list
////        list.add("FOUR");    //Adds "FOUR" at the end of the list
////        System.out.println("1: " + list);    //Output : [ONE, TWO, THREE, FOUR]
////
////        list.add(3, "INSERTED");   //Inserts "INSERTED" at position 3
////        System.out.println("2: " + list);   //Output : [ONE, TWO, THREE, INSERTED, FOUR]
////
////        list.add(1, "INSERTED");   //Inserts "INSERTED" at position 1
////        System.out.println("3: " + list);     //Output : [ONE, INSERTED, TWO, THREE, INSERTED, FOUR]
////
////        list.remove("INSERTED");    //Removes first occurence of "INSERTED"
////        System.out.println("4: " + list);     //Output : [ONE, TWO, THREE, INSERTED, FOUR]
////
////        list.remove(3);           //Removes an element at position 3
////        System.out.println("5: " + list);     //Output : [ONE, TWO, THREE, FOUR]
////
////        list.set(3, "REPLACED");    //Replaces an element at position 3 with "REPLACED"
////        System.out.println("6: " + list);     //Output : [ONE, TWO, THREE, REPLACED]
//
//        ArrayList<String> list1 = new ArrayList<String>();
//
//        list1.add("ONE");
//        list1.add("TWO");
//        list1.add("THREE");
//        list1.add("FOUR");
//        System.out.println("1: " + list1);     //Output : [ONE, TWO, THREE, FOUR]
//
//        ArrayList<String> list2 = new ArrayList<String>();
//        list2.add("THREE");
//        list2.add("FOUR");
//        list2.add("FIVE");
//        list2.add("SIX");
//        System.out.println("2: " + list2);     //Output : [THREE, FOUR, FIVE, SIX]
//
//        list1.addAll(list2);   //Appends list2 at the end of list1
//        System.out.println("3: " + list1);    //Output : [ONE, TWO, THREE, FOUR, THREE, FOUR, FIVE, SIX]
//
////        list1.removeAll(list2);    //Removes the elements of list1 which are also elements of list2
////        System.out.println("4: " + list1);    //Output : [ONE, TWO]
////
////        list1.addAll(2, list2);    //Inserts all elements of list2 into list1 at position 2
////        System.out.println("5: " + list1);    //Output : [ONE, TWO, THREE, FOUR, FIVE, SIX]
//
//        list1.retainAll(list2);    //Retains all elements of list1 which are also elements of list2 /// удаляет все остальные элементы которые НЕсоответствуют элементам списка...
//        System.out.println("6: " + list1);    //Output : [THREE, FOUR, FIVE, SIX]
//
////        list1.clear();      //Removes all elements of list1
////        System.out.println("7: " + list1);   //Output : []
//
//        // >> http://stackoverflow.com/questions/4352885/how-do-i-update-the-element-at-a-certain-position-in-an-arraylist
//        // >> http://codingrus.ru/readarticle.php?article_id=651
//        /*
//        // This method implements Iterable.
//        public Iterator iterator() {
//            return this;
//        }
//        */
//        list1.add(3, "333-1");
//        System.out.println("7: " + list1);
//        list1.add(3, "333-2");
//        System.out.println("8: " + list1);
//        list1.set(3, "333-3");
//        System.out.println("9: " + list1);
//
//        Iterator<String> iterator = list1.iterator();
//        while (iterator.hasNext()){
//            String i = iterator.next();
//            if (i.equals("333-1")||i.equals("333-2")||i.equals("333-3")){
//                System.out.println("> ");
//                iterator.remove();
//            } else {
//                System.out.println("> " + i);
//            }
//        }
//
//        iterator = list1.iterator();
//        while (iterator.hasNext()){
//            System.out.println(">> " + iterator.next());
//        }
//
////        //////////////////////////////////////////////////////////////
////        ArrayList<String> x = new ArrayList<String>();
////        x.add("A1");
////        x.add("A2");
////        x.add("A3");
////        x.add("B1");
////        x.add("B2");
////        x.add("B3");
////        Iterator<String> a = x.iterator();
////        Iterator<String> b = x.iterator();
////        while (a.hasNext()){
////            String ia = a.next();
////            if (ia.equals("A1")){
////                a.remove();
////                while (b.hasNext()){
////                    String ib = b.next(); // java.util.ConcurrentModificationException
////                    if (ib.equals("B1")){
////                        b.remove();
////                    }
////                }
////            }
////        }
////        Iterator<String> c = x.iterator();
////        while (c.hasNext()){
////            System.out.println(">>> " + c.next());
////        }
//    }

}
