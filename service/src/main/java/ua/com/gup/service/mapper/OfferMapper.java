package ua.com.gup.service.mapper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.domain.Offer;
import ua.com.gup.domain.offer.OfferModerationReport;
import ua.com.gup.domain.offer.OfferStatistic;
import ua.com.gup.domain.offer.attribute.value.*;
import ua.com.gup.domain.offer.land.Lands;
import ua.com.gup.service.CategoryAttributeService;
import ua.com.gup.service.CategoryService;
import ua.com.gup.service.dto.category.tree.CategoryAttributeDTO;
import ua.com.gup.service.dto.category.tree.CategoryAttributeValueDTO;
import ua.com.gup.service.dto.offer.OfferCreateDTO;
import ua.com.gup.service.dto.offer.OfferLandsDTO;
import ua.com.gup.service.dto.offer.OfferModerationReportDTO;
import ua.com.gup.service.dto.offer.OfferUpdateDTO;
import ua.com.gup.service.dto.offer.view.OfferViewBaseDTO;
import ua.com.gup.service.dto.offer.view.OfferViewDetailsDTO;
import ua.com.gup.service.dto.offer.view.OfferViewShortDTO;
import ua.com.gup.service.dto.offer.view.OfferViewShortWithModerationReportDTO;
import ua.com.gup.service.security.SecurityUtils;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class OfferMapper {

    private final Logger log = LoggerFactory.getLogger(OfferMapper.class);

    @Autowired
    private PriceMapper priceMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private OfferContactInfoMapper contactInfoMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryAttributeService categoryAttributeService;

    public Offer offerCreateDTOToOffer(OfferCreateDTO offerCreateDTO) {
        Offer offer = new Offer();
        fromOfferCreateDTOToOffer(offerCreateDTO, offer);
        offer.setStatistic(new OfferStatistic());
        return offer;
    }

    public void offerUpdateDTOToOffer(OfferUpdateDTO source, Offer target) {
        fromOfferCreateDTOToOffer(source, target);
        if (source.getCategory() != null) {
            target.setCategories(categoryService.getOfferCategories(source.getCategory()));
        }
        if (source.getAddress() != null) {
            target.setAddress(addressMapper.addressDTOToAddress(source.getAddress()));
        }
        if (source.getImages() != null && source.getImages().size() > 0) {
            LinkedHashSet<String> imageIds = new LinkedHashSet<>();
            source.getImages().forEach(i -> imageIds.add(i.getImageId()));
            target.setImageIds(imageIds);
        }
        if (source.getYoutubeVideoId() != null) {
            target.setYoutubeVideoId(source.getYoutubeVideoId());
        }
        if (source.getContactInfo() != null) {
            target.setContactInfo(contactInfoMapper.contactInfoDTOToContactInfo(source.getContactInfo()));
        }
    }

    public void offerModeratorDTOToOffer(OfferModerationReportDTO source, Offer target) {
        if (source.getCategory() != null) {
            target.setCategories(categoryService.getOfferCategories(source.getCategory()));
        }
        OfferModerationReport moderationReport = new OfferModerationReport();
        moderationReport.setDescription(source.getDescription());
        moderationReport.setRefusalReasons(source.getRefusalReasons());
        moderationReport.setModeratorId(SecurityUtils.getCurrentUserId());
        moderationReport.setLastModifiedDate(ZonedDateTime.now());
        target.setLastModerationReport(moderationReport);
    }

    public OfferViewShortDTO offerToOfferShortDTO(Offer offer) {
        OfferViewShortDTO offerViewShortDTO = new OfferViewShortDTO();
        fromOfferToOfferViewShortDTO(offer, offerViewShortDTO);
        return offerViewShortDTO;
    }

    public OfferViewShortWithModerationReportDTO offerToOfferViewShortWithModerationReportDTO(Offer offer) {
        OfferViewShortWithModerationReportDTO offerViewShortWithModerationReportDTO = new OfferViewShortWithModerationReportDTO();
        fromOfferToOfferViewShortDTO(offer, offerViewShortWithModerationReportDTO);
        offerViewShortWithModerationReportDTO.setModerationReport(offer.getLastModerationReport());
        return offerViewShortWithModerationReportDTO;
    }

    private void fromOfferToOfferViewShortDTO(Offer source, OfferViewShortDTO target) {
        fromOfferToOfferViewBaseDTO(source, target);
        target.setAddress(addressMapper.addressToAddressShortDTO(source.getAddress()));
        target.setStatistic(source.getStatistic());
        if(source.getLands()!=null) {
            target.setLands(transformlandsToOfferLandsDTO(source.getLands()));
        }
    }

    public OfferViewDetailsDTO offerToOfferDetailsDTO(Offer offer) {
        OfferViewDetailsDTO offerViewDetailsDTO = new OfferViewDetailsDTO();
        fromOfferToOfferViewBaseDTO(offer, offerViewDetailsDTO);
        offerViewDetailsDTO.setStatus(offer.getStatus());
        offerViewDetailsDTO.setAddress(addressMapper.addressToAddressDTO(offer.getAddress()));
        offerViewDetailsDTO.setYoutubeVideoId(offer.getYoutubeVideoId());
        offerViewDetailsDTO.setContactInfo(contactInfoMapper.contactInfoToContactInfoDTO(offer.getContactInfo()));
        offerViewDetailsDTO.setStatistic(offer.getStatistic());

        if(offer.getLands()!= null) {
            offerViewDetailsDTO.setLands(transformlandsToOfferLandsDTO(offer.getLands()));
        }

        return offerViewDetailsDTO;
    }



    private void fromOfferToOfferViewBaseDTO(Offer source, OfferViewBaseDTO target) {
        target.setId(source.getId());
        target.setLastModifiedDate(source.getLastModifiedDate());
        target.setAuthorId(source.getAuthorId());
        target.setCategories(source.getCategories());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());
        if (source.getPrice() != null) {
            target.setPrice(priceMapper.moneyToMoneyDTO(source.getPrice()));
        }
        if (source.getImageIds() != null && source.getImageIds().size() > 0) {
            target.setImageIds(source.getImageIds());
        }
        target.setSeoUrl(source.getSeoUrl());
        target.setAttrs(source.getAttrs());
        target.setMultiAttrs(source.getMultiAttrs());
        target.setNumAttrs(source.getNumAttrs());
        target.setBoolAttrs(source.getBoolAttrs());
    }

    private void fromOfferCreateDTOToOffer(OfferCreateDTO source, Offer target) {

        if (source.getCategory() != null) {
            target.setCategories(categoryService.getOfferCategories(source.getCategory()));
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

        if (source.getImages() != null && source.getImages().size() > 0) {
            LinkedHashSet<String> imageIds = new LinkedHashSet<>();
            source.getImages().forEach(i -> imageIds.add(i.getImageId()));
            target.setImageIds(imageIds);
        }

        if(source.getLands() != null){
            target.setLands(transformOfferLandsDTOtoLands(source.getLands()));
        }

        if (source.getYoutubeVideoId() != null) {
            target.setYoutubeVideoId(source.getYoutubeVideoId());
        }

        if (source.getContactInfo() != null) {
            target.setContactInfo(contactInfoMapper.contactInfoDTOToContactInfo(source.getContactInfo()));
        }
        if (source.getCategory() != null) {
            final LinkedHashSet<CategoryAttributeDTO> categoryAttributeDTOS = categoryAttributeService.findAllCategoryAttributeDTO().get(source.getCategory());
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

    private ArrayList<Lands> transformOfferLandsDTOtoLands(ArrayList<OfferLandsDTO> offerLandsDto) {
        ArrayList<Lands> lands = new ArrayList<Lands>();
        for (OfferLandsDTO item : offerLandsDto  ) {
            Lands land = new Lands(item.getMapData(),item.getGeometry());
            lands.add(land);
        }
          return lands;
    }
    private List<OfferLandsDTO> transformlandsToOfferLandsDTO(List<Lands> lands) {
        ArrayList<OfferLandsDTO> offerLandsDTOS = new ArrayList<OfferLandsDTO>();
        for (Lands item : lands  ) {
            OfferLandsDTO offerLandsDTO = new OfferLandsDTO(item.getMapData(),item.getGeometry());
            offerLandsDTOS.add(offerLandsDTO);
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
