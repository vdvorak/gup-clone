package ua.com.itproekt.gup.service.offers.price;

import java.util.*;

public abstract class ARents2 extends ArrayList<List<Rent2>> {

    public ARents2(){
        add(new ArrayList<Rent2>());
        add(new ArrayList<Rent2>());
        add(new ArrayList<Rent2>());
    }

    public ARents2(long[] availables){
        List<Rent2> lAvailables = new ArrayList<Rent2>();
        for (long day : availables) lAvailables.add(new Rent2(day, null, true, true, null, null, null, RentStatus.AVAILABLE, OrderStatus.NONE, 1, null));

        add(lAvailables);
        add(new ArrayList<Rent2>());
        add(new ArrayList<Rent2>());
    }

    public ARents2(long[] availables, long[] rented){
        List<Rent2> lAvailables = new ArrayList<Rent2>(),
                lRented = new ArrayList<Rent2>();
        for (long day : availables) lAvailables.add(new Rent2(day, null, true, true, null, null, null, RentStatus.AVAILABLE, OrderStatus.NONE, 1, null));
        for (long day : rented) lAvailables.add(new Rent2(day, null, true, true, null, null, null, RentStatus.RENTED, OrderStatus.NONE, 1, null));

        add(lAvailables);
        add(lRented);
        add(new ArrayList<Rent2>());
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
    public List<Rent2> getAvailables(){
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
    public List<Rent2> getRented(){
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
                        Iterator<Rent2> iAvailables = getAvailables().iterator();
                        while (iAvailables.hasNext()){
                            Rent2 objAvailables = iAvailables.next();
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
                        Iterator<Rent2> iRented = getRented().iterator();
                        while (iRented.hasNext()){
                            Rent2 objRented = iRented.next();
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
