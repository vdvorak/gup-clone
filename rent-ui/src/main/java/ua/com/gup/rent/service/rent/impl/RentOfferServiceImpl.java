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
import ua.com.gup.rent.filter.RentOfferMoneyFilter;
import ua.com.gup.rent.mapper.RentOfferMapper;
import ua.com.gup.rent.model.enumeration.RentOfferCurrency;
import ua.com.gup.rent.model.enumeration.RentOfferImageSizeType;
import ua.com.gup.rent.model.enumeration.RentOfferStatus;
import ua.com.gup.rent.model.file.RentOfferFileWrapper;
import ua.com.gup.rent.model.image.RentOfferImageInfo;
import ua.com.gup.rent.model.mongo.category.RentOfferCategory;
import ua.com.gup.rent.model.mongo.rent.RentOffer;
import ua.com.gup.rent.model.mongo.user.RentOfferProfile;
import ua.com.gup.rent.repository.profile.RentOfferProfileRepository;
import ua.com.gup.rent.repository.rent.offer.RentOfferRepository;
import ua.com.gup.rent.repository.rent.offer.RentOfferRepositoryCustom;
import ua.com.gup.rent.service.abstracted.RentOfferGenericServiceImpl;
import ua.com.gup.rent.service.category.RentOfferCategoryService;
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
import ua.com.gup.rent.util.RentCompletableFutureUtil;
import ua.com.gup.rent.util.RentOfferSEOFriendlyUrlUtil;
import ua.com.gup.rent.util.security.RentSecurityUtils;

import javax.annotation.PostConstruct;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


@Service
public class RentOfferServiceImpl extends RentOfferGenericServiceImpl<RentOfferDTO, String> implements RentOfferService {

    private static final String RENT_OFFER_SEQUENCE_ID = "rent_offer_sequence";
    private final Logger log = LoggerFactory.getLogger(RentOfferServiceImpl.class);
    @Autowired
    private Environment e;

    @Autowired
    private RentOfferMapper offerMapper;

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
    private RentOfferRepositoryCustom offerRepositoryCustom;

    @Autowired
    private RentOfferRepository offerRepository;

    @Autowired
    private RentSequenceService sequenceService;

    @Autowired
    private RentOfferCategoryService categoryService;

    public RentOfferServiceImpl(@Autowired ua.com.gup.rent.repository.rent.RentOfferRepository rentOfferRepository) {
        super(rentOfferRepository);
    }

    @PostConstruct
    public void initialize() {

        uriComponentsBuilder = UriComponentsBuilder.newInstance().scheme(e.getRequiredProperty("storage.host.scheme"))
                .host(e.getRequiredProperty("storage.host.address"))
                .port(e.getRequiredProperty("storage.host.port"))
                .path(e.getRequiredProperty("storage.host.context-path"))
                .path("/api");

    }

    private void handleMultipartFile() {

    }

    //   @Override
    private void create(RentOfferCreateDTO target) {
        RentOffer rentOffer = offerMapper.fromCreateDTOToRentObject(target);
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

                CompletableFuture<ResponseEntity<RentOfferPostImageResponse>> completableFuture = RentCompletableFutureUtil.toCompletableFuture(asyncRestTemplate.postForEntity(uriComponents.toUri(), requestEntity, RentOfferPostImageResponse.class));

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

    //  @Override
    private void update(RentOfferUpdateDTO rentOfferUpdateDTO) {
        getRepository().update(null);
    }

    //  @Override
    private void deleteById(String rentObjectId) {
        getRepository().deleteById(rentObjectId);
    }

    //    @Override
    private List<RentOfferViewShortDTO> findAll() {
        List<RentOffer> rentOffers = getRepository().findAll();
        return rentOffers.stream().map(rentOffer -> offerMapper.fromRentObjectToShortDTO(rentOffer)).collect(Collectors.toList());
    }

//-----------------------from UI OFFER  copy past -----------------------------------------------------------------------------------------

    @Override
    public RentOfferViewDetailsDTO save(RentOfferCreateDTO rentOfferCreateDTO) {
        log.debug("Request to save Offer : {}", rentOfferCreateDTO);
        String seoURL = generateUniqueSeoUrl(rentOfferCreateDTO.getTitle());

        //todo vdvorak  save image
        // saveOfferImages(null, offerCreateDTO.getImages(), seoURL);

        RentOffer offer = offerMapper.offerCreateDTOToOffer(rentOfferCreateDTO);
        offer.setStatus(RentOfferStatus.ON_MODERATION);
        offer.setSeoUrl(seoURL);
        String userID = RentSecurityUtils.getCurrentUserId();
        offer.setLastModifiedBy(userID);
        offer.setAuthorId(userID);
        offer = offerRepository.save(offer);
        RentOfferViewDetailsDTO result = offerMapper.offerToOfferDetailsDTO(offer);
        return result;
    }

    @Override
    public RentOfferViewDetailsDTO save(RentOfferUpdateDTO offerUpdateDTO) {
        log.debug("Request to update  Rent Offer : {}", offerUpdateDTO);
        RentOffer offer = offerRepository.findOne(offerUpdateDTO.getId());

        //todo vdvorak  save image
        saveOfferImages(/*offer.getImages()*/null, /*offerUpdateDTO.getImages()*/null, /*offer.getSeoUrl()*/null);

        offerMapper.offerUpdateDTOToOffer(offerUpdateDTO, offer);
        offer.setLastModifiedBy(RentSecurityUtils.getCurrentUserId());
        offer.setLastModifiedDate(LocalDateTime.now());
        // on moderation if fields was changed and moderation is needed or last moderation is refused - moderation any way
        if (isNeededModeration(offerUpdateDTO) || offer.getLastOfferModerationReport().isRefused()) {
            offer.setStatus(RentOfferStatus.ON_MODERATION);
        } else {
            offer.setStatus(RentOfferStatus.ACTIVE);
        }
        offer = offerRepository.save(offer);
        RentOfferViewDetailsDTO result = offerMapper.offerToOfferDetailsDTO(offer);
        return result;
    }

    @Override
    public RentOfferViewDetailsDTO save(RentOfferModerationReportDTO offerModerationReportDTO) {
        log.debug("Request to save Rent Offer modified by moderator: {}", offerModerationReportDTO);
        RentOffer offer = offerRepository.findOne(offerModerationReportDTO.getId());
        offerMapper.offerModeratorDTOToOffer(offerModerationReportDTO, offer);
        if (offer.getLastOfferModerationReport().isRefused()) {
            offer.setStatus(RentOfferStatus.REJECTED);
        } else {
            offer.setStatus(RentOfferStatus.ACTIVE);
        }
        offer = offerRepository.save(offer);
        RentOfferViewDetailsDTO result = offerMapper.offerToOfferDetailsDTO(offer);
        return result;
    }

    @Override
    public Optional<RentOfferViewDetailsDTO> findOne(String id) {
        log.debug("Request to get Offer : {}", id);
        RentOffer offer = offerRepository.findOne(id);
        if (offer == null) {
            return Optional.empty();
        }
        return Optional.of(offer).map(o -> offerMapper.offerToOfferDetailsDTO(o));
    }

    @Override
    public Page<RentOfferViewShortDTO> findAll(RentOfferFilter offerFilter, Pageable pageable) {
        if (offerFilter.getRentOfferAuthorFilter() != null) {
            if (offerFilter.getRentOfferAuthorFilter().getPublicId() != null && offerFilter.getRentOfferAuthorFilter().getAuthorId() == null) {
                offerFilter.getRentOfferAuthorFilter().setAuthorId(profileRepository.findByPublicId(offerFilter.getRentOfferAuthorFilter().getPublicId().trim()).getId());
            }
        }
        log.debug("Request to get all Rent Offers by filter  {} ", offerFilter);

        //todo vdvorak add
        calculatePriceInBaseCurrency(offerFilter.getPrice());

        long count = offerRepositoryCustom.countByFilter(offerFilter, RentOfferStatus.ACTIVE);
        List<RentOffer> offers = Collections.EMPTY_LIST;
        if (count > 0) {
            offers = offerRepositoryCustom.findByFilter(offerFilter, RentOfferStatus.ACTIVE, pageable);
        }
        Page<RentOffer> result = new PageImpl<>(offers, pageable, count);
        return result.map(offer -> offerMapper.offerToOfferShortDTO(offer));
    }

    @Override
    public List<RentOfferViewCoordinatesDTO> findCoordinatesByFilter(RentOfferFilter offerFilter, Pageable pageable) {
        return null;
    }

    @Override
    public Page<RentOfferViewShortWithModerationReportDTO> findAllByStatusAndUserId(RentOfferStatus status, String authorId, Pageable pageable) {
        log.debug("Request to get all Rent Offers by status = {} and authorId = {}", status, authorId);
        RentOfferProfile profile = profileRepository.findById(authorId);
        if (profile == null) {
            return new PageImpl<RentOfferViewShortWithModerationReportDTO>(Collections.EMPTY_LIST);
        }
        Page<RentOffer> result = offerRepository.findAllByStatusAndAuthorId(status, profile.getId(), pageable);
        return result.map(offer -> offerMapper.offerToOfferViewShortWithModerationReportDTO(offer));
    }

    @Override
    public Page<RentOfferViewShortWithModerationReportDTO> findAllByStatusAndUserPublicId(RentOfferStatus status, String userPublicId, Pageable pageable) {
        log.debug("Request to get all Offers by status = {} and userPublicId = {}", status, userPublicId);
        RentOfferProfile profile = profileRepository.findByPublicId(userPublicId);
        if (profile == null) {
            return new PageImpl<RentOfferViewShortWithModerationReportDTO>(Collections.EMPTY_LIST);
        }
        Page<RentOffer> result = offerRepository.findAllByStatusAndAuthorId(status, profile.getId(), pageable);
        return result.map(offer -> offerMapper.offerToOfferViewShortWithModerationReportDTO(offer));
    }

    @Override
    public Page<RentOfferViewShortWithModerationReportDTO> findAllByStatus(RentOfferStatus status, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<RentOfferViewDetailsDTO> findOneBySeoUrl(String seoUrl) {
        log.debug("Request to get Rent Offer : {}", seoUrl);
        Optional<RentOffer> offer = offerRepository.findOneBySeoUrl(seoUrl);
        if (offer.isPresent()) {
            final RentOffer o = offer.get();
            o.incrementView(true, false);
            offerRepository.save(o);
            Set<RentOfferStatus> statuses = new HashSet<>();
            statuses.addAll(Arrays.asList(RentOfferStatus.ACTIVE, RentOfferStatus.DEACTIVATED, RentOfferStatus.REJECTED, RentOfferStatus.ON_MODERATION));
            if (!statuses.contains(o.getStatus())) {
                offer = Optional.empty();
            }
        }
        return offer.map(o -> offerMapper.offerToOfferDetailsDTO(o));
    }

    @Override
    public Page<RentOfferViewShortDTO> findRelevantBySeoUrl(String seoUrl, Pageable pageable) {
        Optional<RentOffer> offerOptional = offerRepository.findOneBySeoUrl(seoUrl);
        if (offerOptional.isPresent()) {
            final RentOffer offer = offerOptional.get();
            StringBuilder search = new StringBuilder();

            List<RentOfferCategory> categories = categoryService.findByCodeInOrderByCodeAsc(offer.getCategories());
            for (RentOfferCategory category : categories) {
                final Map<String, String> titles = category.getTitle();
                for (String lang : titles.keySet()) {
                    search.append(titles.get(lang) + " ");
                    break;
                }
            }

            search.append(offer.getTitle());
            RentOfferFilter filter = new RentOfferFilter();
            filter.setQuery(search.toString());
            Page<RentOffer> result = new PageImpl<>(offerRepositoryCustom.findByFilter(filter, RentOfferStatus.ACTIVE, offer.getId(), pageable));
            return result.map(o -> offerMapper.offerToOfferShortDTO(o));

        }
        return new PageImpl<>(new ArrayList<>());
    }

    @Override
    public void incrementPhoneViews(String id) {
        RentOffer offer = offerRepository.findOne(id);
        offer.incrementView(false, true);
        offerRepository.save(offer);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Offer : {}", id);
        RentOffer offer = offerRepository.findOne(id);
        offer.setStatus(RentOfferStatus.ARCHIVED);
        offerRepository.save(offer);
    }


    @Override
    public boolean exists(String id) {
        return offerRepository.exists(id);
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
        log.debug("Request to update update rent offer's status : {}", id);
        RentOffer offer = offerRepository.findOne(id);
        offer.setStatus(status);
        offer = offerRepository.save(offer);
        return Optional.of(offer).map(o -> offerMapper.offerToOfferDetailsDTO(o));
    }

    @Override
    public Boolean isCanUpdateStatus(String id, RentOfferStatus status) {
        RentOffer offer = offerRepository.findOne(id);
        Boolean fromStatus = (offer.getStatus() == RentOfferStatus.ACTIVE || offer.getStatus() == RentOfferStatus.DEACTIVATED);
        Boolean toStatus = (status == RentOfferStatus.ACTIVE || status == RentOfferStatus.DEACTIVATED || status == RentOfferStatus.ARCHIVED);
        return fromStatus && toStatus;
    }

    @Override
    public List<RentOfferCategoryCountDTO> searchCategoriesByString(String string, int page, int size) {
        return null;
    }

    @Override
    public Optional<List<RentOfferStatisticByDateDTO>> findOfferStatisticBySeoUrlAndDateRange(String seoUrl, LocalDate dateStart, LocalDate dateEnd) {
        Optional<RentOffer> offerOptional = offerRepository.findOneBySeoUrl(seoUrl);
        if (offerOptional.isPresent()) {
            RentOffer offer = offerOptional.get();
            return Optional.of(offer.getStatistic())
                    .map(o -> offerMapper.offerStatisticToOfferStatisticDTO(o, offer.getCreatedDate().toLocalDate(), dateStart, dateEnd));
        }
        return Optional.empty();
    }

    @Override
    public Optional<RentOfferViewDetailsDTO> findOfferByIdAndAuthorId(String offerId, String authorId) {
        return null;
    }

    @Override
    public Collection<String> getOfferContactInfoPhoneNumbersById(String offerId) {
        RentOffer offer = offerRepository.findOne(offerId);
        return offer.getContactInfo().getPhoneNumbers();
    }

    @Override
    public RentOfferFileWrapper findImageByIdAndSizeType(String id, RentOfferImageSizeType sizeType) {
        return null;
    }

    @Override
    protected ua.com.gup.rent.repository.rent.RentOfferRepository getRepository() {
        return (ua.com.gup.rent.repository.rent.RentOfferRepository) super.getRepository();
    }


//----------------------------------------------private method -----------------------------------------------------------------------------

    private String generateUniqueSeoUrl(String title) {
        // index number in 36 radix
        return RentOfferSEOFriendlyUrlUtil.generateSEOFriendlyUrl(title + "-" + Long.toString(sequenceService.getNextSequenceValue(RENT_OFFER_SEQUENCE_ID), 36));
    }

    private boolean isNeededModeration(RentOfferUpdateDTO offerUpdateDTO) {
        boolean result = false;

        result |= offerUpdateDTO.getCategory() != null;
        result |= offerUpdateDTO.getTitle() != null;
        result |= offerUpdateDTO.getDescription() != null;
        if (offerUpdateDTO.getImages() != null) {
            for (MultipartFile imageDTO : offerUpdateDTO.getImages()) {
                // result |= (imageDTO.getBase64Data() != null && imageDTO.getImageId() == null);
            }
        }
        result |= offerUpdateDTO.getAddress() != null;

        // price can be change without moderation

        result |= offerUpdateDTO.getContactInfo() != null;
        result |= offerUpdateDTO.getAttrs() != null;
        result |= offerUpdateDTO.getMultiAttrs() != null;
        result |= offerUpdateDTO.getNumAttrs() != null;
        result |= offerUpdateDTO.getBoolAttrs() != null;

        return result;
    }


    private void saveOfferImages(List<String> offerImageIds, String iamge /*List<OfferImageDTO> offerImageDTOS*/, String seoURL) {

    }

    private void calculatePriceInBaseCurrency(RentOfferMoneyFilter moneyFilter) {
        if (moneyFilter != null) {
            if (moneyFilter.getCurrency() != null) {
                final RentOfferCurrency currency = moneyFilter.getCurrency();
                if (moneyFilter.getFrom() != null) {
                    //final BigDecimal fromInBaseCurrency = currencyConverterService.convertToBaseCurrency(currency, new BigDecimal(moneyFilter.getFrom()));
                    //  moneyFilter.setFrom(fromInBaseCurrency.doubleValue());
                }
                if (moneyFilter.getTo() != null) {
                    //    final BigDecimal toInBaseCurrency = currencyConverterService.convertToBaseCurrency(currency, new BigDecimal(moneyFilter.getTo()));
                    //  moneyFilter.setTo(toInBaseCurrency.doubleValue());
                }
                //  moneyFilter.setCurrency(currencyConverterService.getBaseCurrency());
            }
        }
    }
}

