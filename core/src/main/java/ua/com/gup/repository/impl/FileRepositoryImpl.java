package ua.com.gup.repository.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import ua.com.gup.repository.FileRepository;
import ua.com.gup.repository.file.FileWrapper;
import ua.com.gup.model.offer.Image;

import java.util.Map;

@Repository
public class FileRepositoryImpl implements FileRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String save(FileWrapper fileWrapper) {
        // save image
        GridFS gridFS = new GridFS(mongoTemplate.getDb(), fileWrapper.getBucket());
        GridFSInputFile inputFile = gridFS.createFile(fileWrapper.getInputStream(),
                fileWrapper.getFilename(), true);

        // it can be null if it first image
        if (fileWrapper.getId() != null) {
            inputFile.setId(new ObjectId(fileWrapper.getId()));
        }
        inputFile.setContentType(fileWrapper.getContentType());

        DBObject metaData = new BasicDBObject();
        Map<String, Object> metadataMap = fileWrapper.getMetadata();
        for (String key : metadataMap.keySet()) {
            metaData.put(key, metadataMap.get(key));
        }
        inputFile.setMetaData(metaData);
        inputFile.save();
        return inputFile.getId().toString();
    }

    @Override
    public FileWrapper findOne(String bucket, String id) {
        GridFS gridFS = new GridFS(mongoTemplate.getDb(), bucket);
        final GridFSDBFile gridFSDBFile = gridFS.findOne(new ObjectId(id));
        FileWrapper fileWrapper = new FileWrapper();
        fileWrapper.setBucket(bucket);
        fileWrapper.setContentType(gridFSDBFile.getContentType());
        fileWrapper.setFilename(gridFSDBFile.getFilename());
        fileWrapper.setLength(gridFSDBFile.getLength());
        fileWrapper.setId(gridFSDBFile.getId().toString());
        fileWrapper.setInputStream(gridFSDBFile.getInputStream());
        return fileWrapper;
    }

    @Override
    public void delete(String bucket, Image id) {
        GridFS gridFs = new GridFS(mongoTemplate.getDb(), bucket);
        if (ObjectId.isValid(id.getImageId())) {
            gridFs.remove(new ObjectId(id.getImageId()));
        }
    }
}
