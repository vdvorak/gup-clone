package ua.com.itproekt.gup.service.offers;

import ua.com.itproekt.gup.service.offers.price.Rent2;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Rents2 extends ConcurrentLinkedDeque<List<Rent2>> {

    private List<Rent2> availables,
            rented,
            expired;

    public Rents2(){
        add(new ArrayList<Rent2>());
        addFirst(new ArrayList<Rent2>());
        addLast(new ArrayList<Rent2>());
        init();
    }

    public void init(){
        setAvailables();
        setRented();
        setExpired();
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
    public void setAvailables(){
        try {
            availables = getFirst();
        } catch (NoSuchElementException e){
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public List<Rent2> getAvailables(){
        return availables;
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
    public void setRented(){
        try {
            rented = getLast();
        } catch (NoSuchElementException e){
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public List<Rent2> getRented(){
        return rented;
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
    public void setExpired(){
//        List<Rent2> availables = getAvailables(),
//                renteds = getRented(),
//                expireds = null;
        Date date = new Date();
        Long lDate = date.getTime();

        try {
            System.err.println( "-3" );
            int countExpired = 0;
            for (List<Rent2> expired:this){
                System.err.println( "-2 (=" + expired.size() + " " + getAvailables().equals(expired) + "=" + getAvailables().size() + " & " + getRented().equals(expired) + "=" + getRented().size() + ")" );
                /*
                 * 'Expired'
                 */
////                if (!getAvailables().equals(expired) && !getRented().equals(expired)){
//                if ((0<getAvailables().size() && !getAvailables().equals(expired))
//                        && (0<getRented().size() && !getRented().equals(expired))){
                if(++countExpired==2){
                    System.err.println( "-1" );
                    /*
                     * 'Availables'
                     */
                    if(getAvailables()!=null){
                        System.err.println( "#0" );
                        Iterator<Rent2> availables = getAvailables().iterator();
                        while (availables.hasNext()){
                            Rent2 objAvailables = availables.next();
                            System.err.println( "#1" );
                            if (objAvailables.getDay()<lDate){
                                System.err.println( "#2" );
                                expired.add(objAvailables);
                                getAvailables().remove(objAvailables);
                            }
                        }
                    }

                    /*
                     * 'Rented'
                     */
                    if(getRented()!=null){
                        Iterator<Rent2> rented = getRented().iterator();
                        while (rented.hasNext()){
                            Rent2 objRented = rented.next();
                            if (objRented.getDay()<lDate){
                                expired.add(objRented);
                                getRented().remove(objRented);
                            }
                        }
                    }

                    this.expired = expired;
                }
            }
        } catch (NoSuchElementException e){
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public List<Rent2> getExpired(){
        return expired;
    }

}
