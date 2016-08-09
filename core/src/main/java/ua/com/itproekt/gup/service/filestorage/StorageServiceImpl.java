package ua.com.itproekt.gup.service.filestorage;

import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.dao.filestorage.StorageRepository;
import ua.com.itproekt.gup.server.api.rest.dto.FileUploadWrapper;

import java.util.Set;

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

        storageRepository.delete(serviceName,kostyl, fileIds);
    }

    @Override
    public GridFSDBFile getCachedImage(String serviceName, String filePath, String fileId) {
        return storageRepository.getCachedImage(serviceName, filePath, fileId);
    }

    @Override
    public String saveCachedImageProfile(FileUploadWrapper fileUploadWrapper) {
        return storageRepository.saveCachedImageProfile(fileUploadWrapper);
    }

}
