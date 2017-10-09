package ua.com.gup.service.filestorage;

import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.com.gup.model.file.FileUploadWrapper;
import ua.com.gup.model.profiles.Profile;
import ua.com.gup.repository.filestorage.StorageRepository;
import ua.com.gup.service.profile.ProfilesService;
import ua.com.gup.model.CreatedObjResp;

import java.io.IOException;
import java.util.Set;

@Service
public class StorageServiceImpl implements StorageService {

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
        String imageId = profile.getImgId();

        if (imageId != null) {
            delete(PROFILE_SERVICE_NAME, profile.getId());
        }

        profile.setImgId(PROFILE_IMAGE_STUB_ID);
        profilesService.editProfile(profile);
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
    public ResponseEntity<InputStreamResource> readProfileCachedImage(String userId, String cachedSize) {

        GridFSDBFile gridFSDBFile;

        String path = ".file.storage." + cachedSize + ".cache";

        // image stub for case when user doesn't hav avatar
        gridFSDBFile = getCachedImage(PROFILE_SERVICE_NAME, path, PROFILE_IMAGE_STUB_ID);

        Profile profile = profilesService.findById(userId);
        if (profile == null) {
            return responseEntityPreparator(gridFSDBFile);
        }

        if (profile.getImgId() == null) {
            return responseEntityPreparator(gridFSDBFile);
        }

        gridFSDBFile = getCachedImage(PROFILE_SERVICE_NAME, path, profile.getImgId());

        if (gridFSDBFile != null) {
            return responseEntityPreparator(gridFSDBFile);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<InputStreamResource> readCachedImage(String serviceName, String fileId, String cachedSize) {

        GridFSDBFile gridFSDBFile;

        String path = ".file.storage." + cachedSize + ".cache";

        gridFSDBFile = storageRepository.getCachedImage(serviceName, path, fileId);

        if (gridFSDBFile != null) {
            return responseEntityPreparator(gridFSDBFile);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public String saveCachedImageProfile(FileUploadWrapper fileUploadWrapper) {
        return storageRepository.saveCachedImageProfile(fileUploadWrapper);
    }


    @Override
    public ResponseEntity<CreatedObjResp> saveCachedImageProfile(MultipartFile file) {
        FileUploadWrapper fileUploadWrapper = new FileUploadWrapper();

        // Prepare file
        try {
            fileUploadWrapper
                    .setServiceName(PROFILE_SERVICE_NAME)
                    .setInputStream(file.getInputStream())
                    .setContentType(file.getContentType())
                    .setFilename(file.getOriginalFilename());
        } catch (IOException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String uploadedFileId = saveCachedImageProfile(fileUploadWrapper);
        return new ResponseEntity<>(new CreatedObjResp(uploadedFileId), HttpStatus.CREATED);
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
