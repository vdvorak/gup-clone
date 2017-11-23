package ua.com.gup.rent.service.rent.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import ua.com.gup.rent.dto.rentobject.CreateRentObjectDTO;
import ua.com.gup.rent.dto.rentobject.EditRentObjectDTO;
import ua.com.gup.rent.dto.rentobject.RentObjectDTO;
import ua.com.gup.rent.dto.rentobject.ShortDetailsRentObjectDTO;
import ua.com.gup.rent.mapper.RentObjectMapper;
import ua.com.gup.rent.model.mongo.RentObject;
import ua.com.gup.rent.model.mongo.image.ImageInfo;
import ua.com.gup.rent.repository.rent.RentObjectRepository;
import ua.com.gup.rent.service.abstracted.GenericServiceImpl;
import ua.com.gup.rent.service.rent.RentObjectService;
import ua.com.gup.rent.util.CompletableFutureUtil;

import javax.annotation.PostConstruct;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class RentObjectServiceImpl extends GenericServiceImpl<RentObjectDTO, String> implements RentObjectService {

    @Autowired
    private Environment e;

    @Autowired
    private RentObjectMapper rentObjectMapper;

    @Autowired
    private AsyncRestTemplate asyncRestTemplate;
    @Autowired
    private RestTemplate restTemplate;

    private UriComponentsBuilder uriComponentsBuilder;

    @Value(value = "classpath:images/demo.png")
    private Resource demoImage;

    @PostConstruct
    public void initialize() {

        uriComponentsBuilder = UriComponentsBuilder.newInstance().scheme(e.getRequiredProperty("storage.host.scheme"))
                .host(e.getRequiredProperty("storage.host.address"))
                .port(e.getRequiredProperty("storage.host.port"))
                .path(e.getRequiredProperty("storage.host.context-path"))
                .path("/api");

    }

    public RentObjectServiceImpl(@Autowired RentObjectRepository rentObjectRepository) {
        super(rentObjectRepository);
    }


    private void handleMultipartFile() {

    }

    @Override
    public void create(CreateRentObjectDTO t) {
        RentObject rentObject = rentObjectMapper.fromCreateDTOToRentObject(t);
        MultipartFile[] files = t.getImages();
        //if images exists save it's async
        if (files != null && files.length > 0) {
            int length = files.length;
            List<ImageInfo> images = new ArrayList<>(length);

            HttpHeaders commonHeader = new HttpHeaders();
            commonHeader.setContentType(MediaType.MULTIPART_FORM_DATA);

            UriComponents uriComponents = uriComponentsBuilder.cloneBuilder().path("/images/").build();

            List<CompletableFuture> completableFutures = new ArrayList<>(length);
            for (int i = 0; i < length; i++) {
                MultipartFile multipartFile = files[i];

                LinkedMultiValueMap<String, Object> multipartRequest = new LinkedMultiValueMap<>();


                Path path = Paths.get(System.getProperty("java.io.tmpdir")).resolve(multipartFile.getOriginalFilename());
//                Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                multipartRequest.add("image", new FileSystemResource(path.toString()));


                HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(multipartRequest, commonHeader);

                CompletableFuture<ResponseEntity<PostImageResponse>> completableFuture =
                        CompletableFutureUtil.toCompletableFuture(asyncRestTemplate.postForEntity(uriComponents.toUri(), requestEntity, PostImageResponse.class));

                completableFuture.whenCompleteAsync(((responseEntity, throwable) -> {
                    PostImageResponse response = responseEntity.getBody();
                    ImageInfo info = new ImageInfo();
                    info.setS3id(response.getS3id());
                    info.setContentType(multipartFile.getContentType());
                    info.setFileName(multipartFile.getOriginalFilename());
                    info.setSize(multipartFile.getSize());
                    images.add(info);

                }));
                completableFutures.add(completableFuture);
            }
            //wait for all (which save images) responses complete
            CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[]{})).join();
            rentObject.setImages(images);
        }


//        rentObject.setOwnerId(SecurityContextHolder.);
        getRepository().create(rentObject);
    }

    @Override
    public void update(EditRentObjectDTO editRentObjectDTO) {
        getRepository().update(null);
    }

    @Override
    public void deleteById(String rentObjectId) {
        getRepository().deleteById(rentObjectId);
    }

    @Override
    public List<ShortDetailsRentObjectDTO> findAll() {
        List<RentObject> rentObjects = getRepository().findAll();
        return rentObjects.stream().map(rentObject -> rentObjectMapper.fromRentObjectToShortDTO(rentObject)).collect(Collectors.toList());
    }

    @Override
    protected RentObjectRepository getRepository() {
        return (RentObjectRepository) super.getRepository();
    }

}

class PostImageResponse {

    private String s3id;
    private String contentMD5;

    public PostImageResponse() {
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
}