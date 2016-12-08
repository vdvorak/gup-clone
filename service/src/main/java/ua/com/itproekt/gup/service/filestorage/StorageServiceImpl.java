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



    @Override
    public Map<String, String> saveCachedMultiplyImageOfferWithIndex(MultipartFile[] files, int startPosition, int firstImageIndexInArray) {


        MultipartFile[] newFileArray = new MultipartFile[files.length];
        // if firstImageIndexInArray == 1 - that's what we need, if < 1 - we ignore it.
        if (firstImageIndexInArray > 0) {

            // here we take element with firstImageIndexInArray and put it in the first place in the new array

            newFileArray[0] = files[firstImageIndexInArray];


            for (int i = 0; i < firstImageIndexInArray; i++) {
                newFileArray[i + 1] = files[i];
            }

            for (int i = firstImageIndexInArray + 1; i < files.length; i++) {
                newFileArray[i] = files[i];
            }

        }


        Map<String, String> mapOfImagesIds = new HashMap<>();

        for (int i = 0; i < newFileArray.length; i++) {

            FileUploadWrapper fileUploadWrapper = new FileUploadWrapper();

            try {
                fileUploadWrapper
                        .setServiceName("offers")
                        .setInputStream(newFileArray[i].getInputStream())
                        .setContentType(newFileArray[i].getContentType())
                        .setFilename(newFileArray[i].getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }

            mapOfImagesIds.put(saveCachedImageOffer(fileUploadWrapper), String.valueOf(startPosition));
            startPosition++;
        }
        return mapOfImagesIds;
    }


    @Override
    public Map<String, String> saveCachedMultiplyImageOffer(MultipartFile[] files, int startPosition) {
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

            mapOfImagesIds.put(saveCachedImageOffer(fileUploadWrapper), String.valueOf(startPosition));
            startPosition++;
        }
        return mapOfImagesIds;
    }


    @Override
    public MultipartFile[] imageDownloader(List<String> imagesUrlList) {
        List<MultipartFile> multipartFiles = new ArrayList<>();

        for (String imageUrl : imagesUrlList) {
            multipartFiles.add(imageDownloader(imageUrl));
        }
        return multipartFiles.toArray(new MultipartFile[multipartFiles.size()]);
    }


    @Override
    public MultipartFile imageDownloader(String imageUrl) {

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

            return new MockMultipartFile("fileFromImport", url.getFile(), "image/jpeg", baos.toByteArray());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


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
