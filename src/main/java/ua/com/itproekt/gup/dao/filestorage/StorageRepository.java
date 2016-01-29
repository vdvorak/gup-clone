package ua.com.itproekt.gup.dao.filestorage;

import com.mongodb.gridfs.GridFSDBFile;

import java.io.InputStream;
import java.util.Set;

public interface StorageRepository {
    String save(String serviceName, InputStream inputStream, String contentType, String filename);

    GridFSDBFile get(String serviceName, String fileId);

    void delete(String serviceName, String fileId);

    void delete(String serviceName, Set<String> fileIds);
}
