package ua.com.gup.common.service.mapper;

import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ua.com.gup.common.model.image.ImageStorage;
import ua.com.gup.common.model.image.ImageSizeType;


@Component
public class ImageStorageMapper {    

    public void toDTO(ImageStorage image , Map<String, Map<ImageSizeType, String>> images) {        
        Map<ImageSizeType, String> tmp = new HashMap();
        for (ImageSizeType type : image.getImages().keySet()) {
            tmp.put(type, image.getImages().get(type).getUrl());
        }
        images.put(image.getId(), tmp);       
    }
    
    public void toListDTO(List<ImageStorage> imagesSource , Map<String, Map<ImageSizeType, String>> imagesTarget) {
        for (ImageStorage image : imagesSource) {
            toDTO(image, imagesTarget);
        }
        
    }
}
