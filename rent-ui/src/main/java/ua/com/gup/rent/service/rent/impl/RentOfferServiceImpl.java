package ua.com.gup.rent.service.rent.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
import ua.com.gup.rent.filter.RentOfferFilter;
import ua.com.gup.rent.mapper.RentOfferMapper;
import ua.com.gup.rent.model.enumeration.RentOfferImageSizeType;
import ua.com.gup.rent.model.enumeration.RentOfferStatus;
import ua.com.gup.rent.model.file.RentOfferFileWrapper;
import ua.com.gup.rent.model.image.RentOfferImageInfo;
import ua.com.gup.rent.model.mongo.rent.RentOffer;
import ua.com.gup.rent.repository.profile.RentOfferProfileRepository;
import ua.com.gup.rent.repository.rent.RentOfferRepository;
import ua.com.gup.rent.repository.rent.offer.RentOfferRepositoryCRUD;
import ua.com.gup.rent.repository.rent.offer.RentOfferRepositoryCustom;
import ua.com.gup.rent.service.abstracted.RentOfferGenericServiceImpl;
import ua.com.gup.rent.service.dto.rent.RentOfferModerationReportDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferCategoryCountDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferCreateDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferUpdateDTO;
import ua.com.gup.rent.service.dto.rent.offer.statistic.RentOfferStatisticByDateDTO;
import ua.com.gup.rent.service.dto.rent.offer.view.RentOfferViewCoordinatesDTO;
import ua.com.gup.rent.service.dto.rent.offer.view.RentOfferViewDetailsDTO;
import ua.com.gup.rent.service.dto.rent.offer.view.RentOfferViewShortDTO;
import ua.com.gup.rent.service.dto.rent.offer.view.RentOfferViewShortWithModerationReportDTO;
import ua.com.gup.rent.service.rent.RentOfferService;
import ua.com.gup.rent.service.rent.image.RentOfferPostImageResponse;
import ua.com.gup.rent.service.sequence.RentSequenceService;
import ua.com.gup.rent.util.RentOfferSEOFriendlyUrlUtil;
import ua.com.gup.rent.util.security.RentSecurityUtils;

import javax.annotation.PostConstruct;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


@Service
public class RentOfferServiceImpl extends RentOfferGenericServiceImpl<RentOfferDTO, String> implements RentOfferService {

    private final Logger log = LoggerFactory.getLogger(RentOfferServiceImpl.class);
    private static final String RENT_OFFER_SEQUENCE_ID = "rent_offer_sequence";

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

    @Autowired
    private RentOfferProfileRepository profileRepository;

    @Autowired
    private RentOfferRepositoryCustom rentOfferRepositoryCustom;

    @Autowired
    private RentOfferRepositoryCRUD rentOfferRepositoryCRUD;

    @Autowired
    private RentSequenceService rentSequenceService;

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
        log.debug("Request to save Offer : {}", rentOfferCreateDTO);
        String seoURL = generateUniqueSeoUrl(rentOfferCreateDTO.getTitle());
       // saveOfferImages(null, offerCreateDTO.getImages(), seoURL);
        RentOffer offer = rentOfferMapper.offerCreateDTOToOffer(rentOfferCreateDTO);
        offer.setStatus(RentOfferStatus.ON_MODERATION);
        offer.setSeoUrl(seoURL);
        String userID = RentSecurityUtils.getCurrentUserId();
       // offer.setLastModifiedBy(userID);
        offer.setAuthorId(userID);
        offer = rentOfferRepositoryCRUD.save(offer);
        RentOfferViewDetailsDTO result = rentOfferMapper.offerToOfferDetailsDTO(offer);
        return result;
    }

    @Override
    public RentOfferViewDetailsDTO save(RentOfferUpdateDTO offerUpdateDTO) {
        return null;
    }

    @Override
    public Page<RentOfferViewShortDTO> findAll(RentOfferFilter offerFilter, Pageable pageable) {
        //set authorId by exists publicId for OfferFilter
        if (offerFilter.getRentOfferAuthorFilter() != null) {
            if (offerFilter.getRentOfferAuthorFilter().getPublicId() != null && offerFilter.getRentOfferAuthorFilter().getAuthorId() == null) {
                offerFilter.getRentOfferAuthorFilter().setAuthorId(profileRepository.findByPublicId(offerFilter.getRentOfferAuthorFilter().getPublicId().trim()).getId());
            }
        }
        log.debug("Request to get all Offers by filter  {} ", offerFilter);
        //calculatePriceInBaseCurrency(offerFilter.getPrice());
        long count = rentOfferRepositoryCustom.countByFilter(offerFilter, RentOfferStatus.ACTIVE);
        List<RentOffer> offers = Collections.EMPTY_LIST;
        if (count > 0) {
            offers = rentOfferRepositoryCustom.findByFilter(offerFilter, RentOfferStatus.ACTIVE, pageable);
        }
        Page<RentOffer> result = new PageImpl<>(offers, pageable, count);
        return result.map(offer -> rentOfferMapper.offerToOfferShortDTO(offer));
    }

    @Override
    public List<RentOfferViewCoordinatesDTO> findCoordinatesByFilter(RentOfferFilter offerFilter, Pageable pageable) {
        return null;
    }

    @Override
    public Page<RentOfferViewShortWithModerationReportDTO> findAllByStatusAndUserId(RentOfferStatus status, String authorId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<RentOfferViewShortWithModerationReportDTO> findAllByStatusAndUserPublicId(RentOfferStatus status, String userPublicId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<RentOfferViewShortWithModerationReportDTO> findAllByStatus(RentOfferStatus status, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<RentOfferViewDetailsDTO> findOneBySeoUrl(String seoUrl) {
        return null;
    }

    @Override
    public Page<RentOfferViewShortDTO> findRelevantBySeoUrl(String seoUrl, Pageable pageable) {
        return null;
    }

    @Override
    public void incrementPhoneViews(String id) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public boolean exists(String id) {
        return false;
    }

    @Override
    public boolean hasPermissionForUpdate(String offerId, String authrorId) {
        return false;
    }

    @Override
    public void updateActiveOffersBasePrice() {

    }

    @Override
    public Optional<RentOfferViewDetailsDTO> updateStatus(String id, RentOfferStatus status) {
        return null;
    }

    @Override
    public Boolean isCanUpdateStatus(String id, RentOfferStatus status) {
        return null;
    }

    @Override
    public List<RentOfferCategoryCountDTO> searchCategoriesByString(String string, int page, int size) {
        return null;
    }

    @Override
    public Optional<List<RentOfferStatisticByDateDTO>> findOfferStatisticBySeoUrlAndDateRange(String seoUrl, LocalDate dateStart, LocalDate dateEnd) {
        return null;
    }

    @Override
    public Optional<RentOfferViewDetailsDTO> findOfferByIdAndAuthorId(String offerId, String authorId) {
        return null;
    }

    @Override
    public Collection<String> getOfferContactInfoPhoneNumbersById(String offerId) {
        return null;
    }

    @Override
    public RentOfferFileWrapper findImageByIdAndSizeType(String id, RentOfferImageSizeType sizeType) {
        return null;
    }

    @Override
    protected RentOfferRepository getRepository() {
        return (RentOfferRepository) super.getRepository();
    }

    private String generateUniqueSeoUrl(String title) {
        // index number in 36 radix
        return RentOfferSEOFriendlyUrlUtil.generateSEOFriendlyUrl(title + "-" + Long.toString(rentSequenceService.getNextSequenceValue(RENT_OFFER_SEQUENCE_ID), 36));
    }
}

