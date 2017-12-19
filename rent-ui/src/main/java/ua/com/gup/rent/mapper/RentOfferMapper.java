package ua.com.gup.rent.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.common.model.enumeration.CommonStatus;
import ua.com.gup.common.service.mapper.ImageStorageMapper;
import ua.com.gup.rent.model.mongo.rent.RentOffer;
import ua.com.gup.rent.model.rent.RentOfferLands;
import ua.com.gup.rent.model.rent.RentOfferModerationReport;
import ua.com.gup.rent.model.rent.RentOfferSettings;
import ua.com.gup.rent.model.rent.category.attribute.*;
import ua.com.gup.rent.model.rent.statistic.RentOfferStatistic;
import ua.com.gup.rent.service.category.RentOfferCategoryService;
import ua.com.gup.rent.service.category.attribute.RentOfferCategoryAttributeService;
import ua.com.gup.rent.service.dto.category.attribute.RentOfferCategoryAttributeDTO;
import ua.com.gup.rent.service.dto.category.attribute.RentOfferCategoryAttributeValueDTO;
import ua.com.gup.rent.service.dto.rent.RentOfferModerationReportDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferCreateDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferLandsDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferSettingsDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferUpdateDTO;
import ua.com.gup.rent.service.dto.rent.offer.price.RentOfferPriceDTO;
import ua.com.gup.rent.service.dto.rent.offer.price.RentOfferPriceShortDTO;
import ua.com.gup.rent.service.dto.rent.offer.statistic.RentOfferStatisticByDateDTO;
import ua.com.gup.rent.service.dto.rent.offer.statistic.RentOfferStatisticDTO;
import ua.com.gup.rent.service.dto.rent.offer.view.*;
import ua.com.gup.rent.util.RentDateUtil;
import ua.com.gup.rent.util.security.RentSecurityUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class RentOfferMapper {

    private static final int PRICE_ATTRIBUTE_CODE = 1;
    @Autowired
    private RentOfferPriceMapper priceMapper;
    @Autowired
    private RentOfferAddressMapper addressMapper;
    @Autowired
    private RentOfferContactInfoMapper contactInfoMapper;
    @Autowired
    private RentOfferAuthorMapper authorMapper;
    @Autowired
    private RentOfferCategoryMapper offerCategoryMapper;
    @Autowired
    private RentOfferCategoryService categoryService;
    @Autowired
    private RentOfferCategoryAttributeService categoryAttributeService;
    @Autowired
    private RentOfferPriceMapper rentOfferPriceMapper;
    @Autowired
    private ImageStorageMapper offerImageMapper;

    public RentOffer offerCreateDTOToOffer(RentOfferCreateDTO offerCreateDTO) {
        RentOffer offer = new RentOffer();
        fromOfferCreateDTOToOffer(offerCreateDTO, offer);
        offer.setTitle(offerCreateDTO.getTitle());
        offer.setDescription(offerCreateDTO.getDescription());
        offer.setStatus(CommonStatus.NEW);
        offer.setPrice(rentOfferPriceMapper.fromDTOToModel(offerCreateDTO.getPrice()));
        return offer;
    }

    public void offerUpdateDTOToOffer(RentOfferUpdateDTO source, RentOffer target) {
        fromOfferCreateDTOToOffer(source, target);
        if (source.getCategory() != null) {
            target.setCategories(categoryService.getRentOfferCategoriesIds(source.getCategory()));
        }
        //todo vdvorak
        if (source.getAddress() != null) {
            target.setAddress(addressMapper.addressDTOToAddress(source.getAddress()));
        }
        /*if (source.getImages() != null && source.getImages().size() > 0) {
            List<String> imageIds = new LinkedList<>();
            source.getImages().forEach(i -> imageIds.add(i.getImageId()));
            target.setImageIds(imageIds);
        }*/
        if (source.getYoutubeVideoId() != null) {
            target.setYoutubeVideoId(source.getYoutubeVideoId());
        }
        if (source.getContactInfo() != null) {
            target.setContactInfo(contactInfoMapper.contactInfoDTOToContactInfo(source.getContactInfo()));
        }
    }

    public void offerModeratorDTOToOffer(RentOfferModerationReportDTO source, RentOffer target) {
        if (source.getCategory() != null) {
            target.setCategories(categoryService.getRentOfferCategoriesIds(source.getCategory()));
        }
        RentOfferModerationReport moderationReport = new RentOfferModerationReport();
        moderationReport.setDescription(source.getDescription());
        moderationReport.setRefusalReasons(source.getRefusalReasons());
        moderationReport.setModeratorId(RentSecurityUtils.getCurrentUserId());
        moderationReport.setLastModifiedDate(ZonedDateTime.now());
        target.setLastOfferModerationReport(moderationReport);
    }

    public RentOfferViewShortDTO offerToOfferShortDTO(RentOffer offer) {
        RentOfferViewShortDTO offerViewShortDTO = new RentOfferViewShortDTO();
        fromOfferToOfferViewShortDTO(offer, offerViewShortDTO);
        return offerViewShortDTO;
    }

    public RentOfferViewCoordinatesDTO offerToOfferCoordinatesDTO(RentOffer offer) {
        RentOfferViewCoordinatesDTO coordinatesDTO = null;
        if (offer.getAddress() != null && offer.getAddress().getLat() != null && offer.getAddress().getLng() != null) {
            coordinatesDTO = new RentOfferViewCoordinatesDTO(offer.getSeoUrl(), new BigDecimal[]{offer.getAddress().getLat(), offer.getAddress().getLng()});
        }
        return coordinatesDTO;
    }

    public RentOfferViewShortWithModerationReportDTO offerToOfferViewShortWithModerationReportDTO(RentOffer offer) {
        RentOfferViewShortWithModerationReportDTO offerViewShortWithModerationReportDTO = new RentOfferViewShortWithModerationReportDTO();
        fromOfferToOfferViewShortDTO(offer, offerViewShortWithModerationReportDTO);
        offerViewShortWithModerationReportDTO.setModerationReport(offer.getLastOfferModerationReport());
        return offerViewShortWithModerationReportDTO;
    }


    public RentOfferViewDetailsDTO offerToOfferDetailsDTO(RentOffer offer) {
        RentOfferViewDetailsDTO offerViewDetailsDTO = new RentOfferViewDetailsDTO();
        fromOfferToOfferViewBaseDTO(offer, offerViewDetailsDTO);
        offerViewDetailsDTO.setAttrs(offer.getAttrs());
        offerViewDetailsDTO.setMultiAttrs(offer.getMultiAttrs());
        offerViewDetailsDTO.setNumAttrs(offer.getNumAttrs());
        offerViewDetailsDTO.setBoolAttrs(offer.getBoolAttrs());
        offerViewDetailsDTO.setStatus(offer.getStatus());

        offerViewDetailsDTO.setAddress(addressMapper.addressToAddressDTO(offer.getAddress()));
        if (offer.getPrice() != null) {
            RentOfferPriceDTO priceDTO = new RentOfferPriceDTO();
            //todo vdvorak check
            //  priceMapper.moneyToMoneyDTO(offer.getRentOfferPrice(), priceDTO);
            offerViewDetailsDTO.setPrice(priceDTO);
        }
        offerViewDetailsDTO.setYoutubeVideoId(offer.getYoutubeVideoId());

        //owner ? doesn't hide phone number : hide phone number
        boolean hidePhoneNumber = !RentSecurityUtils.isAuthenticated() || !(RentSecurityUtils.getCurrentUserId().equals(offer.getAuthorId()));
        offerViewDetailsDTO.setContactInfo(contactInfoMapper.contactInfoToContactInfoDTO(offer.getContactInfo(), hidePhoneNumber));

        offerViewDetailsDTO.setOfferStatistic(new RentOfferStatisticDTO(offer.getStatistic().getTotalOfferViewsCount(), offer.getStatistic().getTotalOfferPhonesViewsCount()));
        if (offer.getLands() != null) {
            offerViewDetailsDTO.setLands(transformLandsToOfferLandsDTO(offer.getLands()));
        }

        return offerViewDetailsDTO;
    }

    private void fromOfferToOfferViewShortDTO(RentOffer source, RentOfferViewShortDTO target) {
        fromOfferToOfferViewBaseDTO(source, target);
        target.setAddress(addressMapper.addressToAddressDTO(source.getAddress()));
        if (source.getPrice() != null) {
            RentOfferPriceShortDTO priceDTO = new RentOfferPriceShortDTO();
            priceMapper.fromModelToDTO(source.getPrice());
            Optional<RentOfferCategorySingleAttributeValue> collect = source.getAttrs().values().stream().filter(a -> a.getCode() == PRICE_ATTRIBUTE_CODE).findFirst();
            if (collect.isPresent()) {
                RentOfferCategorySingleAttributeValue priceAttributes = collect.get();
                priceDTO.setSelected(priceAttributes.getSelected());
                priceDTO.setTitle(priceAttributes.getTitle());
            }
            target.setPrice(priceDTO);
        }
        if (source.getLands() != null) {
            target.setLands(transformLandsToOfferLandsDTO(source.getLands()));
        }
    }

    public List<RentOfferStatisticByDateDTO> offerStatisticToOfferStatisticDTO(RentOfferStatistic viewStatistic, LocalDate offerDtCreate, LocalDate dateStart, LocalDate dateEnd) {
        List<LocalDate> dates = RentDateUtil.getDateRangeBetweenDates(dateStart, dateEnd, true);
        List<RentOfferStatisticByDateDTO> statistic = new ArrayList<>();
        for (LocalDate date : dates) {
            RentOfferStatisticByDateDTO statisticByDateDTO = new RentOfferStatisticByDateDTO();
            statisticByDateDTO.setDate(date.toString());
            statisticByDateDTO.setOfferViews(viewStatistic.getOfferViewsCountByDate(offerDtCreate, date));
            statisticByDateDTO.setOfferPhonesViews(viewStatistic.getOfferPhonesViewsCountByDate(offerDtCreate, date));
            statistic.add(statisticByDateDTO);
        }
        return statistic;
    }


    private void fromOfferToOfferViewBaseDTO(RentOffer source, RentOfferViewBaseDTO target) {

        if (source.getSettings() != null) {
            target.setSettings(
                    new RentOfferSettingsDTO(source.getSettings().getMinRentDays(),
                            source.getSettings().getMaxRentDays(),
                            source.getSettings().getStartDay(),
                            source.getSettings().getEndDay()
                    )
            );
        }
        target.setId(source.getId());
        target.setAuthor(authorMapper.createAuthorDTO(source.getAuthorId()));
        if (source.getCategories() != null) {
            target.setCategories(offerCategoryMapper.offerCategoriesByCategoriesIds(source.getCategories()));
        }
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());
        offerImageMapper.toListDTO(source.getImages(), target.getImages());
        target.setSeoUrl(source.getSeoUrl());
    }

    private void fromOfferCreateDTOToOffer(RentOfferCreateDTO source, RentOffer target) {
        if (source.getSettings() != null) {
            target.setSettings(new RentOfferSettings(source.getSettings().getMinRentDays(),
                    source.getSettings().getMaxRentDays(),
                    source.getSettings().getStartDay(),
                    source.getSettings().getEndDay()));
        }
        if (source.getCategory() != null) {
            target.setCategories(categoryService.getRentOfferCategoriesIds(source.getCategory()));
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
            target.setPrice(priceMapper.fromDTOToModel(source.getPrice()));
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
            final Set<RentOfferCategoryAttributeDTO> categoryAttributeDTOS = categoryAttributeService.findAllCategoryAttributeDTO().get(source.getCategory());
            if (categoryAttributeDTOS != null && !categoryAttributeDTOS.isEmpty()) {
                final Map<String, RentOfferCategoryAttributeDTO> categoryAttributeDTOMap = categoryAttributeDTOS.stream().collect(Collectors.toMap(RentOfferCategoryAttributeDTO::getKey, Function.identity()));
                if (source.getAttrs() != null) {
                    LinkedHashMap<String, RentOfferCategorySingleAttributeValue> attrs = new LinkedHashMap<>();
                    for (String key : source.getAttrs().keySet()) {
                        final String value = source.getAttrs().get(key);
                        final RentOfferCategoryAttributeDTO categoryAttributeDTO = categoryAttributeDTOMap.get(key);
                        RentOfferCategorySingleAttributeValue attributeValue = new RentOfferCategorySingleAttributeValue();
                        fromCategoryAttributeDTOToOfferCategoryAttributeValue(categoryAttributeDTO, attributeValue);
                        RentOfferToOfferCategoryAttributeValue selected = new RentOfferToOfferCategoryAttributeValue();
                        selected.setKey(value);
                        for (RentOfferCategoryAttributeValueDTO valueDTO : categoryAttributeDTO.getValues()) {
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
                    LinkedHashMap<String, RentOfferCategoryMultiAttributeValue> multiAttrs = new LinkedHashMap<>();
                    for (String key : source.getMultiAttrs().keySet()) {
                        RentOfferCategoryMultiAttributeValue attributeValue = new RentOfferCategoryMultiAttributeValue();
                        final RentOfferCategoryAttributeDTO categoryAttributeDTO = categoryAttributeDTOMap.get(key);
                        fromCategoryAttributeDTOToOfferCategoryAttributeValue(categoryAttributeDTO, attributeValue);
                        final String[] values = source.getMultiAttrs().get(key).split(",");
                        LinkedHashSet<RentOfferToOfferCategoryAttributeValue> selected = new LinkedHashSet<>();
                        for (String value : values) {
                            RentOfferToOfferCategoryAttributeValue selectedItem = new RentOfferToOfferCategoryAttributeValue();
                            selectedItem.setKey(value);
                            for (RentOfferCategoryAttributeValueDTO valueDTO : categoryAttributeDTO.getValues()) {
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
                    LinkedHashMap<String, RentOfferCategoryNumericAttributeValue> numericAttrs = new LinkedHashMap<>();
                    for (String key : source.getNumAttrs().keySet()) {
                        RentOfferCategoryNumericAttributeValue attributeValue = new RentOfferCategoryNumericAttributeValue();
                        final RentOfferCategoryAttributeDTO categoryAttributeDTO = categoryAttributeDTOMap.get(key);
                        fromCategoryAttributeDTOToOfferCategoryAttributeValue(categoryAttributeDTO, attributeValue);
                        attributeValue.setSelected(source.getNumAttrs().get(key));
                        attributeValue.setSelectedDouble(source.getNumAttrs().get(key).doubleValue());
                        numericAttrs.put(key, attributeValue);
                    }
                    target.setNumAttrs(numericAttrs);
                }
                if (source.getBoolAttrs() != null) {
                    LinkedHashMap<String, RentOfferCategoryBoolAttributeValue> boolAttrs = new LinkedHashMap<>();
                    for (String key : source.getBoolAttrs().keySet()) {
                        RentOfferCategoryBoolAttributeValue attributeValue = new RentOfferCategoryBoolAttributeValue();
                        final RentOfferCategoryAttributeDTO categoryAttributeDTO = categoryAttributeDTOMap.get(key);
                        fromCategoryAttributeDTOToOfferCategoryAttributeValue(categoryAttributeDTO, attributeValue);
                        attributeValue.setSelected(source.getBoolAttrs().get(key));
                        boolAttrs.put(key, attributeValue);
                    }
                    target.setBoolAttrs(boolAttrs);
                }
            }
        }
    }

    private RentOfferLands transformOfferLandsDTOToLands(RentOfferLandsDTO offerLandsDto) {
        RentOfferLands lands = null;
        if (offerLandsDto != null) {
            lands = new RentOfferLands(offerLandsDto.getCadnums(), offerLandsDto.getPolygons());
        }
        return lands;
    }

    private RentOfferLandsDTO transformLandsToOfferLandsDTO(RentOfferLands lands) {
        RentOfferLandsDTO offerLandsDTOS = null;
        if (lands != null) {
            offerLandsDTOS = new RentOfferLandsDTO(lands.getCadnums(), lands.getPolygons());
        }
        return offerLandsDTOS;
    }

    private void fromCategoryAttributeDTOToOfferCategoryAttributeValue(RentOfferCategoryAttributeDTO source, RentOfferCategoryAttributeBaseValue target) {
        target.setCode(source.getCode());
        target.setTitle(source.getTitle());
        target.setUnit(source.getUnit());
        target.setType(source.getType());
    }

    public RentOfferViewShortDTO fromRentObjectToShortDTO(RentOffer rentOffer) {
        RentOfferViewShortDTO rentOfferViewShortDTO = new RentOfferViewShortDTO();
        rentOfferViewShortDTO.setTitle(rentOffer.getTitle());
        rentOfferViewShortDTO.setDescription(rentOffer.getDescription());
        return rentOfferViewShortDTO;
    }
}