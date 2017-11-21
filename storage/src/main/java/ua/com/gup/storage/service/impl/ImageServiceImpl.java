package ua.com.gup.storage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.storage.dto.ImageDTO;
import ua.com.gup.storage.repository.ImageRepository;
import ua.com.gup.storage.service.ImageService;

import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public String saveImage(String imageKey, String contentType, InputStream inputStream) throws IOException {
        return imageRepository.saveImage(imageKey, contentType, inputStream);
    }

    @Override
    public ImageDTO getImageByKey(String imageKey) {
        return imageRepository.getImageInputStreamByKey(imageKey);
    }

    @Override
    public void deleteImageByKey(String imageKey) {
        imageRepository.deleteImageByKey(imageKey);
    }

    @Override
    public Boolean doesObjectExists(String imageKey) {
        return imageRepository.doesObjectExists(imageKey);
    }
}
