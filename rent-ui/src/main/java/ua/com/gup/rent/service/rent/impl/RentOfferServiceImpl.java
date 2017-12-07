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
import ua.com.gup.rent.mapper.RentOfferMapper;
import ua.com.gup.rent.model.image.RentOfferImageInfo;
import ua.com.gup.rent.model.mongo.rent.RentOffer;
import ua.com.gup.rent.repository.rent.RentOfferRepository;
import ua.com.gup.rent.service.abstracted.RentOfferGenericServiceImpl;
import ua.com.gup.rent.service.dto.rent.RentOfferModerationReportDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferCreateDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferUpdateDTO;
import ua.com.gup.rent.service.dto.rent.offer.view.RentOfferViewDetailsDTO;
import ua.com.gup.rent.service.dto.rent.offer.view.RentOfferViewShortDTO;
import ua.com.gup.rent.service.rent.RentOfferService;
import ua.com.gup.rent.service.rent.image.RentOfferPostImageResponse;

import javax.annotation.PostConstruct;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


@Service
public class RentOfferServiceImpl extends RentOfferGenericServiceImpl<RentOfferDTO, String> implements RentOfferService {

    @Autowired
    private Environment e;

    @Autowired
    private RentOfferMapper rentOfferMapper;

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

    public RentOfferServiceImpl(@Autowired RentOfferRepository rentOfferRepository) {
        super(rentOfferRepository);
    }


    private void handleMultipartFile() {

    }

    @Override
    public void create(RentOfferCreateDTO target) {
        RentOffer rentOffer = rentOfferMapper.fromCreateDTOToRentObject(target);
        MultipartFile[] files = target.getImages();
        //if images exists save it's async
        if (files != null && files.length > 0) {
            int length = files.length;
            List<RentOfferImageInfo> images = new ArrayList<>(length);

            HttpHeaders commonHeader = new HttpHeaders();
            commonHeader.setContentType(MediaType.MULTIPART_FORM_DATA);

            UriComponents uriComponents = uriComponentsBuilder.cloneBuilder().path("/images/").build();

            List<CompletableFuture> completableFutures = new ArrayList<>(length);
            for (int i = 0; i < length; i++) {
                MultipartFile multipartFile = files[i];

                LinkedMultiValueMap<String, Object> multipartRequest = new LinkedMultiValueMap<>();

                Path path = Paths.get(System.getProperty("java.io.tmpdir")).resolve(multipartFile.getOriginalFilename());
              //Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                multipartRequest.add("image", new FileSystemResource(path.toString()));

                HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(multipartRequest, commonHeader);

                CompletableFuture<ResponseEntity<RentOfferPostImageResponse>> completableFuture =
                        ua.com.gup.rent.util.RentCompletableFutureUtil.toCompletableFuture(asyncRestTemplate.postForEntity(uriComponents.toUri(), requestEntity, RentOfferPostImageResponse.class));

                completableFuture.whenCompleteAsync(((responseEntity, throwable) -> {
                    RentOfferPostImageResponse response = responseEntity.getBody();
                    RentOfferImageInfo info = new RentOfferImageInfo();
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
            rentOffer.setImages(images);
        }
        getRepository().create(rentOffer);
    }

    @Override
    public void update(RentOfferUpdateDTO rentOfferUpdateDTO) {
        getRepository().update(null);
    }
    @Override
    public void deleteById(String rentObjectId) {
        getRepository().deleteById(rentObjectId);
    }

    @Override
    public List<RentOfferViewShortDTO> findAll() {
        List<RentOffer> rentOffers = getRepository().findAll();
        return rentOffers.stream().map(rentOffer -> rentOfferMapper.fromRentObjectToShortDTO(rentOffer)).collect(Collectors.toList());
    }

    @Override
    public Optional<RentOfferViewDetailsDTO> findOne(String id) {
        return null;
    }

    @Override
    public RentOfferViewDetailsDTO save(RentOfferModerationReportDTO offerModerationReportDTO) {
        return null;
    }

    @Override
    public RentOfferViewDetailsDTO save(RentOfferCreateDTO rentOfferCreateDTO) {
        return null;
    }

    @Override
    protected RentOfferRepository getRepository() {
        return (RentOfferRepository) super.getRepository();
    }

}

