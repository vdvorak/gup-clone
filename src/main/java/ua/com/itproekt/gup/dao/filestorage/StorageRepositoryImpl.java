package ua.com.itproekt.gup.dao.filestorage;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.Set;

@Repository
public class StorageRepositoryImpl implements StorageRepository {
    private static final String FILE_STORAGE = ".file.storage";

    @Autowired
    private MongoTemplate mongoTemplate;

    private GridFS gridFs;

    @Override
    public String save(String serviceName, InputStream inputStream, String contentType, String filename) {
        this.gridFs =  new GridFS(mongoTemplate.getDb(), serviceName + FILE_STORAGE );
        GridFSInputFile input = gridFs.createFile(inputStream, filename, true);
        input.setContentType(contentType);
        input.save();
        return input.getId().toString();
    }

    @Override
    public GridFSDBFile get(String serviceName, String fileId) {
        this.gridFs =  new GridFS(mongoTemplate.getDb(), serviceName + FILE_STORAGE );
        return gridFs.findOne(new ObjectId(fileId));
    }

    @Override
    public void delete(String serviceName, String fileId) {
        this.gridFs =  new GridFS(mongoTemplate.getDb(), serviceName + FILE_STORAGE );
        gridFs.remove(new ObjectId(fileId));
    }

    @Override
    public void delete(String serviceName, Set<String> fileIds) {
        this.gridFs =  new GridFS(mongoTemplate.getDb(), serviceName + FILE_STORAGE );
        for (String fileId : fileIds) {
            gridFs.remove(new ObjectId(fileId));
        }
    }
}
