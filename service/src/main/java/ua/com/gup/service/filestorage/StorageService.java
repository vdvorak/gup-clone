package ua.com.gup.service.filestorage;

import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    /**
     * Delete profile photo (avatar) and replace it with stub image.
     *
     * @param userId - the user ID.
     */
    void deleteProfileImage(String userId);  
    
    
    /**
     * Delete profile photo (avatar) 
     *
     * @param publicUserId - the user public ID.
     */
    void deleteProfileImageByPublicId(String publicUserId);  

    /**
     * Save photo in two variants: large and small
     *
     * @param file - the image.
     * @return
     */
    public Map<String,String> saveCachedImageProfile(String profileId, MultipartFile file);
}
