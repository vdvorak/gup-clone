package ua.com.gup.common.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import ua.com.gup.common.model.FileInfo;
import ua.com.gup.common.model.FileType;

public interface FileStorageService {

    public FileInfo save(MultipartFile in);
    
    public FileInfo save(String filename, FileType type, byte[] bytes);

    public List<FileInfo> save(MultipartFile files[]);
    
    public void delete(String id, FileType type);
    
    public void delete(FileInfo fileInfo);
}
