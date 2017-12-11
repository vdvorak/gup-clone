package ua.com.gup.service.filestorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.com.gup.common.model.FileInfo;
import ua.com.gup.common.model.FileType;
import ua.com.gup.common.model.ImageFileInfo;
import ua.com.gup.common.service.FileStorageService;
import ua.com.gup.common.util.ImageScaleUtil;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.repository.profile.ProfileRepository;
import ua.com.gup.service.profile.ProfilesService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired(required = false)
    private FileStorageService storageService;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfilesService profilesService;

    @Override
    public void deleteProfileImage(String userId) {
        Profile profile = profilesService.findById(userId);
        if (profile.getImageLarge() != null) {
            ImageFileInfo imageLarge = profile.getImageLarge();
            storageService.delete(imageLarge);
            profile.setImageLarge(null);
        }

        if (profile.getImageSmall() != null) {
            ImageFileInfo imageSmall = profile.getImageSmall();
            storageService.delete(imageSmall);
            profile.setImageSmall(null);
        }
        profileRepository.save(profile);
    }   

    @Override
    public Map<String, String> saveCachedImageProfile(String profileId, MultipartFile file) {
        try {
            Profile profile = profilesService.findById(profileId);

            String extension = file.getContentType().split("/")[1];
            BufferedImage inputImage = ImageIO.read(file.getInputStream());

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            BufferedImage large = ImageScaleUtil.largeBufferedImageProfilePreparator(inputImage);
            ImageIO.write(large, extension, os);
            FileInfo info = storageService.save("large_" + file.getOriginalFilename(), FileType.IMAGE, os.toByteArray());
            profile.setImageLarge((ImageFileInfo) info);

            os = new ByteArrayOutputStream();
            BufferedImage small = ImageScaleUtil.smallBufferedImageProfilePreparator(inputImage);
            ImageIO.write(small, extension, os);
            info = storageService.save("small_" + file.getOriginalFilename(), FileType.IMAGE, os.toByteArray());
            profile.setImageSmall((ImageFileInfo) info);


            profilesService.editProfile(profile);

            Map<String, String> urls = new HashMap<>();
            urls.put("large", profile.getImageLarge().getS3id());
            urls.put("small", profile.getImageSmall().getS3id());
            return urls;
        } catch (IOException ex) {
            Logger.getLogger(StorageServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
