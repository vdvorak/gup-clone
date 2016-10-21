package ua.com.itproekt.gup.server.api.rest.order;


import ua.com.itproekt.gup.model.activityfeed.Event;
import ua.com.itproekt.gup.model.activityfeed.EventType;
import ua.com.itproekt.gup.model.order.Order;
import ua.com.itproekt.gup.model.order.OrderComment;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.order.OrderService;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Helper methods for OrderRestController class.
 *
 * @author Kobylyatskyy Alexander
 */
public class OrderRestHelper {


    static Event eventPreparatorForSeller(Profile profile, Order order, EventType eventType) {

        return new Event()
                .setTargetUId(order.getSellerId())
                .setType(eventType)
                .setContentStoreId(order.getOfferId())
                .setContentStoreTitle(order.getOfferTitle())
                .setContentId(order.getId())
                .setMakerId(order.getBuyerId())
                .setImgId(order.getOfferMainImageId())
                .setMakerName(profile.getUsername());
    }


    static Event eventPreparatorForBuyer(Profile profile, Order order, EventType eventType) {

        return new Event()
                .setTargetUId(order.getBuyerId())
                .setType(eventType)
                .setContentStoreId(order.getOfferId())
                .setContentStoreTitle(order.getOfferTitle())
                .setContentId(order.getId())
                .setMakerId(order.getSellerId())
                .setImgId(order.getOfferMainImageId())
                .setMakerName(profile.getUsername());
    }


    static void commentUpdaterAndEventSender(Profile profile, OrderService orderService, ActivityFeedService activityFeedService, String userId, Order order, Order oldOrder, EventType eventType) {

        OrderComment newComment = order.getOrderComments().get(0)
                .setDate(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli())
                .setUserId(userId);

        oldOrder.getOrderComments().add(newComment);
        orderService.findAndUpdate(oldOrder);

        activityFeedService.createEvent(eventPreparatorForSeller(profile, oldOrder, eventType));
    }
}
