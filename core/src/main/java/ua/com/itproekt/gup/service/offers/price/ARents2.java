package ua.com.itproekt.gup.service.offers.price;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

public abstract class ARents2 extends ConcurrentLinkedDeque<List<Rent2>> {

    public ARents2(){
        add(new ArrayList<Rent2>());
        addFirst(new ArrayList<Rent2>());
        addLast(new ArrayList<Rent2>());
    }

    public ARents2(long[] availables){
        List<Rent2> lAvailables = new ArrayList<Rent2>();
        for (long day : availables) lAvailables.add(new Rent2(day, null, true, true, null, null, null, RentStatus.AVAILABLE, OrderStatus.NONE, 1, null));

        add(new ArrayList<Rent2>());
        addFirst(lAvailables);
        addLast(new ArrayList<Rent2>());
    }

    public ARents2(long[] availables, long[] rented){
        List<Rent2> lAvailables = new ArrayList<Rent2>(),
                lRented = new ArrayList<Rent2>();
        for (long day : availables) lAvailables.add(new Rent2(day, null, true, true, null, null, null, RentStatus.AVAILABLE, OrderStatus.NONE, 1, null));
        for (long day : rented) lAvailables.add(new Rent2(day, null, true, true, null, null, null, RentStatus.RENTED, OrderStatus.NONE, 1, null));

        add(new ArrayList<Rent2>());
        addFirst(lAvailables);
        addLast(lRented);
    }

    /**
     * #1. Условие:
     *     -- делать автоматическую проверку всех НЕактивных дней (переводить в НЕактивное состояние)
     * #2. Порядок:
     *     -- (a) при иннициализации, изначально все дни попадают в список - доступных
     *     -- (b) дни которые клиент арендует попадает в список - арендованых
     *     -- (c) после удаления арендованого дня он снова может вернуться в список - доступных
     *     -- (d) все просроченные дни попадают в список - просроченых (и больше из списка-просроченых они уже НЕмогут вернуться в другие списки-доступных-арендованых)
     */
//    public void setAvailables(){
//        try {
//            availables = getFirst();
//        } catch (NoSuchElementException e){
//            e.printStackTrace();
//        } catch (NullPointerException e){
//            e.printStackTrace();
//        }
//    }

    public List<Rent2> getAvailables(){
        return getFirst();
    }

    /**
     * #1. Условие:
     *     -- делать автоматическую проверку всех НЕактивных дней (переводить в НЕактивное состояние)
     * #2. Порядок:
     *     -- (a) при иннициализации, изначально все дни попадают в список - доступных
     *     -- (b) дни которые клиент арендует попадает в список - арендованых
     *     -- (c) после удаления арендованого дня он снова может вернуться в список - доступных
     *     -- (d) все просроченные дни попадают в список - просроченых (и больше из списка-просроченых они уже НЕмогут вернуться в другие списки-доступных-арендованых)
     */
//    public void setRented(){
//        try {
//            rented = getLast();
//        } catch (NoSuchElementException e){
//            e.printStackTrace();
//        } catch (NullPointerException e){
//            e.printStackTrace();
//        }
//    }

    public List<Rent2> getRented(){
        return getLast();
    }

    /**
     * #1. Условие:
     *     -- делать автоматическую проверку всех НЕактивных дней (переводить в НЕактивное состояние)
     * #2. Порядок:
     *     -- (a) при иннициализации, изначально все дни попадают в список - доступных
     *     -- (b) дни которые клиент арендует попадает в список - арендованых
     *     -- (c) после удаления арендованого дня он снова может вернуться в список - доступных
     *     -- (d) все просроченные дни попадают в список - просроченых (и больше из списка-просроченых они уже НЕмогут вернуться в другие списки-доступных-арендованых)
     */
//    public void setExpired(){
////        List<Rent2> availables = getAvailables(),
////                renteds = getRented(),
////                expireds = null;
//        Date date = new Date();
//        Long lDate = date.getTime();
//
//        try {
//            System.err.println( "-3" );
//            int countExpired = 0;
//            for (List<Rent2> expired:this){
//                System.err.println( "-2 (=" + expired.size() + " " + getAvailables().equals(expired) + "=" + getAvailables().size() + " & " + getRented().equals(expired) + "=" + getRented().size() + ")" );
//                /*
//                 * 'Expired'
//                 */
//////                if (!getAvailables().equals(expired) && !getRented().equals(expired)){
////                if ((0<getAvailables().size() && !getAvailables().equals(expired))
////                        && (0<getRented().size() && !getRented().equals(expired))){
//                if(++countExpired==2){
//                    System.err.println( "-1" );
//                    /*
//                     * 'Availables'
//                     */
//                    if(getAvailables()!=null){
//                        System.err.println( "#0" );
//                        Iterator<Rent2> availables = getAvailables().iterator();
//                        while (availables.hasNext()){
//                            Rent2 objAvailables = availables.next();
//                            System.err.println( "#1" );
//                            if (objAvailables.getDay()<lDate){
//                                System.err.println( "#2" );
//                                expired.add(objAvailables);
//                                getAvailables().remove(objAvailables);
//                            }
//                        }
//                    }
//
//                    /*
//                     * 'Rented'
//                     */
//                    if(getRented()!=null){
//                        Iterator<Rent2> rented = getRented().iterator();
//                        while (rented.hasNext()){
//                            Rent2 objRented = rented.next();
//                            if (objRented.getDay()<lDate){
//                                expired.add(objRented);
//                                getRented().remove(objRented);
//                            }
//                        }
//                    }
//
//                    this.expired = expired;
//                }
//            }
//        } catch (NoSuchElementException e){
//            e.printStackTrace();
//        } catch (NullPointerException e){
//            e.printStackTrace();
//        }
//    }

    public List<Rent2> getExpired(){
        List<Rent2> expired = new ArrayList<Rent2>();
        Date date = new Date();
        Long lDate = date.getTime();

        try {
            int countExpired = 0;
            for (List<Rent2> e:this){
                /*
                 * 'Expired'
                 */
                if(++countExpired==2){ // |-1| |0| |1|
                    /*
                     * 'Availables'
                     */
                    if(getAvailables()!=null){
                        List<Rent2> tmp = new ArrayList<Rent2>();
                        Iterator<Rent2> iAvailables = getAvailables().iterator();
                        while (iAvailables.hasNext()){
                            Rent2 objAvailables = new Rent2(iAvailables.next()); //Rent2 objAvailables = iAvailables.next();
                            if (objAvailables.getDay()<lDate){
//                                removeFirstOccurrence(objAvailables); // java.lang.ClassCastException
                                expired.add(objAvailables); //if(expired.contains(objAvailables)) expired.add(objAvailables);
//                                removeFirstOccurrence(objAvailables); // java.lang.ClassCastException
                            } else {
                                tmp.add(objAvailables);
                            }
                        }
//                        getAvailables().removeAll(expired); //TODO: ...
                        //removeFirst(); //removeFirstOccurrence();
////                        addFirst(tmp); //getAvailables().addAll(tmp);
                    }

                    /*
                     * 'Rented'
                     */
//                    if(getRented()!=null){
//                        Iterator<Rent2> rented = getRented().iterator();
//                        while (rented.hasNext()){
//                            Rent2 objRented = rented.next();
//                            if (objRented.getDay()<lDate){
//                                expired.add(objRented);
//                                getRented().remove(objRented);
//                            }
//                        }
//                    }

                }
            }
        } catch (NoSuchElementException e){
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        return expired;
    }


    /**
     * add Exception on getAvailables() + getRended() to getExpired()
     */

}
