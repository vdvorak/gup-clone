package ua.com.gup.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.common.dto.offer.CommonModerationReportDTO;
import ua.com.gup.common.model.category.attribute.*;
import ua.com.gup.common.model.mongo.offer.Lands;
import ua.com.gup.common.model.mongo.offer.OfferModerationReport;
import ua.com.gup.common.service.mapper.ImageStorageMapper;
import ua.com.gup.dto.category.tree.CategoryAttributeDTO;
import ua.com.gup.dto.category.tree.CategoryAttributeValueDTO;
import ua.com.gup.dto.offer.*;
import ua.com.gup.dto.offer.statistic.OfferStatisticByDateDTO;
import ua.com.gup.dto.offer.statistic.OfferStatisticDTO;
import ua.com.gup.dto.offer.view.*;
import ua.com.gup.mongo.composition.domain.offer.Offer;
import ua.com.gup.mongo.model.statistic.OfferStatistic;
import ua.com.gup.service.category.CategoryService;
import ua.com.gup.service.category.attribute.CategoryAttributeService;
import ua.com.gup.util.DateUtil;
import ua.com.gup.util.security.SecurityUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class OfferMapper {

    @Autowired
    private PriceMapper priceMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private OfferContactInfoMapper contactInfoMapper;

    @Autowired
    private OfferAuthorMapper authorMapper;

    @Autowired
    private OfferCategoryMapper offerCategoryMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryAttributeService categoryAttributeService;
    @Autowired
    private ImageStorageMapper offerImageMapper;

    private static final int PRICE_ATTRIBUTE_CODE = 1;

    public Offer offerCreateDTOToOffer(OfferCreateDTO offerCreateDTO) {
        Offer offer = new Offer();
        fromOfferCreateDTOToOffer(offerCreateDTO, offer);
        return offer;
    }

    public void offerUpdateDTOToOffer(OfferUpdateDTO source, Offer target) {
        fromOfferCreateDTOToOffer(source, target);
        if (source.getCategory() != null) {
            target.setCategories(categoryService.getOfferCategoriesIds(source.getCategory()));
        }
        if (source.getAddress() != null) {
            target.setAddress(addressMapper.addressDTOToAddress(source.getAddress()));
        }

        if (source.getYoutubeVideoId() != null) {
            target.setYoutubeVideoId(source.getYoutubeVideoId());
        }
        if (source.getContactInfo() != null) {
            target.setContactInfo(contactInfoMapper.contactInfoDTOToContactInfo(source.getContactInfo()));
        }
    }

    public void offerModeratorDTOToOffer(CommonModerationReportDTO source, Offer target) {
        if (source.getCategory() != null) {
            target.setCategories(categoryService.getOfferCategoriesIds(source.getCategory()));
        }
        OfferModerationReport moderationReport = new OfferModerationReport();
        moderationReport.setDescription(source.getDescription());
        moderationReport.setRefusalReasons(source.getRefusalReasons());
        moderationReport.setModeratorId(SecurityUtils.getCurrentUserId());
        moderationReport.setLastModifiedDate(ZonedDateTime.now());
        target.setLastOfferModerationReport(moderationReport);
    }

    public OfferViewShortDTO offerToOfferShortDTO(Offer offer) {
        OfferViewShortDTO offerViewShortDTO = new OfferViewShortDTO();
        fromOfferToOfferViewShortDTO(offer, offerViewShortDTO);
        return offerViewShortDTO;
    }

    public OfferViewCoordinatesDTO offerToOfferCoordinatesDTO(Offer offer) {
        OfferViewCoordinatesDTO coordinatesDTO = null;


        if (offer.getAddress() != null
                && offer.getAddress().getLat() != null
                && offer.getAddress().getLng() != null) {
            coordinatesDTO = new OfferViewCoordinatesDTO(offer.getSeoUrl(), new BigDecimal[]{offer.getAddress().getLat(), offer.getAddress().getLng()});
        }
        return coordinatesDTO;
    }

    public OfferViewShortWithModerationReportDTO offerToOfferViewShortWithModerationReportDTO(Offer offer) {
        OfferViewShortWithModerationReportDTO offerViewShortWithModerationReportDTO = new OfferViewShortWithModerationReportDTO();
        fromOfferToOfferViewShortDTO(offer, offerViewShortWithModerationReportDTO);
        offerViewShortWithModerationReportDTO.setModerationReport(offer.getLastOfferModerationReport());
        return offerViewShortWithModerationReportDTO;
    }


    public OfferViewDetailsDTO offerToOfferDetailsDTO(Offer offer) {
        OfferViewDetailsDTO offerViewDetailsDTO = new OfferViewDetailsDTO();
        fromOfferToOfferViewBaseDTO(offer, offerViewDetailsDTO);
        offerViewDetailsDTO.setAttrs(offer.getAttrs());
        offerViewDetailsDTO.setMultiAttrs(offer.getMultiAttrs());
        offerViewDetailsDTO.setNumAttrs(offer.getNumAttrs());
        offerViewDetailsDTO.setBoolAttrs(offer.getBoolAttrs());
        offerViewDetailsDTO.setStatus(offer.getStatus());
        offerViewDetailsDTO.setAddress(addressMapper.addressToAddressDTO(offer.getAddress()));
        if (offer.getPrice() != null) {
            OfferPriceDTO priceDTO = new OfferPriceDTO();
            priceMapper.moneyToMoneyDTO(offer.getPrice(), priceDTO);
            offerViewDetailsDTO.setPrice(priceDTO);
        }
        offerViewDetailsDTO.setYoutubeVideoId(offer.getYoutubeVideoId());

        //owner ? doesn't hide phone number : hide phone number
        boolean hidePhoneNumber = !SecurityUtils.isAuthenticated() || !(SecurityUtils.getCurrentUserId().equals(offer.getAuthorId()));
        offerViewDetailsDTO.setContactInfo(contactInfoMapper.contactInfoToContactInfoDTO(offer.getContactInfo(), hidePhoneNumber));

        offerViewDetailsDTO.setOfferStatistic(new OfferStatisticDTO(offer.getStatistic().getTotalOfferViewsCount(), offer.getStatistic().getTotalOfferPhonesViewsCount()));
        if (offer.getLands() != null) {
            offerViewDetailsDTO.setLands(transformLandsToOfferLandsDTO(offer.getLands()));
        }

        return offerViewDetailsDTO;
    }

    private void fromOfferToOfferViewShortDTO(Offer source, OfferViewShortDTO target) {
        fromOfferToOfferViewBaseDTO(source, target);
        target.setAddress(addressMapper.addressToAddressDTO(source.getAddress()));
        if (source.getPrice() != null) {
            OfferPriceShortDTO priceDTO = new OfferPriceShortDTO();
            priceMapper.moneyToMoneyDTO(source.getPrice(), priceDTO);
            Optional<OfferCategorySingleAttributeValue> collect = source.getAttrs().values().stream().filter(a -> a.getCode() == PRICE_ATTRIBUTE_CODE).findFirst();
            if (collect.isPresent()) {
                OfferCategorySingleAttributeValue priceAttributes = collect.get();
                priceDTO.setSelected(priceAttributes.getSelected());
                priceDTO.setTitle(priceAttributes.getTitle());
            }
            target.setPrice(priceDTO);
        }
        if (source.getLands() != null) {
            target.setLands(transformLandsToOfferLandsDTO(source.getLands()));
        }
    }

    public List<OfferStatisticByDateDTO> offerStatisticToOfferStatisticDTO(OfferStatistic viewStatistic, LocalDate offerDtCreate, LocalDate dateStart, LocalDate dateEnd) {
        List<LocalDate> dates = DateUtil.getDateRangeBetweenDates(dateStart, dateEnd, true);
        List<OfferStatisticByDateDTO> statistic = new ArrayList<>();
        for (LocalDate date : dates) {
            OfferStatisticByDateDTO statisticByDateDTO = new OfferStatisticByDateDTO();
            statisticByDateDTO.setDate(date.toString());
            statisticByDateDTO.setOfferViews(viewStatistic.getOfferViewsCountByDate(offerDtCreate, date));
            statisticByDateDTO.setOfferPhonesViews(viewStatistic.getOfferPhonesViewsCountByDate(offerDtCreate, date));
            statistic.add(statisticByDateDTO);
        }
        return statistic;
    }


    private void fromOfferToOfferViewBaseDTO(Offer source, OfferViewBaseDTO target) {
        target.setId(source.getId());
        target.setLastModifiedDate(source.getLastModifiedDate());
        target.setAuthor(authorMapper.createAuthorDTO(source.getAuthorId()));
        if (source.getCategories() != null) {
            target.setCategories(offerCategoryMapper.offerCategoriesByCategoriesIds(source.getCategories()));
        }
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());

        offerImageMapper.toListDTO(source.getImages(), target.getImages());

        target.setSeoUrl(source.getSeoUrl());

    }

    private void fromOfferCreateDTOToOffer(OfferCreateDTO source, Offer target) {

        if (source.getCategory() != null) {
            target.setCategories(categoryService.getOfferCategoriesIds(source.getCategory()));
        }

        if (source.getTitle() != null) {
            target.setTitle(source.getTitle());
        }

        if (source.getDescription() != null) {
            target.setDescription(source.getDescription());
        }

        if (source.getAddress() != null) {
            target.setAddress(addressMapper.addressDTOToAddress(source.getAddress()));
        }

        if (source.getPrice() != null) {
            target.setPrice(priceMapper.moneyDTOToMoney(source.getPrice()));
        }

        if (source.getLands() != null) {
            target.setLands(transformOfferLandsDTOToLands(source.getLands()));
        }

        if (source.getYoutubeVideoId() != null) {
            target.setYoutubeVideoId(source.getYoutubeVideoId());
        }

        if (source.getContactInfo() != null) {
            target.setContactInfo(contactInfoMapper.contactInfoDTOToContactInfo(source.getContactInfo()));
        }
        if (source.getCategory() != null) {

            final SortedSet<CategoryAttributeDTO> categoryAttributeDTOS = categoryAttributeService.findAllCategoryAttributeDTO().get(source.getCategory());

            final Map<String, CategoryAttributeDTO> categoryAttributeDTOMap = categoryAttributeDTOS.stream().collect(Collectors.toMap(CategoryAttributeDTO::getKey, Function.identity()));

            if (source.getAttrs() != null) {

                LinkedHashMap<String, OfferCategorySingleAttributeValue> attrs = new LinkedHashMap<>();

                for (String key : source.getAttrs().keySet()) {

                    final String value = source.getAttrs().get(key);
                    final CategoryAttributeDTO categoryAttributeDTO = categoryAttributeDTOMap.get(key);
                    OfferCategorySingleAttributeValue attributeValue = new OfferCategorySingleAttributeValue();
                    fromCategoryAttributeDTOToOfferCategoryAttributeValue(categoryAttributeDTO, attributeValue);
                    OfferCategoryAttributeValue selected = new OfferCategoryAttributeValue();
                    selected.setKey(value);
                    for (CategoryAttributeValueDTO valueDTO : categoryAttributeDTO.getValues()) {
                        if (value.equals(valueDTO.getKey())) {
                            selected.setTitle(valueDTO.getTitle());
                        }
                    }
                    attributeValue.setSelected(selected);
                    attrs.put(key, attributeValue);
                }
                target.setAttrs(attrs);
            }

            if (source.getMultiAttrs() != null) {
                LinkedHashMap<String, OfferCategoryMultiAttributeValue> multiAttrs = new LinkedHashMap<>();
                for (String key : source.getMultiAttrs().keySet()) {
                    OfferCategoryMultiAttributeValue attributeValue = new OfferCategoryMultiAttributeValue();
                    final CategoryAttributeDTO categoryAttributeDTO = categoryAttributeDTOMap.get(key);
                    fromCategoryAttributeDTOToOfferCategoryAttributeValue(categoryAttributeDTO, attributeValue);
                    final String[] values = source.getMultiAttrs().get(key).split(",");
                    LinkedHashSet<OfferCategoryAttributeValue> selected = new LinkedHashSet<>();
                    for (String value : values) {
                        OfferCategoryAttributeValue selectedItem = new OfferCategoryAttributeValue();
                        selectedItem.setKey(value);
                        for (CategoryAttributeValueDTO valueDTO : categoryAttributeDTO.getValues()) {
                            if (value.equals(valueDTO.getKey())) {
                                selectedItem.setTitle(valueDTO.getTitle());
                            }
                        }
                        selected.add(selectedItem);
                    }
                    attributeValue.setSelected(selected);
                    multiAttrs.put(key, attributeValue);
                }
                target.setMultiAttrs(multiAttrs);
            }

            if (source.getNumAttrs() != null) {
                LinkedHashMap<String, OfferCategoryNumericAttributeValue> numericAttrs = new LinkedHashMap<>();
                for (String key : source.getNumAttrs().keySet()) {
                    OfferCategoryNumericAttributeValue attributeValue = new OfferCategoryNumericAttributeValue();
                    final CategoryAttributeDTO categoryAttributeDTO = categoryAttributeDTOMap.get(key);
                    fromCategoryAttributeDTOToOfferCategoryAttributeValue(categoryAttributeDTO, attributeValue);
                    attributeValue.setSelected(source.getNumAttrs().get(key));
                    attributeValue.setSelectedDouble(source.getNumAttrs().get(key).doubleValue());
                    numericAttrs.put(key, attributeValue);
                }
                target.setNumAttrs(numericAttrs);
            }

            if (source.getBoolAttrs() != null) {
                LinkedHashMap<String, OfferCategoryBoolAttributeValue> boolAttrs = new LinkedHashMap<>();
                for (String key : source.getBoolAttrs().keySet()) {
                    OfferCategoryBoolAttributeValue attributeValue = new OfferCategoryBoolAttributeValue();
                    final CategoryAttributeDTO categoryAttributeDTO = categoryAttributeDTOMap.get(key);
                    fromCategoryAttributeDTOToOfferCategoryAttributeValue(categoryAttributeDTO, attributeValue);
                    attributeValue.setSelected(source.getBoolAttrs().get(key));
                    boolAttrs.put(key, attributeValue);
                }
                target.setBoolAttrs(boolAttrs);
            }
        }
    }

    private Lands transformOfferLandsDTOToLands(OfferLandsDTO offerLandsDto) {
        Lands lands = null;
        if (offerLandsDto != null) {
            lands = new Lands(offerLandsDto.getCadnums(), offerLandsDto.getPolygons());
        }
        return lands;
    }

    private OfferLandsDTO transformLandsToOfferLandsDTO(Lands lands) {
        OfferLandsDTO offerLandsDTOS = null;
        if (lands != null) {
            offerLandsDTOS = new OfferLandsDTO(lands.getCadnums(), lands.getPolygons());
        }
        return offerLandsDTOS;

    }

    private void fromCategoryAttributeDTOToOfferCategoryAttributeValue(CategoryAttributeDTO source, OfferCategoryAttributeBaseValue target) {
        target.setCode(source.getCode());
        target.setTitle(source.getTitle());
        target.setUnit(source.getUnit());
        target.setType(source.getType());
    }
}
