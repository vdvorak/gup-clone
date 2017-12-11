package ua.com.gup.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.dto.offer.OfferPriceDTO;
import ua.com.gup.mongo.model.offer.Price;
import ua.com.gup.service.currency.CurrencyConverterService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import ua.com.gup.mongo.composition.domain.offer.OfferImage;
import ua.com.gup.mongo.composition.domain.offer.OfferImageSizeType;


@Component
public class OfferImageMapper {    

    public void toDTO(OfferImage image , Map<String, Map<OfferImageSizeType, String>> images) {        
        Map<OfferImageSizeType, String> tmp = new HashMap();
        for (OfferImageSizeType type : image.getImages().keySet()) {
            tmp.put(type, image.getImages().get(type).getUrl());
        }
        images.put(image.getId(), tmp);       
    }
    
    public void toListDTO(List<OfferImage> imagesSorce , Map<String, Map<OfferImageSizeType, String>> imagesTarget) {        
        for (OfferImage image : imagesSorce) {
            toDTO(image, imagesTarget);
        }
        
    }
}
