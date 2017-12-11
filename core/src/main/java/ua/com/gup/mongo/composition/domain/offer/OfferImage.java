package ua.com.gup.mongo.composition.domain.offer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import ua.com.gup.common.model.FileInfo;

public class OfferImage {

    private String id;
    private Map<OfferImageSizeType,FileInfo> images;

    public OfferImage() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<OfferImageSizeType, FileInfo> getImages() {
        if(images == null){
            images = new HashMap();
        }
        return images;
    }

    public void setImages(Map<OfferImageSizeType, FileInfo> images) {
        this.images = images;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OfferImage other = (OfferImage) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
}
