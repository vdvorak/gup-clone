package ua.com.gup.rent.event.listener.offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import ua.com.gup.common.service.DictionaryService;
import ua.com.gup.rent.event.offer.RentOfferEvent;
import ua.com.gup.rent.service.amqp.RentNotificationService;

public abstract class RentOfferListener<T extends RentOfferEvent> implements ApplicationListener<T> {


    @Autowired
    protected DictionaryService dictionaryService;
    @Autowired
    protected RentNotificationService rentNotificationService;

}
