package ua.com.gup.common.service.impl;

import ua.com.gup.common.util.CompletableFutureUtil;
import java.io.IOException;
import ua.com.gup.common.service.*;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import ua.com.gup.common.exeption.IOStorageException;
import ua.com.gup.common.exeption.StorageException;
import ua.com.gup.common.exeption.UnsupportedFileStorageException;
import ua.com.gup.common.model.FileInfo;
import ua.com.gup.common.model.FileType;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Autowired
    private AsyncRestTemplate asyncRestTemplate;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Environment e;
    private UriComponentsBuilder uriComponentsBuilder;

    @PostConstruct
    public void initialize() {
        uriComponentsBuilder = UriComponentsBuilder.newInstance()
                .scheme(e.getRequiredProperty("storage.host.scheme"))
                .host(e.getRequiredProperty("storage.host.address"))
                .port(e.getRequiredProperty("storage.host.port"))
                .path(e.getRequiredProperty("storage.host.context-path"))
                .path("/api");
    }
    
    @Override
    public void delete(FileInfo fileInfo){
        delete(fileInfo.getS3id(), fileInfo.getType());
    }
    
    @Override
    public void delete(String id, FileType type ){
        URI uri = createUri(type, id);
        restTemplate.delete(uri);
    }

    @Override
    public FileInfo save(MultipartFile file) {
        try {
            FileType type = FileType.fromContentType(file.getContentType());
            return save(file.getOriginalFilename(), type, file.getBytes());
        } catch (IOException ex) {
            throw new IOStorageException(ex);
        }
    }

    @Override
    public FileInfo save(String filename, FileType type, byte[] bytes) {
        HttpEntity<MultiValueMap<String, Object>> requestEntity = createRequest(type, bytes, filename);
        ResponseEntity<PostFileResponse> response = restTemplate.postForEntity(createUri(type), requestEntity, PostFileResponse.class);
        if (!HttpStatus.CREATED.equals(response.getStatusCode())) {
            throw new StorageException(response.getStatusCode().toString());
        }
        return createFileInfo(type, response.getBody().getContentType(), bytes.length, filename, response.getBody().getS3id());
    }

    @Override
    public List<FileInfo> save(MultipartFile files[]) {
        List<FileInfo> result = new ArrayList<>();
        if (files != null && files.length > 0) {
            int length = files.length;
            List<CompletableFuture> completableFutures = new ArrayList<>(length);
            for (MultipartFile file : files) {
                try {
                    FileType type = FileType.fromContentType(file.getContentType());
                    HttpEntity<MultiValueMap<String, Object>> reqeustEntity = createRequest(type, file.getBytes(), file.getOriginalFilename());
                    CompletableFuture<ResponseEntity<PostFileResponse>> completableFuture
                            = CompletableFutureUtil.toCompletableFuture(asyncRestTemplate.postForEntity(createUri(type), reqeustEntity, PostFileResponse.class));
                    completableFuture.whenCompleteAsync(new BiConsumer<ResponseEntity<PostFileResponse>, Throwable>() {
                        @Override
                        public void accept(ResponseEntity<PostFileResponse> t, Throwable u) {
                            try {
                                FileInfo info = createFileInfo(type, file.getOriginalFilename(),file.getSize(),file.getOriginalFilename(),t.getBody().getS3id());
                                result.add(info);
                            } catch (Exception ex) {
                                Logger.getLogger(FileStorageServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    });

                    completableFutures.add(completableFuture);
                } catch (IOException ex) {
                    Logger.getLogger(FileStorageServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //wait for all (which save images) responses complete
            CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[]{})).join();
        }
        return result;
    }

    private URI createUri(FileType type, String id) {
        return uriComponentsBuilder.cloneBuilder().path("/" + type.getPath() + "/" + id).build().toUri();
    }
    
    private URI createUri(FileType type) {
        return uriComponentsBuilder.cloneBuilder().path("/" + type.getPath() + "/").build().toUri();
    }

    private HttpHeaders createMultyPartHeader() {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.MULTIPART_FORM_DATA);
        return header;
    }

    private HttpEntity<MultiValueMap<String, Object>> createRequest(FileType type, byte[] bytes, String filename) {
        HttpHeaders headers = createMultyPartHeader();
        FileMessageResource res = new FileMessageResource(bytes, filename);
        LinkedMultiValueMap<String, Object> multipartRequest = new LinkedMultiValueMap<>();
        multipartRequest.add(type.getRequestParamName(), res);
        return new HttpEntity<>(multipartRequest, headers);
    }

    private FileInfo createFileInfo(FileType type, String contentType, long size, String filename, String s3id) {
        try {
            FileInfo fileInfo = type.createInstance();
            fileInfo.setContentType(contentType);
            fileInfo.setS3id(s3id);
            fileInfo.setSize(size);
            fileInfo.setFileName(filename);
            return fileInfo;
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new UnsupportedFileStorageException(ex);
        }
    }

}

class PostFileResponse {

    private String s3id;
    private String contentMD5;
    private String contentType;
    private URL imageURL;

    public PostFileResponse() {
    }

    public String getS3id() {
        return s3id;
    }

    public void setS3id(String s3id) {
        this.s3id = s3id;
    }

    public String getContentMD5() {
        return contentMD5;
    }

    public void setContentMD5(String contentMD5) {
        this.contentMD5 = contentMD5;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public URL getImageURL() {
        return imageURL;
    }

    public void setImageURL(URL imageURL) {
        this.imageURL = imageURL;
    }

}
