package ua.com.gup.repository;


import ua.com.gup.server.api.rest.file.FileWrapper;
import ua.com.gup.model.offer.Image;

public interface FileRepository {

    String save(FileWrapper fileWrapper);

    FileWrapper findOne(String bucket, String id);

    void delete(String bucket, Image id);
}
