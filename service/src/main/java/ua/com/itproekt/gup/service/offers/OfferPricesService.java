package ua.com.itproekt.gup.service.offers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.itproekt.gup.model.order.Order;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.service.offers.price.*;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.ConvertUtil;
import ua.com.itproekt.gup.util.SecurityOperations;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Три варианта оплаты:
 * 1. Оплата за товар-покупку (на постояное пользование);
 *    -- здесь выставляется только одна-фиксированная цена на товар
 *    -- и после покупки единица товара списывается (владение на постояной основе для одного клиента) ТОГДА заказ выполнен
 * 2. Полная оплата за аренду предмета-услуги (на временное пользование);
 *    -- здесь выставляются ценовые правила на конкретный период(ы) времени аренды товара-услуги
 *    -- после (полной) оплаты единица товара-услуги передается во временную експлуатацию для конкретного клиента ТОГДА заказ выполнен
 *    -- по истечении времени експлуатации эта единица товара-услуги переходит во временную експлуатацию уже для другого клиента
 * 3. Частичная предоплата за аренду предмета-услуги (предапрительное бронирование на временное пользование);
 *    -- Владелец объявления должен выбрать Пользоваться/НЕпользоваться услугой предоплаты-бронирования товара-услуги (при этом деньги за услугу получает только GUP)
 *       --- здесь выставляется одна-фиксированная цена предоплаты для возможности гарантированной аренды этого товара-услуги спустя через какое-то время
 *       --- здесь выставляется (1) короткое-время-действия для возможности гарантированной аренды (с запасом времени...при условии что это короткое-время-действия НЕбудет блокировать последнюю возможность для выполнения аренды товара-услуги)
 *       --- здесь выставляется (2) срок до которого разрешается вносить предоплату или до которого предоплата будет еще актуальной...
 *    -- при этом объявление будет только частично доступно-видно (для клиента который его бронировал-предоплату и должен внести полную оплату И для владельца этого объявления)
 *    -- в случае спустя через какое-то время если полная оплата небыла проведена - ТОГДА заказ анулируется-сбрасывается И аренда товара-услуги снова становиться доступной
 *
 * Гарантией выполнения клиентского-заказа на аренду товара/услуги - является контроль состояния заказа:
 * 1. Состояния выполнения заказа:
 *    -- в процессе выполнения заказа, он может иметь несколько состояний
 *       --- Подача заявки от клиента (это еще не заказ)
 *       --- Прийнятия заявка владельцем на обработку
 *       --- Заявка еще в состоянии обработки (выполняются проверки...либо формируется новый заказ, либо отклоняется заявка, либо оповещаются причины задержки выполнения заявки)
 *       --- Создается новый (НЕподтвержденый) заказ
 *           ---- заказ будет успешно подтвержден в случае когда клиент предоставит квитанцию об оплате (в какие-то оговоренные сроки с владельцем)
 *       --- Заказ подтвержден (на клиентский профайл/почту/телефон возвращается ID-заявки, счет на оплату и последний срок выполненя...)
 *           ---- в случае бронирования заказ будет предварительно подтвержден...такая себе отсрочка на какое-то время раздумывания
 *                ----- бронирование НЕдает права оставлять отзыва и выствлять рейтинг покупателю/продавцу об заказе (заказ просто безследно сбрасывается...)
 *           ---- отличие между оплатой и бронированием (за бронирование клиенту ничего НЕбудет, за заказ покупатель/продавец получает отзыв и тем самым набивает себе рейтинг...)
 *                ----- могут быть нечестные клиенты и нечестные продавцы (за каждым заказом и покупатель и продавец ДОЛЖЕН отставить отзыв друг о друге - это повлияет на их рейтинг)
 *           ---- рейтинг показывает уровень доверия к клиенту И владельцу объявления + предотвращает/защищает пользователей от НЕчестных сделок
 *       --- Заказ успешно выполнен (закрыта - когда все хорошо)
 *       --- Заказ отклонен (закрыт - в случае по вине клиента)
 * 2. Предварительная проверка состояния (в целях безопасности):
 *    -- передавать предварительное состояние в котором находится арендуемая товар-услуга
 *       --- чтобы система могла проследить и проверить правильность для выполнения заказа (на случай если состояние заказа будет дублироваться...)
 *    -- передавать рейтинг клиента (всегда - на случай чтобы владелец мог прийнять правильное решение от нечестных клиентов...)
 *
 * FIXME: Закрываем библиотечные методы ('add','get') для внешнего доступа через механизм делигирования..
 */
public class OfferPricesService extends ConcurrentLinkedQueue<Price> { //public abstract class OfferPricesService extends ConcurrentLinkedQueue<Price> {

    private static volatile Boolean initDate;
    private static String formatter = "d.MM.yyyy",
            monthOfPrices = "monthOfPrices",
            monthOfRents = "rents";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);
    private Long weekdayPrice,weekendPrice;
    private Long[][] weekdays,weekends;
    private ArrayList<Long> listWeekdays,listWeekends;
    private Rents rents;

    /**
     * #3. One cost (per day); Two dates (start/stop):
     *     -------------------------------------------
     *     - Only during this period (initial - final date) established a special tax;
     *       -- it can set a special price of only one day (start/end dates are the same)
     *       -- possible through the default constructor (without parameters)
     *
     * #4. One cost (per day); Undated (empty array):
     *     ------------------------------------------
     *     - During the default period (three full months) established a special tax;
     *       -- default period (three full months) is determined based on the current date
     *       -- possible through the default constructor (without parameters)
     */
    protected OfferPricesService(){
        initDate = null;
        listWeekdays = new ArrayList<Long>();
        listWeekends = new ArrayList<Long>();
    }

    /**
     * #1. One cost (per day); Two dates (start/stop):
     *     -------------------------------------------
     *     - During the default period (three full months) is established taking into account the cost of weekdays and weekends;
     *       -- default period (three full months) is determined based on the specified dates (start/end)
     *       -- through constructor with parameters
     *     - During this period (start-end dates) established a special tax;
     *       -- possible through the default constructor (without parameters)
     *
     * #2. Two costs (weekdays/weekend); Undated (empty array):
     *     ----------------------------------------------------
     *     - During the default period (three full months) is established taking into account the cost of weekdays and weekends;
     *       -- default period (three full months) is determined based on the current date
     *       -- through constructor with parameters
     */
    protected OfferPricesService(Long weekdayPrice, Long weekendPrice){
        if (weekdayPrice!=null
                && weekendPrice!=null){
            this.weekdayPrice = weekdayPrice;
            this.weekendPrice = weekendPrice;
        }
        initDate = null;
        listWeekdays = new ArrayList<Long>();
        listWeekends = new ArrayList<Long>();
//        gson = new Gson();
    }

    /**
     * Restore from Json to Object-Month:
     * ----------------------------------
     * - It restores the state of all prices (previously created) for the entire period;
     */
    protected OfferPricesService(String jsonRestore){
        JsonParser parser = new JsonParser();
        JsonObject objJsonObject = (JsonObject) parser.parse(jsonRestore);
        Gson gson = new Gson();
        Map<String, PriceOfRentsRestore> restores = gson.fromJson(objJsonObject, new TypeToken<Map<String, PriceOfRentsRestore>>(){}.getType());
        PriceOfRentsRestore restore = restores.get(monthOfPrices);
        PriceOfRent weekdays = restore.getWeekday(),
                weekends = restore.getWeekend();
        PriceOfRent[] specialdays = restore.getSpecialdays();

        weekdayPrice = weekdays.getPrice();
        weekendPrice = weekends.getPrice();
        initDate = null;
        listWeekdays = new ArrayList<Long>();
        listWeekends = new ArrayList<Long>();
        for (PriceOfRent specialday : specialdays) restorePrices(specialday.getPrice(), ConvertUtil.toDate(specialday.getDays()));
    }

    protected OfferPricesService(final String jsonPriceRestore, final String jsonRentsRestore){
        JsonParser parser = new JsonParser();
        JsonObject objJsonObject = (JsonObject) parser.parse(jsonPriceRestore);
        Gson gson = new Gson();
        Map<String, PriceOfRentsRestore> restores = gson.fromJson(objJsonObject, new TypeToken<Map<String, PriceOfRentsRestore>>(){}.getType());
        PriceOfRentsRestore restore = restores.get(monthOfPrices);
        PriceOfRent weekdays = restore.getWeekday(),
                weekends = restore.getWeekend();
        PriceOfRent[] specialdays = restore.getSpecialdays();

        weekdayPrice = weekdays.getPrice();
        weekendPrice = weekends.getPrice();
        initDate = null;
        listWeekdays = new ArrayList<Long>();
        listWeekends = new ArrayList<Long>();
        for (PriceOfRent specialday : specialdays) restorePrices(specialday.getPrice(), ConvertUtil.toDate(specialday.getDays()));

        JsonParser parser2 = new JsonParser();
        JsonObject objJsonObject2 = (JsonObject) parser2.parse(jsonRentsRestore);
        Gson gson2 = new Gson();
        Map<String, RentsRestore> restores2 = gson2.fromJson(objJsonObject2, new TypeToken<Map<String, RentsRestore>>(){}.getType());
        RentsRestore restore2 = restores2.get(monthOfRents);
        rents = Rents.getInstance(restore2.getAvailables(), restore2.getRented(), restore2.getExpired()); //TODO: #1
    }

    /**
     * Restore from (MonthOfPricesRestore) to Object-Month:
     * ----------------------------------------------------
     * - It restores the state of all prices (previously created) for the entire period;
     */
    protected OfferPricesService(PriceOfRentsRestore restore){
        PriceOfRent weekdays = restore.getWeekday(),
                weekends = restore.getWeekend();
        PriceOfRent[] specialdays = restore.getSpecialdays();

        if (weekdays.getPrice()!=null
                && weekends.getPrice()!=null){
            weekdayPrice = weekdays.getPrice();
            weekendPrice = weekends.getPrice();
        }
        initDate = null;
        listWeekdays = new ArrayList<Long>();
        listWeekends = new ArrayList<Long>();
        if (specialdays!=null)
            for (PriceOfRent specialday : specialdays) restorePrices(specialday.getPrice(), ConvertUtil.toDate(specialday.getDays()));
        else
            restorePrices(0l, new Long[]{});
    }

    protected OfferPricesService(final PriceOfRentsRestore priceRestore, final RentsRestore rentsRestore){
        PriceOfRent weekdays = priceRestore.getWeekday(),
                weekends = priceRestore.getWeekend();
        PriceOfRent[] specialdays = priceRestore.getSpecialdays();

        if (weekdays.getPrice()!=null
                && weekends.getPrice()!=null){
            weekdayPrice = weekdays.getPrice();
            weekendPrice = weekends.getPrice();
        }
        initDate = null;
        listWeekdays = new ArrayList<Long>();
        listWeekends = new ArrayList<Long>();
        if (specialdays!=null)
            for (PriceOfRent specialday : specialdays) restorePrices(specialday.getPrice(), ConvertUtil.toDate(specialday.getDays()));
        else
            restorePrices(0l, new Long[]{});
        rents = Rents.getInstance(rentsRestore.getAvailables(), rentsRestore.getRented(), rentsRestore.getExpired()); //TODO: #2
    }

    /**
     * #1. Setting costs of used constructor with parameters;
     *     -- (optional)
     * #2. Initialization-days period, used a method addPrices(), according to the previously specified value;
     *     -- it can be used with the default constructor (without parameters)
     * FIXME: it is problem when add several specials (if only NULL init month)
     * #3. Условие:
     *     -- При добавлении прайса состояние аренды тоже должно обновляться (добавляться);
     *        --- нужно учитывать что некоторые дни попрайсу могут быть ИЗМЕНЕНЫ А это значит что перед изменением нужно предвариельно проверять состояние аренды на этот день
     *            (разрешить зменять только для повторяющих-дней: выходные-будни...)
     */
    public void addPrices(Long price, Long[] days) {
        Price newPrice;
        switch (days.length) {
            case 0:
                if (weekdayPrice==null && weekendPrice==null) weekdayPrice = weekendPrice = price; //TODO:
                initDate(java.util.Calendar.getInstance().getTimeInMillis());
                break;
            case 1:
                initDate(days[0]);
                synchronized (OfferPricesService.class){
                    newPrice = new Price(price);
                    for (Price curPrice : this)
                        if (curPrice.remove(days[0])) newPrice.add(days[0]);
                    if (weekdayPrice==null && weekendPrice==null) removeAll(this); //TODO:
                    add(newPrice);
                }
                break;
            case 2:
                initDate(days);
                synchronized (OfferPricesService.class){
                    newPrice = new Price(price);
                    java.util.Calendar lastDate = new GregorianCalendar(Integer.valueOf(ConvertUtil.toDate(days[1]).split("\\.")[2]), (Integer.valueOf(ConvertUtil.toDate(days[1]).split("\\.")[1])-1), Integer.valueOf(ConvertUtil.toDate(days[1]).split("\\.")[0]));
                    for (java.util.Calendar currDate = new GregorianCalendar(Integer.valueOf(ConvertUtil.toDate(days[0]).split("\\.")[2]), (Integer.valueOf(ConvertUtil.toDate(days[0]).split("\\.")[1])-1), Integer.valueOf(ConvertUtil.toDate(days[0]).split("\\.")[0])); currDate.getTimeInMillis()<=lastDate.getTimeInMillis(); currDate.add(java.util.Calendar.DATE, 1)) {
                        for (Price currPrice : this)
                            if (currPrice.remove(currDate.getTimeInMillis())) newPrice.add(currDate.getTimeInMillis());
                    }
                    if (weekdayPrice==null && weekendPrice==null) removeAll(this); //TODO:
                    add(newPrice);
                }
                break;
            default:
                break;
        }

        // FIXME: to constructor...START
        long[] availablesDays = null;
        for (Price prices : this) for (long p : prices) availablesDays = ArrayUtils.add(availablesDays, p);
        rents = Rents.getInstance(availablesDays); //TODO: #3
        // FIXME: to constructor...FINISH
    }

    public void restorePrices(Long price, Long[] days) {
        Price newPrice;
        switch (days.length) {
            case 0:
                if (weekdayPrice==null && weekendPrice==null) weekdayPrice = weekendPrice = price; //TODO:
                initDate(java.util.Calendar.getInstance().getTimeInMillis());
                break;
            case 1:
                initDate(days[0]);
                synchronized (OfferPricesService.class){
                    newPrice = new Price(price);
                    for (Price curPrice : this)
                        if (curPrice.remove(days[0])) newPrice.add(days[0]);
                    if (weekdayPrice==null && weekendPrice==null) removeAll(this); //TODO:
                    add(newPrice);
                }
                break;
            case 2:
                initDate(days);
                synchronized (OfferPricesService.class){
                    newPrice = new Price(price);
                    java.util.Calendar lastDate = new GregorianCalendar(Integer.valueOf(ConvertUtil.toDate(days[1]).split("\\.")[2]), (Integer.valueOf(ConvertUtil.toDate(days[1]).split("\\.")[1])-1), Integer.valueOf(ConvertUtil.toDate(days[1]).split("\\.")[0]));
                    for (java.util.Calendar currDate = new GregorianCalendar(Integer.valueOf(ConvertUtil.toDate(days[0]).split("\\.")[2]), (Integer.valueOf(ConvertUtil.toDate(days[0]).split("\\.")[1])-1), Integer.valueOf(ConvertUtil.toDate(days[0]).split("\\.")[0])); currDate.getTimeInMillis()<=lastDate.getTimeInMillis(); currDate.add(java.util.Calendar.DATE, 1)) {
                        for (Price currPrice : this)
                            if (currPrice.remove(currDate.getTimeInMillis())) newPrice.add(currDate.getTimeInMillis());
                    }
                    if (weekdayPrice==null && weekendPrice==null) removeAll(this); //TODO:
                    add(newPrice);
                }
                break;
            default:
                break;
        }
    }

    /**
     * #1. Условия:
     *     -- все дни которые до текущего числа автоматически являются НЕактивными (переводить в НЕактивное состояние)
     *     -- делать автоматическую проверку всех НЕактивных дней (переводить в НЕактивное состояние)
     * #2. Порядок:
     *     -- (a) при иннициализации, изначально все дни попадают в список - доступных
     *     -- (b) дни которые клиент арендует попадает в список - арендованых
     *     -- (c) после удаления арендованого дня он снова может вернуться в список - доступных
     *     -- (d) все просроченные дни попадают в список - просроченых (и больше из списка-просроченых они уже НЕмогут вернуться в другие списки-доступных-арендованых)
     */
    public void addRent(Long[] days, String userId) throws ConcurrentModificationException {
        RentUser user = new RentUser(); //TODO: ...
        user.setId(userId); //TODO: ...
        user.setFullName("Петренко Юрий Владимирович"); //TODO: ...
        user.setImgId("57e440464c8eda79f765532d"); //TODO: ...
        user.setRating(10);
        Order order = new Order(); //TODO: ...
        Date date = new Date(); //TODO: ...

        if( getRents().getAvailables().isEmpty() ){
            for (Price prices : this) {
                for (Long day : prices) getRents().getAvailables().add(new Rent(day, user, true, true, null, (date.getTime()), date.getTime(), RentStatus.RENTED, OrderStatus.SUCCESSFULLY_ORDER, 0, null));
            }
        } else {
            for (Long day : days) {
                Rent findAvailables = new Rent(day, user, true, true, null, (date.getTime()), date.getTime(), RentStatus.RENTED, OrderStatus.SUCCESSFULLY_ORDER, 0, null);
                if( getRents().getAvailables().contains(findAvailables) ){
                    Rent objAvailables = getRents().getAvailables().get(getRents().getAvailables().indexOf(findAvailables));
                    if( getRents().getAvailables().remove(objAvailables) ) getRents().getRented().add(findAvailables);
                }
            }
        }
    }

    public void addRent(Long[] days, RentUser user) throws ConcurrentModificationException {
        Order order = new Order(); //TODO: ...
        Date date = new Date(); //TODO: ...

        if( getRents().getAvailables().isEmpty() ){
            for (Price prices : this) {
                for (Long day : prices) getRents().getAvailables().add(new Rent(day, user, true, true, null, (date.getTime()), date.getTime(), RentStatus.RENTED, OrderStatus.SUCCESSFULLY_ORDER, 0, null));
            }
        } else {
            for (Long day : days) {
                Rent findAvailables = new Rent(day, user, true, true, null, (date.getTime()), date.getTime(), RentStatus.RENTED, OrderStatus.SUCCESSFULLY_ORDER, 0, null);
                if( getRents().getAvailables().contains(findAvailables) ){
                    Rent objAvailables = getRents().getAvailables().get(getRents().getAvailables().indexOf(findAvailables));
                    if( getRents().getAvailables().remove(objAvailables) ) getRents().getRented().add(findAvailables);
                }
            }
        }
    }

    /**
     * #1. Условие:
     *     -- запрещается удалять день который был арендован
     *     -- запрещается удалять список дней если хотябы один из дней в этом списке арендован
     */
    public Integer delPrices(Long[] days) {
        Integer del;
        if (isRent(days)) {
            synchronized (OfferPricesService.class){
                del = 0;
                switch (days.length) {
                    case 1:
                        for (Price curPrice : this)
                            if (curPrice.remove(days[0])) del++;
                        break;
                    case 2:
                        java.util.Calendar lastDate = new GregorianCalendar(Integer.valueOf(ConvertUtil.toDate(days[1]).split("\\.")[2]), (Integer.valueOf(ConvertUtil.toDate(days[1]).split("\\.")[1])-1), Integer.valueOf(ConvertUtil.toDate(days[1]).split("\\.")[0])); //java.util.Calendar lastDate = new GregorianCalendar(Integer.valueOf(convertDate(days[1]).split("\\.")[2]), (Integer.valueOf(convertDate(days[1]).split("\\.")[1])-1), Integer.valueOf(convertDate(days[1]).split("\\.")[0]));
                        for (java.util.Calendar currDate = new GregorianCalendar(Integer.valueOf(ConvertUtil.toDate(days[0]).split("\\.")[2]), (Integer.valueOf(ConvertUtil.toDate(days[0]).split("\\.")[1])-1), Integer.valueOf(ConvertUtil.toDate(days[0]).split("\\.")[0])); currDate.getTimeInMillis()<=lastDate.getTimeInMillis(); currDate.add(java.util.Calendar.DATE, 1)){ //for (java.util.Calendar currDate = new GregorianCalendar(Integer.valueOf(convertDate(days[0]).split("\\.")[2]), (Integer.valueOf(convertDate(days[0]).split("\\.")[1])-1), Integer.valueOf(convertDate(days[0]).split("\\.")[0])); currDate.getTimeInMillis()<=lastDate.getTimeInMillis(); currDate.add(java.util.Calendar.DATE, 1)){
                            for (Price currPrice : this)
                                if (currPrice.remove(currDate.getTimeInMillis())) del++;
                        }
                        break;
                    default:
                        break;
                }
            }
        } else {
            return 0;
        }
        return del;
    }

    /**
     * #1. Условие:
     *     -- удаление арендованного дня (делает обратное) переводит в состояние доступного для аренды
     *     -- все дни которые до текущего числа автоматически являются НЕактивными (переводить в НЕактивное состояние)
     *     -- делать автоматическую проверку всех НЕактивных дней (переводить в НЕактивное состояние)
     * #2. Порядок:
     *     -- (a) при иннициализации, изначально все дни попадают в список - доступных
     *     -- (b) дни которые клиент арендует попадает в список - арендованых
     *     -- (c) после удаления арендованого дня он снова может вернуться в список - доступных
     *     -- (d) все просроченные дни попадают в список - просроченых (и больше из списка-просроченых они уже НЕмогут вернуться в другие списки-доступных-арендованых)
     */
    public Integer delRent(Long[] days) {
        //FIXME: предварительно создавать полный список удаляемых элементов И удалять этот список (НЕ по одному элементу...)
        int del = 0;
        if( !getRents().getRented().isEmpty() ){
            for (Long day : days) {
                Rent findRented = new Rent(day, null, true, true, null, null, null, RentStatus.RENTED, OrderStatus.NONE, 1, null);
                if( getRents().getRented().contains(findRented) ){
                    Rent objRented = getRents().getRented().get(getRents().getRented().indexOf(findRented));
                    if( getRents().getRented().remove(objRented) ){
                        getRents().getAvailables().add(objRented);
                        del++;
                    }
                }
            }
        }
        return del;
    }

    public Boolean isPrice(Long day) {
        for (Price prices : this)
            if (prices.contains(day)) return true;
        return false;
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
    public Boolean isRent(Long day) {
        return getRents().getRented().contains(new Rent(day, null, true, true, null, null, null, RentStatus.AVAILABLE, OrderStatus.NONE, 1, null));
    }

    public Boolean isRent(Long[] days) {
        for (Long day : days) if(!isRent(day)) return false;
        return true;
    }

    public Long getPrice(Long day){
        Long price = 0l;
        for (Price prices : this)
            if (prices.contains(day)) price += prices.get();
        return price;
    }

    public Long getPrice(Long[] days){
        Long price = 0l;
        for (Price prices : this) {
            for (Long day : days)
                if (prices.contains(day)) price += prices.get();
        }
        return price;
    }

    public Rents getRents(){
        return rents;
    }

    //    @Override
//    public String toString() throws NoSuchElementException {
//        StringBuilder data = new StringBuilder();
//        for (Price prices : this) {
//            for (Long price : prices) data.append("(" + prices.get() + ")" + convertDate(price) + " ");
//            if (!prices.isEmpty()) data.append("\n");
//        }
//        return data.toString();
//    }
    @Override
    public String toString() throws NoSuchElementException {
        StringBuilder data = new StringBuilder();
        List<Long> isRents = new LinkedList<Long>(),
                isExpireds = new LinkedList<Long>();
        List<Rent> e = getRents().getExpired(),
                a = getRents().getAvailables(),
                r = getRents().getRented();
        if (e!=null) for (Rent experied : e) isExpireds.add(experied.getDay());
        if (r!=null) for (Rent rented : r) isRents.add(rented.getDay());
        for (Price prices : this) {
            for (Long price : prices) data.append(prices.get() + (isExpireds.contains(price)?"|EXPIRED":isRents.contains(price)?"|RENTED":"") +"(" + ConvertUtil.toDate(price) + ") "); //for (Long price : prices) data.append(prices.get() + (isExpireds.contains(price)?"|EXPIRED":isRents.contains(price)?"|RENTED":"") +"(" + convertDate(price) + ") ");
            if (!prices.isEmpty()) data.append("\n");
        }
        return data.toString();
    }

    public String toRent() {
        StringBuilder data = new StringBuilder();
        try {
            List<Rent> e = getRents().getExpired(),
                    a = getRents().getAvailables(),
                    r = getRents().getRented();
            if(a!=null){
                Iterator<Rent> availables = a.iterator();
                while (availables.hasNext()) data.append("AVAILABLE" + availables.next() + " ");
                data.append("\n");
            }
            if(r!=null){
                Iterator<Rent> rented = r.iterator();
                while (rented.hasNext()) data.append("RENTED" + rented.next() + " ");
                data.append("\n");
            }
            if(e!=null){
                Iterator<Rent> expired = e.iterator();
                while (expired.hasNext()) data.append("EXPIRED" + expired.next() + " ");
                data.append("\n");
            }
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (ConcurrentModificationException e){
            e.printStackTrace();
        }
        return data.toString();
    }

    public String jsonRent() {
        StringBuilder data = new StringBuilder();
        int available = 0,
                rent = 0,
                expired = 0;
        data.append("{\n  \"" + monthOfRents + "\": {\n");
        data.append("    \"availables\": [");
//        for (Rent availableDays : getRents().getAvailables()) {
//            if (0<available) data.append(",{");
//            else data.append("\n      {");
//            data.append("\n        " + availableDays);
//            data.append("\n      }");
//            ++available;
//        }
///////////////////////////////////////////////////////////////
        for (Rent availableDays : getRents().getAvailables()) {
            if (0<available) data.append(",{");
            else data.append("\n      {");
            data.append("\n        " + availableDays);
            data.append("\n      }");
            ++available;
        }
///////////////////////////////////////////////////////////////
        data.append("\n    ]\n");
        data.append("   ,\"rented\": [");
        for (Rent rentedDays : getRents().getRented()) { //FIXME: предоплата вносится на срок один-день, либо она есть либо ее нет (и всегда указывается срок до которого она действительна - начиная с текущего момента предоплаты и даже если срока остается менее одного дня = но при условии что допустимый срок предоплаты ЕСТЬ-остается..)
            if (0<rent) data.append(",{");
            else data.append("\n      {");
            data.append("\n        " + rentedDays);
            data.append("\n      }");
            ++rent;
        }
        data.append("\n    ]\n");
        data.append("    ,\"expired\": [");
        for (Rent expiredDays : getRents().getExpired()) {
            if (0<expired) data.append(",{");
            else data.append("\n      {");
            data.append("\n        " + expiredDays);
            data.append("\n      }");
            ++expired;
        }
        data.append("\n    ]\n  }\n}");
        return data.toString();
    }

//    public String toJson() throws NoSuchElementException {
////        return gson.toJson(this);
//        int scheme = 0;
//        StringBuilder data = new StringBuilder();
//        data.append("{\n  \"" + monthOfPrices + "\": {\n"); //data.append("{\n");
//        if (weekdayPrice!=null && weekendPrice!=null){
//            for (Price prices : this) {
//                if (scheme==0){
//                    data.append("    \"weekday\": {\n");
//                    data.append("      \"price\": " + prices.get() + "\n");
//                    data.append("      ,\"days\": [\"" + ConvertUtil.toDate(prices.element()) + "\"");
//                    if (1 < prices.size()) {
//                        Long lastPrice = 0l;
//                        for (Long price : prices) lastPrice = price;
//                        data.append(",\"" + ConvertUtil.toDate(lastPrice) + "\"]\n");
//                    } else {
//                        data.append("]\n");
//                    }
//                    data.append("    }\n");
//                }
//                if (scheme==1){
//                    data.append("    ,\"weekend\": {\n");
//                    data.append("      \"price\": " + prices.get() + "\n");
//                    data.append("      ,\"days\": [\"" + ConvertUtil.toDate(prices.element()) + "\"");
//                    if (1 < prices.size()) {
//                        Long lastPrice = 0l;
//                        for (Long price : prices) lastPrice = price;
//                        data.append(",\"" + ConvertUtil.toDate(lastPrice) + "\"]\n");
//                    } else {
//                        data.append("]\n");
//                    }
//                    data.append("    }\n");
//                }
//                if (scheme==2) data.append("    ,\"specialdays\": [\n");
//                if (1<scheme){
//                    if (scheme==2) data.append("      {\n");
//                    if (2<scheme) data.append("      ,{\n");
//                    data.append("        \"price\": " + prices.get() + "\n");
//                    data.append("        ,\"days\": [\"" + ConvertUtil.toDate(prices.element()) + "\"");
//                    if (1 < prices.size()) {
//                        Long lastPrice = 0l;
//                        for (Long price : prices) lastPrice = price;
//                        data.append(",\"" + ConvertUtil.toDate(lastPrice) + "\"]\n");
//                    } else {
//                        data.append("]\n");
//                    }
//                    data.append("      }\n");
//                }
//                if (scheme==(this.size()-1) && 1<scheme) data.append("    ]\n");
//                ++scheme;
//            }
//        } else {
//            if (!this.isEmpty()){ //TODO
//                data.append("    \"specialdays\":\n  [\n");
//                for (Price prices : this) {
//                    if (0 < scheme) data.append("      ,{\n");
//                    else data.append("      {\n");
//                    data.append("      \"price\": " + prices.get() + "\n");
//                    data.append("        ,\"days\": [\"" + ConvertUtil.toDate(prices.element()) + "\"");
//                    if (1 < prices.size()) {
//                        Long lastPrice = 0l;
//                        for (Long price : prices) lastPrice = price;
//                        data.append(",\"" + ConvertUtil.toDate(lastPrice) + "\"  ]\n");
//                    } else {
//                        data.append("  ]\n");
//                    }
//                    data.append("      }\n");
//                    ++scheme;
//                }
//                data.append("    ]\n");
//            }
//        }
//        data.append("  }\n}"); //data.append("}");
//        return data.toString();
//    }

    public String toJson() throws NoSuchElementException {
        int scheme = 0;
        StringBuilder data = new StringBuilder();
        data.append("{\n");
        if (weekdayPrice!=null && weekendPrice!=null){
            for (Price prices : this) {
                if (scheme==0){
                    data.append("    \"weekday\": \n");
                    data.append("      \"price\": " + prices.get() + "\n");
//                    data.append("      ,\"days\": [\"" + ConvertUtil.toDate(prices.element()) + "\"");
//                    if (1 < prices.size()) {
//                        Long lastPrice = 0l;
//                        for (Long price : prices) lastPrice = price;
//                        data.append(",\"" + ConvertUtil.toDate(lastPrice) + "\"]\n");
//                    } else {
//                        data.append("]\n");
//                    }
//                    data.append("    }\n");
                }
                if (scheme==1){
                    data.append("    ,\"weekend\": \n");
                    data.append("      \"price\": " + prices.get() + "\n");
//                    data.append("      ,\"days\": [\"" + ConvertUtil.toDate(prices.element()) + "\"");
//                    if (1 < prices.size()) {
//                        Long lastPrice = 0l;
//                        for (Long price : prices) lastPrice = price;
//                        data.append(",\"" + ConvertUtil.toDate(lastPrice) + "\"]\n");
//                    } else {
//                        data.append("]\n");
//                    }
//                    data.append("    }\n");
                }
                if (scheme==2) data.append("    ,\"specialdays\": [\n");
                if (1<scheme){
                    if (scheme==2) data.append("      {\n");
                    if (2<scheme) data.append("      ,{\n");
                    data.append("        \"price\": " + prices.get() + "\n");
                    data.append("        ,\"days\": [\"" + ConvertUtil.toDate(prices.element()) + "\"");
                    if (1 < prices.size()) {
                        Long lastPrice = 0l;
                        for (Long price : prices) lastPrice = price;
                        data.append(",\"" + ConvertUtil.toDate(lastPrice) + "\"]\n");
                    } else {
                        data.append("]\n");
                    }
                    data.append("      }\n");
                }
                if (scheme==(this.size()-1) && 1<scheme) data.append("    ]\n");
                ++scheme;
            }
        } else {
            if (!this.isEmpty()){ //TODO
                data.append("    \"specialdays\":\n  [\n");
                for (Price prices : this) {
                    if (0 < scheme) data.append("      ,{\n");
                    else data.append("      {\n");
                    data.append("      \"price\": " + prices.get() + "\n");
                    data.append("        ,\"days\": [\"" + ConvertUtil.toDate(prices.element()) + "\"");
                    if (1 < prices.size()) {
                        Long lastPrice = 0l;
                        for (Long price : prices) lastPrice = price;
                        data.append(",\"" + ConvertUtil.toDate(lastPrice) + "\"  ]\n");
                    } else {
                        data.append("  ]\n");
                    }
                    data.append("      }\n");
                    ++scheme;
                }
                data.append("    ]\n");
            }
        }
        data.append("\n}");
        return data.toString();
    }

    public String toJsonFull() throws NoSuchElementException {
        int scheme = 0;
        int available = 0,
                rent = 0,
                expired = 0;
        StringBuilder data = new StringBuilder();

        /**
         * monthOfPrices
         */
        data.append("{\n  \"" + monthOfPrices + "\": {\n");
        if (weekdayPrice!=null && weekendPrice!=null){
            for (Price prices : this) {
                if (scheme==0){
                    data.append("    \"weekday\": {\n");
                    data.append("      \"price\": " + prices.get() + "\n");
                    data.append("      ,\"days\": [\"" + ConvertUtil.toDate(prices.element()) + "\"");
                    if (1 < prices.size()) {
                        Long lastPrice = 0l;
                        for (Long price : prices) lastPrice = price;
                        data.append(",\"" + ConvertUtil.toDate(lastPrice) + "\"]\n");
                    } else {
                        data.append("]\n");
                    }
                    data.append("    }\n");
                }
                if (scheme==1){
                    data.append("    ,\"weekend\": {\n");
                    data.append("      \"price\": " + prices.get() + "\n");
                    data.append("      ,\"days\": [\"" + ConvertUtil.toDate(prices.element()) + "\"");
                    if (1 < prices.size()) {
                        Long lastPrice = 0l;
                        for (Long price : prices) lastPrice = price;
                        data.append(",\"" + ConvertUtil.toDate(lastPrice) + "\"]\n");
                    } else {
                        data.append("]\n");
                    }
                    data.append("    }\n");
                }
                if (scheme==2) data.append("    ,\"specialdays\": [\n");
                if (1<scheme){
                    if (scheme==2) data.append("      {\n");
                    if (2<scheme) data.append("      ,{\n");
                    data.append("        \"price\": " + prices.get() + "\n");
                    data.append("        ,\"days\": [\"" + ConvertUtil.toDate(prices.element()) + "\"");
                    if (1 < prices.size()) {
                        Long lastPrice = 0l;
                        for (Long price : prices) lastPrice = price;
                        data.append(",\"" + ConvertUtil.toDate(lastPrice) + "\"]\n");
                    } else {
                        data.append("]\n");
                    }
                    data.append("      }\n");
                }
                if (scheme==(this.size()-1) && 1<scheme) data.append("    ]\n");
                ++scheme;
            }
        } else {
            if (!this.isEmpty()){ //TODO
                data.append("    \"specialdays\":\n  [\n");
                for (Price prices : this) {
                    if (0 < scheme) data.append("      ,{\n");
                    else data.append("      {\n");
                    data.append("      \"price\": " + prices.get() + "\n");
                    data.append("        ,\"days\": [\"" + ConvertUtil.toDate(prices.element()) + "\"");
                    if (1 < prices.size()) {
                        Long lastPrice = 0l;
                        for (Long price : prices) lastPrice = price;
                        data.append(",\"" + ConvertUtil.toDate(lastPrice) + "\"  ]\n");
                    } else {
                        data.append("  ]\n");
                    }
                    data.append("      }\n");
                    ++scheme;
                }
                data.append("    ]\n");
            }
        }
        data.append("  }\n");

        /**
         * monthOfRents
         */
        data.append("  ,\"" + monthOfRents + "\": {\n");
        data.append("    \"availables\": [");
        for (Rent availableDays : getRents().getAvailables()) {
            if (0<available) data.append(",{");
            else data.append("\n      {");
            data.append("\n        " + availableDays);
            data.append("\n      }");
            ++available;
        }
        data.append("\n    ]\n");
        data.append("   ,\"rented\": [");
        for (Rent rentedDays : getRents().getRented()) { //FIXME: предоплата вносится на срок один-день, либо она есть либо ее нет (и всегда указывается срок до которого она действительна - начиная с текущего момента предоплаты и даже если срока остается менее одного дня = но при условии что допустимый срок предоплаты ЕСТЬ-остается..)
            if (0<rent) data.append(",{");
            else data.append("\n      {");
            data.append("\n        " + rentedDays);
            data.append("\n      }");
            ++rent;
        }
        data.append("\n    ]\n");
        data.append("    ,\"expired\": [");
        for (Rent expiredDays : getRents().getExpired()) {
            if (0<expired) data.append(",{");
            else data.append("\n      {");
            data.append("\n        " + expiredDays);
            data.append("\n      }");
            ++expired;
        }
        data.append("\n    ]\n  }\n}");

        return data.toString();
    }

    public PriceOfRentsRestore toRestore(){
        JsonParser parser = new JsonParser();
        JsonObject objJsonObject = (JsonObject) parser.parse(toJson());
        Gson gson = new Gson();
        Map<String, PriceOfRentsRestore> restores = gson.fromJson(objJsonObject, new TypeToken<Map<String, PriceOfRentsRestore>>(){}.getType());
        PriceOfRentsRestore restore = restores.get(monthOfPrices);
//        System.err.println(restore);
        return restore;
    }

    public RentsRestore toRestore2(){
        JsonParser parser2 = new JsonParser();
        JsonObject objJsonObject = (JsonObject) parser2.parse(jsonRent());
        Gson gson2 = new Gson();
        Map<String, RentsRestore> restores2 = gson2.fromJson(objJsonObject, new TypeToken<Map<String, RentsRestore>>(){}.getType());
        RentsRestore restore2 = restores2.get(monthOfRents);
//        System.err.println(restore2);
        return restore2;
    }

    private void init(){
        synchronized (OfferPricesService.class){
            if (initDate==null){
                weekdays = new Long[1][listWeekdays.size()];
                weekends = new Long[1][listWeekends.size()];
                weekdays[0] = listWeekdays.toArray(new Long[listWeekdays.size()]);
                weekends[0] = listWeekends.toArray(new Long[listWeekends.size()]);

                Price weekdaysPrice = new Price(weekdayPrice);
                for (Long lWeekday:weekdays[0]) weekdaysPrice.add(lWeekday);
                add(weekdaysPrice);
                Price weekendsPrice = new Price(weekendPrice);
                for (Long lWeekend:weekends[0]) weekendsPrice.add(lWeekend);
                add(weekendsPrice);
            }
        }
    }

    private void initDate(Integer month, Integer year){
        java.util.Calendar cal = new GregorianCalendar(year, (month-1), 1);
        do {
            String strMonth = month<10 ? "0" + month : String.valueOf(month),
                    strDay = cal.get(java.util.Calendar.DAY_OF_MONTH)<10 ? "0"+cal.get(java.util.Calendar.DAY_OF_MONTH) : String.valueOf(cal.get(java.util.Calendar.DAY_OF_MONTH));
            int day = cal.get(java.util.Calendar.DAY_OF_WEEK);
            if (day != java.util.Calendar.SATURDAY && day != java.util.Calendar.SUNDAY)
                listWeekdays.add(ConvertUtil.toDate(strDay + "." + strMonth + "." + year));
            else
                listWeekends.add(ConvertUtil.toDate(strDay + "." + strMonth + "." + year));
            cal.add(java.util.Calendar.DAY_OF_YEAR, 1);
        } while (cal.get(java.util.Calendar.MONTH) == (month-1));
    }

    private void initDate(Long day){
        synchronized (OfferPricesService.class) {
            if (initDate == null) {
                initDate(Integer.valueOf(ConvertUtil.toDate(day).split("\\.")[1]), Integer.valueOf(ConvertUtil.toDate(day).split("\\.")[2]));
                init();

                initDate = new Boolean(true);
            }
        }
    }

    private void initDate(Long[] days){
        synchronized (OfferPricesService.class) {
            if (initDate == null) {
                Arrays.sort(days);
                java.util.Calendar lastCal = new GregorianCalendar(Integer.valueOf(ConvertUtil.toDate(days[days.length - 1]).split("\\.")[2]), (Integer.valueOf(ConvertUtil.toDate(days[days.length - 1]).split("\\.")[1])-1), 1);
                for (java.util.Calendar currCal = new GregorianCalendar(Integer.valueOf(ConvertUtil.toDate(days[0]).split("\\.")[2]), (Integer.valueOf(ConvertUtil.toDate(days[0]).split("\\.")[1])-1), 1); currCal.getTimeInMillis()<=lastCal.getTimeInMillis(); currCal.add(java.util.Calendar.MONTH, 1))
                    initDate(Integer.valueOf(String.valueOf(simpleDateFormat.format(currCal.getTime())).split("\\.")[1]), Integer.valueOf(String.valueOf(simpleDateFormat.format(currCal.getTime())).split("\\.")[2]));
                init();

                initDate = new Boolean(true);
            }
        }
    }
}