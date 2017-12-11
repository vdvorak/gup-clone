package ua.com.gup.rent.service.storage;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import ua.com.gup.common.service.impl.FileStorageServiceImpl;

import javax.annotation.PostConstruct;

@Service
public class FileStorageServiceRentImpl extends FileStorageServiceImpl{

    @PostConstruct
    public void initialize() {
        uriComponentsBuilder = UriComponentsBuilder.newInstance()
                .scheme(e.getRequiredProperty("storage.host.scheme"))
                .host(e.getRequiredProperty("storage.host.address"))
                .port(e.getRequiredProperty("storage.host.port"))
                .path(e.getRequiredProperty("storage.host.context-path"))
                .path("/api");
    }
    
}
