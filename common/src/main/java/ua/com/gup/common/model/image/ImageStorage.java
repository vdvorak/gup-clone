package ua.com.gup.common.model.image;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import ua.com.gup.common.model.FileInfo;
import ua.com.gup.common.model.ImageFileInfo;

public class ImageStorage {

    private String id;
    private Map<ImageSizeType,ImageFileInfo> images;

    public ImageStorage() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<ImageSizeType, ImageFileInfo> getImages() {
        if(images == null){
            images = new HashMap();
        }
        return images;
    }

    public void setImages(Map<ImageSizeType, ImageFileInfo> images) {
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
        final ImageStorage other = (ImageStorage) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
}
