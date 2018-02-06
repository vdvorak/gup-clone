package ua.com.gup.rent.event.listener.offer.bid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import ua.com.gup.rent.event.offer.bid.RentOfferBidEvent;
import ua.com.gup.rent.service.amqp.RentNotificationService;

@Component
public abstract class RentOfferBidListener<T extends RentOfferBidEvent> implements ApplicationListener<T> {
    @Autowired
    protected RentNotificationService rentNotificationService;
}
