package ua.com.gup.rent.event.listener.offer.bid;

import org.springframework.stereotype.Component;
import ua.com.gup.notify.factory.NotificationFactory;
import ua.com.gup.notify.model.Notification;
import ua.com.gup.notify.model.NotificationType;
import ua.com.gup.rent.event.offer.bid.RentOfferBidCreatedEvent;
import ua.com.gup.rent.model.mongo.rent.bid.RentOfferBid;
import ua.com.gup.rent.model.mongo.rent.bid.RentOfferBidStatus;


@Component
public class RentOfferBidCreationListener extends RentOfferBidListener<RentOfferBidCreatedEvent> {

    @Override
    public void onApplicationEvent(RentOfferBidCreatedEvent event) {
        RentOfferBid offerBid = event.getSource();
        if (RentOfferBidStatus.NEW.equals(offerBid.getStatus())) {
            String title = "Входящий запрос на аренду";
            String description = "У вас запросили аренду \"" + offerBid.getOfferBidInfo().getTitle() + "\"";
            Notification notification = NotificationFactory.createNotification(NotificationType.DEAL, title, description, offerBid.getOfferBidInfo().getAuthorId());
            rentNotificationService.convertAndSend(notification);
        }

    }
}
