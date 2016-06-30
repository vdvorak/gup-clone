package ua.com.itproekt.gup.util;

import org.springframework.util.CollectionUtils;
import ua.com.itproekt.gup.api.rest.dto.OfferInfo;
import ua.com.itproekt.gup.model.offer.Offer;
import java.util.ArrayList;
import java.util.List;

/**
* Created by jfractal on 10.06.2016.
*/
public final class ModelUtil {

    public static EntityPage<OfferInfo> toModel(EntityPage<Offer> entities){
        List<Offer> offers = entities.getEntities();
        List<OfferInfo> infos = new ArrayList<>();
        if(!CollectionUtils.isEmpty(offers)){
            for (Offer offer: offers) {
                infos.add(toModel(offer));
            }
        }
        return new EntityPage<>(entities.getEntities().size(), infos);
    }


    public static OfferInfo toModel(Offer offer){
        OfferInfo info = new OfferInfo();
        info.setTitle(offer.getTitle());
        info.setCurrency(offer.getCurrency());
        info.setImagesIds(offer.getImagesIds());
        info.setPrice(offer.getPrice());
        info.setProperties(offer.getProperties());
        info.setViews(offer.getViews());
        return info;
    }

}
