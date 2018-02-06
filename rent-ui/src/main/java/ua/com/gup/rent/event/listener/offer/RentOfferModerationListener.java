package ua.com.gup.rent.event.listener.offer;

import org.springframework.stereotype.Component;
import ua.com.gup.notify.factory.NotificationFactory;
import ua.com.gup.notify.model.Notification;
import ua.com.gup.notify.model.NotificationType;
import ua.com.gup.rent.event.offer.RentOfferModeratedEvent;
import ua.com.gup.rent.model.mongo.rent.RentOffer;

@Component
public class RentOfferModerationListener extends RentOfferListener<RentOfferModeratedEvent> {

    @Override
    public void onApplicationEvent(RentOfferModeratedEvent event) {
        RentOffer offer = event.getSource();
        String title = "(no title)";
        String description = "(no description)";
        switch (offer.getStatus()) {
            case ACTIVE:
                title = "Объявление прошло модерацию";
                description = "Объявление \"" + offer.getTitle() + "\" успешно прошло модерацию. Теперь ваше объявление доступно в поиске и для заказов";
                break;
            case REJECTED:
                title = "Объявление не прошло модерацию";
                description = "Объявление \"" + offer.getTitle() + "\" не прошло модерацию. Внесите правки";
                break;
        }
        Notification notification = NotificationFactory.createNotification(NotificationType.OFFER, title, description, offer.getAuthorId());
        rentNotificationService.convertAndSend(notification);
    }
}
