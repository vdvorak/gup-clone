package ua.com.itproekt.gup.service.offers;

import org.apache.commons.lang.ArrayUtils;
import ua.com.itproekt.gup.service.offers.price.Rent;

import java.util.Arrays;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Используется тип списка 'ConcurrentLinkedDeque' потому-что в многопоточном режиме будут часто выполнятся операции с добавлением-удалением элементов списка
 */
public class Rents extends ConcurrentLinkedDeque<Rent> {

    private Rent availables,
            rented,
            expired;

    public Rents(){
        add(new Rent());
        addFirst(new Rent());
        addLast(new Rent());
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

    public Rent getAvailables(){
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

    public Rent getRented(){
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
        Long[] availables = (Long[]) ArrayUtils.clone(getAvailables().get()),
                renteds = (Long[]) ArrayUtils.clone(getRented().get()),
                expireds = null;
        Date date = new Date();
        Long lDate = date.getTime();

        try {
            for (Rent expired:this){
            /*
             * 'Expired'
             */
                if (!getAvailables().equals(expired) && !getRented().equals(expired)){
                /*
                 * 'Availables'
                 */
                    if (getAvailables().get()!=null){
                    /*
                     * FIXME: Найти все ID-элементов дял совпадающих дат, И сформировать новый суб-массив (по условию найденных ID...)
                     */
                        for (Long day : getAvailables().get()){
                            if (day<lDate){
                                availables = (Long[]) ArrayUtils.removeElement(availables, day);
                                Arrays.sort( availables );
                                expireds = (Long[]) ArrayUtils.add(expireds, day);
                                Arrays.sort( expireds );
                            }
                        }
                        if (expireds!=null){
                            getAvailables().set(availables);
                            expired.set(expireds);
                        }
                    }
                /*
                 * 'Rented'
                 */
                    if (getRented().get()!=null){
                    /*
                     * FIXME: Найти все ID-элементов дял совпадающих дат, И сформировать новый суб-массив (по условию найденных ID...)
                     */
                        for (Long day : getRented().get()){
                            if (day<lDate){
                                renteds = (Long[]) ArrayUtils.removeElement(renteds, day);
                                expireds = (Long[]) ArrayUtils.add(expireds, day);
                                Arrays.sort( expireds );
                            }
                        }
                        if (expireds!=null){
                            getRented().set(renteds);
                            expired.set(expireds);
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

    public Rent getExpired(){
        return expired;
    }

}
