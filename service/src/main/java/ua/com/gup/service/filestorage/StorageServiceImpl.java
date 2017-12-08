package ua.com.gup.service.filestorage;

import com.google.common.collect.HashBiMap;
import com.mongodb.gridfs.GridFSDBFile;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.mongo.model.file.FileUploadWrapper;
import ua.com.gup.mongo.model.other.CreatedObjResp;
import ua.com.gup.repository.filestorage.StorageRepository;
import ua.com.gup.service.profile.ProfilesService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import ua.com.gup.common.model.FileInfo;
import ua.com.gup.common.model.FileType;
import ua.com.gup.common.model.ImageFileInfo;
import ua.com.gup.common.service.FileStorageService;
import ua.com.gup.common.util.ImageScaleUtil;
import ua.com.gup.repository.profile.ProfileRepository;

@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    private FileStorageService storageService;
    @Autowired
    private ProfileRepository profileRepository;
    
    private final String PROFILE_SERVICE_NAME = "profile";
    private final String PROFILE_IMAGE_STUB_ID = "57e3d1548f70bc65995fd062";

    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private ProfilesService profilesService;

    @Override
    public void delete(String serviceName, String fileId) {
        storageRepository.delete(serviceName, null, fileId);
    }

    @Override
    public void delete(String serviceName, Set<String> fileIds) {

        //ToDo логика подстановки filePath
        // Сейчас тут костыль
        String kostyl = null;

        storageRepository.delete(serviceName, kostyl, fileIds);
    }

    @Override
    public void deleteProfileImage(String userId) {
        Profile profile = profilesService.findById(userId);
        if (profile.getImageLarge() != null) {
            ImageFileInfo imageLarge = profile.getImageLarge();
            storageService.delete(imageLarge);
            profile.setImageLarge(null);
        }
        
        if (profile.getImageSmall()!= null) {
            ImageFileInfo imageSmall = profile.getImageSmall();
            storageService.delete(imageSmall);
            profile.setImageSmall(null);
        }        
        profileRepository.save(profile);
    }

    @Override
    public void deleteListOfOfferImages(Set<String> imagesId) {
        storageRepository.delete("offers", ".file.storage.large.cache", imagesId);
        storageRepository.delete("offers", ".file.storage.medium.cache", imagesId);
        storageRepository.delete("offers", ".file.storage.small.cache", imagesId);
    }


    @Override
    public GridFSDBFile getCachedImage(String serviceName, String filePath, String fileId) {
        return storageRepository.getCachedImage(serviceName, filePath, fileId);
    }
   
  
    @Override
    public Map<String,String> saveCachedImageProfile(String profileId, MultipartFile file) {
        try {           
            Profile profile = profilesService.findById(profileId);
            
            String extension = file.getContentType().split("/")[1];
            BufferedImage inputImage = ImageIO.read(file.getInputStream());
            
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            BufferedImage large = ImageScaleUtil.largeBufferedImageProfilePreparator(inputImage);            
            ImageIO.write(large, extension, os);            
            FileInfo info = storageService.save("large_"+file.getOriginalFilename(), FileType.IMAGE, os.toByteArray());
            profile.setImageLarge((ImageFileInfo) info);
            
            os = new ByteArrayOutputStream();  
            BufferedImage small = ImageScaleUtil.smallBufferedImageProfilePreparator(inputImage);
            ImageIO.write(small, extension, os);        
            info = storageService.save("small_"+file.getOriginalFilename(), FileType.IMAGE, os.toByteArray());
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


    @Override
    public String saveCachedImageOffer(FileUploadWrapper fileUploadWrapper) {
        return storageRepository.saveCachedImageOffer(fileUploadWrapper);
    }


    // ------------------------------------------ Helper methods ----------------------------------------

    /**
     * Method prepare response for the client from the GridFSDBFile.
     *
     * @param gridFSDBFile - the GridFSDBFile.
     * @return - the ResponseEntity object which will be sent to the client side.
     */
    private ResponseEntity<InputStreamResource> responseEntityPreparator(GridFSDBFile gridFSDBFile) {
        return ResponseEntity.ok()
                .contentLength(gridFSDBFile.getLength())
                .contentType(MediaType.parseMediaType(gridFSDBFile.getContentType()))
                .header("Content-Disposition", "attachment; filename=" + gridFSDBFile.getFilename())
                .body(new InputStreamResource(gridFSDBFile.getInputStream()));
    }

   


}
