package ua.com.gup.common.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.com.gup.common.model.FileInfo;
import ua.com.gup.common.model.FileType;
import ua.com.gup.common.model.ImageFileInfo;
import ua.com.gup.common.model.image.ImageStorage;
import ua.com.gup.common.model.image.ImageSizeType;
import ua.com.gup.common.service.FileStorageService;
import ua.com.gup.common.service.ImageService;
import ua.com.gup.common.util.ImageScaleUtil;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private FileStorageService fileStorageService;

    @Override
    public ImageStorage saveImageStorage(MultipartFile file) throws IOException {
        String extension = file.getContentType().split("/")[1];
        BufferedImage inputImage = ImageIO.read(file.getInputStream());

        ImageStorage offerImage = new ImageStorage();
        offerImage.setId(UUID.randomUUID().toString());

        Map<ImageSizeType, ImageFileInfo> map = new HashMap<>();
        byte[] largeImage = convertImage(inputImage, ImageSizeType.LARGE, extension);
        FileInfo info = fileStorageService.save(ImageSizeType.LARGE + "_" + file.getOriginalFilename(), FileType.IMAGE, largeImage);
        map.put(ImageSizeType.LARGE, (ImageFileInfo) info);

        byte[] mediumImage = convertImage(inputImage, ImageSizeType.MEDIUM, extension);
        info = fileStorageService.save(ImageSizeType.MEDIUM + "_" + file.getOriginalFilename(), FileType.IMAGE, mediumImage);
        map.put(ImageSizeType.MEDIUM, (ImageFileInfo) info);

        byte[] smallImage = convertImage(inputImage, ImageSizeType.SMALL, extension);
        info = fileStorageService.save(ImageSizeType.SMALL + "_" + file.getOriginalFilename(), FileType.IMAGE, smallImage);
        map.put(ImageSizeType.SMALL, (ImageFileInfo) info);
        offerImage.setImages(map);
        return offerImage;
    }

    private byte[] convertImage(BufferedImage srcImage, ImageSizeType size, String extension) throws IOException {
        BufferedImage image = null;
        switch (size) {
            case LARGE:
                image = ImageScaleUtil.largeBufferedImageOfferPreparator(srcImage);
                break;
            case MEDIUM:
                image = ImageScaleUtil.mediumBufferedImageOfferPreparator(srcImage);
                break;
            case SMALL:
                image = ImageScaleUtil.smallBufferedImageOfferPreparator(srcImage);
                break;
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, extension, os);
        return os.toByteArray();
    }
    
    @Override
    public  void deleteImageStorage(ImageStorage image) {
        for (FileInfo fileInfo : image.getImages().values()) {
            fileStorageService.delete(fileInfo);
        }
    }
}
