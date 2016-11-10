package ua.com.itproekt.gup.service.offers.price;

import java.util.*;

public abstract class ARents extends ArrayList<List<Rent>> {

    public ARents(){
        add(new ArrayList<Rent>());
        add(new ArrayList<Rent>());
        add(new ArrayList<Rent>());
    }

    public ARents(long[] availables){
        List<Rent> lAvailables = new ArrayList<Rent>();
        for (long day : availables) lAvailables.add(new Rent(day, null, true, true, null, null, null, RentStatus.AVAILABLE, OrderStatus.NONE, 1, null));

        add(lAvailables);
        add(new ArrayList<Rent>());
        add(new ArrayList<Rent>());
    }

//    public ARents(long[] availables, long[] rented){
//        List<Rent> lAvailables = new ArrayList<Rent>(),
//                lRented = new ArrayList<Rent>();
//        for (long day : availables) lAvailables.add(new Rent(day, null, true, true, null, null, null, RentStatus.AVAILABLE, OrderStatus.NONE, 1, null));
//        for (long day : rented) lAvailables.add(new Rent(day, null, true, true, null, null, null, RentStatus.RENTED, OrderStatus.SUCCESSFULLY_ORDER, 1, null));
//
//        add(lAvailables);
//        add(lRented);
//        add(new ArrayList<Rent>());
//    }

    /**
     * #1. Условие:
     *     -- делать автоматическую проверку всех НЕактивных дней (переводить в НЕактивное состояние)
     * #2. Порядок:
     *     -- (a) при иннициализации, изначально все дни попадают в список - доступных
     *     -- (b) дни которые клиент арендует попадает в список - арендованых
     *     -- (c) после удаления арендованого дня он снова может вернуться в список - доступных
     *     -- (d) все просроченные дни попадают в список - просроченых (и больше из списка-просроченых они уже НЕмогут вернуться в другие списки-доступных-арендованых)
     */
    public List<Rent> getAvailables(){
        Collections.sort(get(0)); //Collections.sort(getFirst());
        return get(0); //return getFirst();
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
    public List<Rent> getRented(){
        Collections.sort(get(1)); //Collections.sort(getLast());
        return get(1); //return getLast();
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
    public List<Rent> getExpired(){
        List<Rent> expired = new ArrayList<Rent>();
        Date date = new Date();
        Long lDate = date.getTime();

        try {
            int countExpired = 0;
            for (List<Rent> e:this){
                /*
                 * 'Expired'
                 */
                if(++countExpired==2){ // |-1| |0| |1|
                    /*
                     * 'Availables'
                     */
                    if(getAvailables()!=null){
                        Iterator<Rent> iAvailables = getAvailables().iterator();
                        while (iAvailables.hasNext()){
                            Rent objAvailables = iAvailables.next();
                            if (objAvailables.getDay()<lDate){
                                iAvailables.remove();
                                expired.add(objAvailables);
                            }
                        }
                    }

                    /*
                     * 'Rented'
                     */
                    if(getAvailables()!=null){
                        Iterator<Rent> iRented = getRented().iterator();
                        while (iRented.hasNext()){
                            Rent objRented = iRented.next();
                            if (objRented.getDay()<lDate){
                                iRented.remove();
                                expired.add(objRented);
                            }
                        }
                    }
                }
            }
        } catch (NoSuchElementException e){
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        if(!expired.isEmpty()) set(2, expired);
        Collections.sort(get(2));
        return get(2);
    }


    /**
     * add Exception on getAvailables() + getRended() to getExpired()
     */

}
