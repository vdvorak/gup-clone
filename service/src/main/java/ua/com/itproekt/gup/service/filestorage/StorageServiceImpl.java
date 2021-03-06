package ua.com.itproekt.gup.service.filestorage;

import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.com.itproekt.gup.dao.filestorage.StorageRepository;
import ua.com.itproekt.gup.server.api.rest.dto.FileUploadWrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    StorageRepository storageRepository;

    @Override
    public void delete(String serviceName, String fileId) {

        //ToDo логика подстановки filePath
        // Сейчас тут костыль
        String kostyl = null;

        storageRepository.delete(serviceName, kostyl, fileId);
    }

    @Override
    public void delete(String serviceName, Set<String> fileIds) {

        //ToDo логика подстановки filePath
        // Сейчас тут костыль
        String kostyl = null;

        storageRepository.delete(serviceName, kostyl, fileIds);
    }

    /**
     * Delete offer images in all cached variants
     *
     * @param imagesId
     */
    @Override
    public void deleteListOfOfferImages(Set<String> imagesId) {
        storageRepository.delete("offers", ".file.storage.large.cache", imagesId);
        storageRepository.delete("offers", ".file.storage.medium.cache", imagesId);
        storageRepository.delete("offers", ".file.storage.small.cache", imagesId);
    }

    @Override
    public void deleteDiffImagesAfterOfferUpdate(Map<String, String> oldImagesMap, Map<String, String> newImagesMap) {
        deleteListOfOfferImages(compareTwoMapAndReturnDiffKeys(oldImagesMap, newImagesMap));
    }

    @Override
    public GridFSDBFile getCachedImage(String serviceName, String filePath, String fileId) {
        return storageRepository.getCachedImage(serviceName, filePath, fileId);
    }

    @Override
    public String saveCachedImageProfile(FileUploadWrapper fileUploadWrapper) {
        return storageRepository.saveCachedImageProfile(fileUploadWrapper);
    }

    @Override
    public String saveCachedImageOffer(FileUploadWrapper fileUploadWrapper) {
        return storageRepository.saveCachedImageOffer(fileUploadWrapper);
    }


    /**
     * All files from multipart array saved in cached size
     *
     * @param files Multipart array
     * @return Map of images id's and their order.
     */
    @Override
    public Map<String, String> saveCachedMultiplyImageOffer(MultipartFile[] files, int firstPosition) {

        Map<String, String> mapOfImagesIds = new HashMap<>();

        for (int i = 0; i < files.length; i++) {

            FileUploadWrapper fileUploadWrapper = new FileUploadWrapper();

            try {
                fileUploadWrapper
                        .setServiceName("offers")
                        .setInputStream(files[i].getInputStream())
                        .setContentType(files[i].getContentType())
                        .setFilename(files[i].getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }

            mapOfImagesIds.put(saveCachedImageOffer(fileUploadWrapper), String.valueOf(firstPosition));
            firstPosition++;
        }
        return mapOfImagesIds;
    }


    @Override
    public MultipartFile[] imageDownloader(List<String> imagesUrlList) {
        List<MultipartFile> multipartFiles = new ArrayList<>();

        for (String imageUrl : imagesUrlList) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            InputStream is = null;


            try {
                URL url = new URL(imageUrl);
                is = url.openStream();
                byte[] byteChunk = new byte[4096]; // Or whatever size you want to read in at a time.
                int n;

                while ((n = is.read(byteChunk)) > 0) {
                    baos.write(byteChunk, 0, n);
                }

                MultipartFile multipartFile = new MockMultipartFile("fileFromImport",
                        url.getFile(), "image/jpeg", baos.toByteArray());

                multipartFiles.add(multipartFile);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return multipartFiles.toArray(new MultipartFile[multipartFiles.size()]);
    }

    /**
     * @param oldImagesMap
     * @param newImagesMap
     * @return
     */
    private Set<String> compareTwoMapAndReturnDiffKeys(Map<String, String> oldImagesMap, Map<String, String> newImagesMap) {
        Set<String> diffMap = new HashSet<>();

        boolean hasRemove = true;

        for (String s : oldImagesMap.keySet()) {

            for (String s1 : newImagesMap.keySet()) {

                if (s.equals(s1)) {
                    hasRemove = false;
                }
            }
            if (hasRemove) {
                diffMap.add(s);
            }
            hasRemove = true;
        }
        return diffMap;
    }
}
