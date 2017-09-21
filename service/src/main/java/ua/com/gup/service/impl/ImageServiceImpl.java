package ua.com.gup.service.impl;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import ua.com.gup.dto.offer.OfferImageDTO;
import ua.com.gup.dto.offer.enumeration.OfferImageSizeType;
import ua.com.gup.model.offer.Image;
import ua.com.gup.repository.dao.file.FileRepository;
import ua.com.gup.server.api.rest.file.FileWrapper;
import ua.com.gup.service.ImageService;

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

    private static final Pattern IMAGE_BASE64_PATTERN = Pattern.compile("data:image/(png|jpg|jpeg);base64,.*");
    private static final Pattern BASE64_PATTERN = Pattern.compile("data:image/(png|jpg|jpeg);base64,(.*?)");
    private static final Pattern CONTENT_TYPE_PATTERN = Pattern.compile("data:(.*?);base64.*");
    private static final String OFFER_IMAGE_PATH = "offer/image/";

    private final Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);

    private final FileRepository fileRepository;

    @Autowired
    public ImageServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public FileWrapper findOne(String id, OfferImageSizeType type) {
        return fileRepository.findOne(OFFER_IMAGE_PATH + type.toString().toLowerCase(), id);
    }

    @Override
    public void deleteOfferImage(String id) {
        for (OfferImageSizeType type : OfferImageSizeType.values()) {
            fileRepository.delete(OFFER_IMAGE_PATH + type.toString().toLowerCase(), new Image(null,null,id));
        }
    }

    @Override
    public String saveOfferImage(OfferImageDTO offerImageDTO, String fileName) {
        if (!StringUtils.isEmpty(offerImageDTO.getBase64Data())
                && IMAGE_BASE64_PATTERN.matcher(offerImageDTO.getBase64Data()).matches()) {
            try {
                String imageBase64 = offerImageDTO.getBase64Data();
                Matcher matcher = CONTENT_TYPE_PATTERN.matcher(imageBase64);
                final String contentType = matcher.matches() ? matcher.group(1) : "";
                Matcher base64PatternMatcher = BASE64_PATTERN.matcher(imageBase64);
                if (base64PatternMatcher.matches()) {
                    if (!fileName.contains(".") && base64PatternMatcher.groupCount() > 1) {
                        fileName = fileName + "." + base64PatternMatcher.group(1);
                    }
                    BufferedImage bufferedImage = ImageIO.read(
                            new ByteArrayInputStream(Base64Utils.decodeFromString(base64PatternMatcher.group(2))));
                    FileWrapper fileWrapper = new FileWrapper();
                    fileWrapper.setFilename(fileName);
                    fileWrapper.setContentType(contentType);
                    fileWrapper.setId(offerImageDTO.getImageId());
                    // will save image in all size types
                    for (OfferImageSizeType type : OfferImageSizeType.values()) {
                        fileWrapper.setBucket(OFFER_IMAGE_PATH + type.toString().toLowerCase());
                        BufferedImage resizedImage = resizeOfferImage(bufferedImage, type);
                        ByteArrayOutputStream os = new ByteArrayOutputStream();
                        ImageIO.write(resizedImage, base64PatternMatcher.group(1), os);
                        InputStream is = new ByteArrayInputStream(os.toByteArray());
                        fileWrapper.setInputStream(is);
                        fileWrapper.setId(fileRepository.save(fileWrapper));
                    }
                    return fileWrapper.getId();
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
