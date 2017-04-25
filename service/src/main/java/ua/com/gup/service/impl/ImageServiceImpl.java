package ua.com.gup.service.impl;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import ua.com.gup.repository.FileRepository;
import ua.com.gup.repository.FileUploadWrapper;
import ua.com.gup.service.ImageService;
import ua.com.gup.service.dto.OfferImageDTO;
import ua.com.gup.service.dto.enumeration.OfferImageSizeType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ImageServiceImpl implements ImageService {

    private static final Pattern IMAGE_BASE64_PATTERN = Pattern.compile("data:image/(png|jpg);base64,.*");
    private static final Pattern BASE64_PATTERN = Pattern.compile("data:image/(png|jpg);base64,(.*?)");
    private static final Pattern CONTENT_TYPE_PATTERN = Pattern.compile("data:(.*?);base64.*");
    private static final String OFFER_IMAGE_PATH = "offer/image/";

    private final Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);

    private final FileRepository fileRepository;

    @Autowired
    public ImageServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public String saveOfferImage(OfferImageDTO offerImageDTO, String fileName) {
        if (StringUtils.isEmpty(offerImageDTO.getImageId()) && !StringUtils.isEmpty(offerImageDTO.getBase64Data())
                && IMAGE_BASE64_PATTERN.matcher(offerImageDTO.getBase64Data()).matches()) {
            try {
                String imageBase64 = offerImageDTO.getBase64Data();
                Matcher matcher = CONTENT_TYPE_PATTERN.matcher(imageBase64);
                final String contentType = matcher.matches() ? matcher.group(1) : "";
                Matcher base64PatternMatcher = BASE64_PATTERN.matcher(imageBase64);
                if (base64PatternMatcher.matches()) {
                    BufferedImage bufferedImage = ImageIO.read(
                            new ByteArrayInputStream(Base64Utils.decodeFromString(base64PatternMatcher.group(2))));
                    FileUploadWrapper fileUploadWrapper = new FileUploadWrapper();
                    fileUploadWrapper.setFilename(fileName);
                    fileUploadWrapper.setContentType(contentType);
                    fileUploadWrapper.setId(offerImageDTO.getImageId());
                    // will save image in all size types
                    for (OfferImageSizeType type : OfferImageSizeType.values()) {
                        fileUploadWrapper.setBucket(OFFER_IMAGE_PATH + type.toString().toLowerCase());
                        BufferedImage resizedImage = resizeOfferImage(bufferedImage, type);
                        ByteArrayOutputStream os = new ByteArrayOutputStream();
                        ImageIO.write(resizedImage, contentType, os);
                        InputStream is = new ByteArrayInputStream(os.toByteArray());
                        fileUploadWrapper.setInputStream(is);
                        fileUploadWrapper.setId(fileRepository.save(fileUploadWrapper));
                    }
                    return fileUploadWrapper.getId();
                }
            } catch (IOException e) {
                log.error("Offer image saving exception: ", e);
            }
        }
        return null;
    }

    private BufferedImage resizeOfferImage(BufferedImage bufferedImage, OfferImageSizeType type) {
        int originHeight = bufferedImage.getHeight();
        int originWidth = bufferedImage.getWidth();
        if (type.getWidth() >= originWidth && type.getHeight() >= originHeight) {
            return bufferedImage;
        } else if (type.getWidth() < originWidth && type.getHeight() < originHeight) {
            return Scalr.resize(bufferedImage, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.AUTOMATIC, type.getWidth(), type.getHeight());
        } else {
            if (originWidth > type.getWidth()) {
                return Scalr.resize(bufferedImage, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_WIDTH, type.getWidth());
            } else {
                return Scalr.resize(bufferedImage, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_HEIGHT, type.getHeight());
            }
        }
    }
}
