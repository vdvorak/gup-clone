package ua.com.gup.rent.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.common.model.category.attribute.*;
import ua.com.gup.common.model.enumeration.CommonStatus;
import ua.com.gup.common.model.mongo.offer.OfferModerationReport;
import ua.com.gup.common.service.mapper.ImageStorageMapper;
import ua.com.gup.rent.model.mongo.rent.RentOffer;
import ua.com.gup.rent.model.mongo.rent.calendar.RentOfferCalendar;
import ua.com.gup.rent.model.rent.calendar.RentOfferCalendarDay;
import ua.com.gup.rent.model.rent.statistic.RentOfferStatistic;
import ua.com.gup.rent.service.category.RentOfferCategoryService;
import ua.com.gup.rent.service.category.attribute.RentOfferCategoryAttributeService;
import ua.com.gup.rent.service.dto.category.attribute.RentOfferCategoryAttributeDTO;
import ua.com.gup.rent.service.dto.category.attribute.RentOfferCategoryAttributeValueDTO;
import ua.com.gup.common.dto.offer.CommonModerationReportDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferCreateDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferUpdateDTO;
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
    private RentOfferSettingsMapper rentOfferSettingsMapper;
    @Autowired
    private RentOfferCalendarMapper rentOfferCalendarMapper;
    @Autowired
    private RentOfferLandsMapper rentOfferLandsMapper;
    @Autowired
    private RentOfferCategoryAttributeMapper rentOfferCategoryAttributeMapper;
    @Autowired
    private ImageStorageMapper offerImageMapper;

    public RentOffer offerCreateDTOToOffer(RentOfferCreateDTO offerCreateDTO) {
        RentOffer offer = new RentOffer();
        fromOfferCreateDTOToOffer(offerCreateDTO, offer);
        offer.setTitle(offerCreateDTO.getTitle());
        offer.setDescription(offerCreateDTO.getDescription());
        offer.setStatus(CommonStatus.NEW);
        offer.setPrice(rentOfferPriceMapper.fromDTOToModel(offerCreateDTO.getPrice()));

        LocalDate startDate = offerCreateDTO.getCalendar().getStartDate();
        LocalDate endDate = offerCreateDTO.getCalendar().getEndDate();
        List<RentOfferCalendarDay> days = offerCreateDTO.getCalendar().getDays();

        RentOfferCalendar rentOfferCalendar = new RentOfferCalendar(startDate, endDate);
        rentOfferCalendar.setDays(days.toArray(new RentOfferCalendarDay[days.size()]));
        offer.setRentOfferCalendar(rentOfferCalendar);
        offer.setRentObjectsCount(offerCreateDTO.getCount());

        return offer;
    }

    public void offerUpdateDTOToOffer(RentOfferUpdateDTO source, RentOffer target) {
        fromOfferCreateDTOToOffer(source, target);
        if (source.getCategory() != null) {
            target.setCategories(categoryService.getRentOfferCategoriesIds(source.getCategory()));
        }
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

    public void offerModeratorDTOToOffer(CommonModerationReportDTO source, RentOffer target) {
        if (source.getCategory() != null) {
            target.setCategories(categoryService.getRentOfferCategoriesIds(source.getCategory()));
        }
        OfferModerationReport moderationReport = new OfferModerationReport();
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
            offerViewDetailsDTO.setPrice(priceMapper.fromModelToDTO(offer.getPrice()));
        }
        offerViewDetailsDTO.setYoutubeVideoId(offer.getYoutubeVideoId());

        //owner ? doesn't hide phone number : hide phone number
        boolean hidePhoneNumber = !RentSecurityUtils.isAuthenticated() || !(RentSecurityUtils.getCurrentUserId().equals(offer.getAuthorId()));
        offerViewDetailsDTO.setContactInfo(contactInfoMapper.contactInfoToContactInfoDTO(offer.getContactInfo(), hidePhoneNumber));

        offerViewDetailsDTO.setOfferStatistic(new RentOfferStatisticDTO(offer.getStatistic().getTotalOfferViewsCount(), offer.getStatistic().getTotalOfferPhonesViewsCount()));
        offerViewDetailsDTO.setCount(offer.getRentObjectsCount());
        if (offer.getLands() != null) {
            offerViewDetailsDTO.setLands(rentOfferLandsMapper.fromModelToDTO(offer.getLands()));
        }

        if (offer.getRentOfferCalendar() != null)
            offerViewDetailsDTO.setRentOfferCalendarDTO(rentOfferCalendarMapper.fromModelToDTO(offer.getRentOfferCalendar()));
        return offerViewDetailsDTO;
    }

    private void fromOfferToOfferViewShortDTO(RentOffer source, RentOfferViewShortDTO target) {

        fromOfferToOfferViewBaseDTO(source, target);

        target.setStatus(source.getStatus());
        target.setAttrs(source.getAttrs());
        target.setMultiAttrs(source.getMultiAttrs());
        target.setNumAttrs(source.getNumAttrs());
        target.setBoolAttrs(source.getBoolAttrs());
        target.setStatus(source.getStatus());

        target.setYoutubeVideoId(source.getYoutubeVideoId());

        //owner ? doesn't hide phone number : hide phone number
        boolean hidePhoneNumber = !RentSecurityUtils.isAuthenticated() || !(RentSecurityUtils.getCurrentUserId().equals(source.getAuthorId()));
        target.setContactInfo(contactInfoMapper.contactInfoToContactInfoDTO(source.getContactInfo(), hidePhoneNumber));

        target.setOfferStatistic(new RentOfferStatisticDTO(source.getStatistic().getTotalOfferViewsCount(), source.getStatistic().getTotalOfferPhonesViewsCount()));

        if (source.getAddress() != null) {
            target.setAddress(addressMapper.addressToAddressDTO(source.getAddress()));
        }

        if (source.getLands() != null) {
            target.setLands(rentOfferLandsMapper.fromModelToDTO(source.getLands()));
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

        target.setId(source.getId());
        if (source.getAuthorId() != null && !source.getAuthorId().isEmpty()) {
            target.setAuthor(authorMapper.createAuthorDTO(source.getAuthorId()));
        }

        if (source.getPrice() != null) {
            target.setPrice(rentOfferPriceMapper.fromModelToDTO(source.getPrice()));
        }

        if (source.getSettings() != null) {
            target.setSettings(rentOfferSettingsMapper.fromModelToDTO(source.getSettings()));
        }

        if (source.getCategories() != null) {
            target.setCategories(offerCategoryMapper.offerCategoriesByCategoriesIds(source.getCategories()));
        }

        target.setLastModifiedDate(source.getLastModifiedDate());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());
        target.setSeoUrl(source.getSeoUrl());
        target.setDeposit(source.getDeposit());
        offerImageMapper.toListDTO(source.getImages(), target.getImages());
    }

    private void fromOfferCreateDTOToOffer(RentOfferCreateDTO source, RentOffer target) {

        if (source.getSettings() != null) {
            target.setSettings(rentOfferSettingsMapper.fromDTOToModel(source.getSettings()));
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
            target.setLands(rentOfferLandsMapper.fromDTOToModel(source.getLands()));
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
                    LinkedHashMap<String, OfferCategorySingleAttributeValue> attrs = new LinkedHashMap<>();
                    for (String key : source.getAttrs().keySet()) {
                        final String value = source.getAttrs().get(key);
                        final RentOfferCategoryAttributeDTO categoryAttributeDTO = categoryAttributeDTOMap.get(key);
                        OfferCategorySingleAttributeValue attributeValue = new OfferCategorySingleAttributeValue();
                        rentOfferCategoryAttributeMapper.fromCategoryAttributeDTOToOfferCategoryAttributeValue(categoryAttributeDTO, attributeValue);
                        OfferCategoryAttributeValue selected = new OfferCategoryAttributeValue();
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
                    LinkedHashMap<String, OfferCategoryMultiAttributeValue> multiAttrs = new LinkedHashMap<>();
                    for (String key : source.getMultiAttrs().keySet()) {
                        OfferCategoryMultiAttributeValue attributeValue = new OfferCategoryMultiAttributeValue();
                        final RentOfferCategoryAttributeDTO categoryAttributeDTO = categoryAttributeDTOMap.get(key);
                        rentOfferCategoryAttributeMapper.fromCategoryAttributeDTOToOfferCategoryAttributeValue(categoryAttributeDTO, attributeValue);
                        final String[] values = source.getMultiAttrs().get(key).split(",");
                        LinkedHashSet<OfferCategoryAttributeValue> selected = new LinkedHashSet<>();
                        for (String value : values) {
                            OfferCategoryAttributeValue selectedItem = new OfferCategoryAttributeValue();
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
                    LinkedHashMap<String, OfferCategoryNumericAttributeValue> numericAttrs = new LinkedHashMap<>();
                    for (String key : source.getNumAttrs().keySet()) {
                        OfferCategoryNumericAttributeValue attributeValue = new OfferCategoryNumericAttributeValue();
                        final RentOfferCategoryAttributeDTO categoryAttributeDTO = categoryAttributeDTOMap.get(key);
                        rentOfferCategoryAttributeMapper.fromCategoryAttributeDTOToOfferCategoryAttributeValue(categoryAttributeDTO, attributeValue);
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
                        final RentOfferCategoryAttributeDTO categoryAttributeDTO = categoryAttributeDTOMap.get(key);
                        rentOfferCategoryAttributeMapper.fromCategoryAttributeDTOToOfferCategoryAttributeValue(categoryAttributeDTO, attributeValue);
                        attributeValue.setSelected(source.getBoolAttrs().get(key));
                        boolAttrs.put(key, attributeValue);
                    }
                    target.setBoolAttrs(boolAttrs);
                }
            }
        }
    }
}