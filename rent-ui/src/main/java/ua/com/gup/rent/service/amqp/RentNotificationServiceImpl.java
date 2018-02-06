package ua.com.gup.rent.service.amqp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ua.com.gup.notify.model.Notification;

@Service
public class RentNotificationServiceImpl implements RentNotificationService {

    private final static Logger logger = LoggerFactory.getLogger(RentNotificationServiceImpl.class);
    private final RabbitTemplate rabbitTemplate;

    public RentNotificationServiceImpl(@Qualifier("rabbitProducerTemplate") RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Async
    public void convertAndSend(Notification notification) {
        rabbitTemplate.convertAndSend(notification);
    }


}
