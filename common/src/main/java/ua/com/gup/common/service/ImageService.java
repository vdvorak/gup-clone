package ua.com.gup.common.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import ua.com.gup.common.model.image.ImageStorage;

public interface ImageService {

    public ImageStorage saveImageStorage(MultipartFile file) throws IOException;

    public void deleteImageStorage(ImageStorage image);
}
