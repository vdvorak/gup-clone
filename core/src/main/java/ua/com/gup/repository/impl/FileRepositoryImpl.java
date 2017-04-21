package ua.com.gup.repository.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import ua.com.gup.repository.FileRepository;
import ua.com.gup.repository.FileUploadWrapper;

import java.util.Map;

@Repository
public class FileRepositoryImpl implements FileRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String save(FileUploadWrapper fileUploadWrapper) {
        // save image
        GridFS gridFS = new GridFS(mongoTemplate.getDb(), fileUploadWrapper.getBucket());
        GridFSInputFile inputFile = gridFS.createFile(fileUploadWrapper.getInputStream(),
                fileUploadWrapper.getFilename(), true);

        // it can be null if it first image
        if (fileUploadWrapper.getId() != null) {
            inputFile.setId(new ObjectId(fileUploadWrapper.getId()));
        }
        inputFile.setContentType(fileUploadWrapper.getContentType());

        DBObject metaData = new BasicDBObject();
        Map<String, Object> metadataMap = fileUploadWrapper.getMetadata();
        for (String key : metadataMap.keySet()) {
            metaData.put(key, metadataMap.get(key));
        }
        inputFile.setMetaData(metaData);
        inputFile.save();
        return inputFile.getId().toString();
    }

}
