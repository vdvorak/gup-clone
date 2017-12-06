package ua.com.gup.common.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import ua.com.gup.common.model.FileInfo;

public interface FileStorageService {

    public FileInfo save(MultipartFile in) ;    

    public List<FileInfo> save(MultipartFile files[]) throws Exception;

}
