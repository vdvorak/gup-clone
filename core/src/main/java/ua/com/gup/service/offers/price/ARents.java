package ua.com.gup.service.offers.price;

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

    public ARents(List<Rent> lAvailables, List<Rent> lRented, List<Rent> lExpired){
        add(lAvailables);
        add(lRented);
        add(lExpired);
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
    public List<Rent> getAvailables(){
        Collections.sort(get(0));
        return get(0);
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
        Collections.sort(get(1));
        return get(1);
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
                if(++countExpired==2){ //TODO |-1| |0| |1|
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
     * TODO: add Exception on getAvailables() + getRended() to getExpired()
     */

}
