package ua.com.gup.service.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.domain.Offer;
import ua.com.gup.domain.OfferModerationReport;
import ua.com.gup.domain.OfferNumericValue;
import ua.com.gup.domain.OfferStatistic;
import ua.com.gup.service.CategoryService;
import ua.com.gup.service.dto.*;
import ua.com.gup.service.security.SecurityUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
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
    private CategoryService categoryService;

    public Offer offerCreateDTOToOffer(OfferCreateDTO offerCreateDTO) {
        Offer offer = new Offer();
        fromOfferBaseDTOToOffer(offerCreateDTO, offer);
        if (offerCreateDTO.getCategory() != null) {
            offer.setCategories(categoryService.getOfferCategories(offerCreateDTO.getCategory()));
        }
        if (offerCreateDTO.getAddress() != null) {
            offer.setAddress(addressMapper.addressDTOToAddress(offerCreateDTO.getAddress()));
        }
        if (offerCreateDTO.getImages() != null && offerCreateDTO.getImages().size() > 0) {
            LinkedHashSet<String> imageIds = new LinkedHashSet<>();
            offerCreateDTO.getImages().forEach(i -> imageIds.add(i.getImageId()));
            offer.setImageIds(imageIds);
        }
        if (offerCreateDTO.getYoutubeVideoId() != null) {
            offer.setYoutubeVideoId(offerCreateDTO.getYoutubeVideoId());
        }
        if (offerCreateDTO.getContactInfo() != null) {
            offer.setContactInfo(contactInfoMapper.contactInfoDTOToContactInfo(offerCreateDTO.getContactInfo()));
        }
        offer.setStatistic(new OfferStatistic());
        return offer;
    }

    public void offerUpdateDTOToOffer(OfferUpdateDTO source, Offer target) {
        fromOfferBaseDTOToOffer(source, target);
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

    public void offerModeratorDTOToOffer(OfferModeratorDTO source, Offer target) {
        if (source.getCategory() != null) {
            target.setCategories(categoryService.getOfferCategories(source.getCategory()));
        }
        OfferModerationReport moderationReport = source.getModerationReport();
        if (moderationReport == null) {
            moderationReport = new OfferModerationReport();
        }
        moderationReport.setModeratorId(SecurityUtils.getCurrentUserId());
        moderationReport.setLastModifiedDate(LocalDateTime.now());
        target.setLastModerationReport(moderationReport);
    }

    public OfferDetailsDTO offerToOfferDetailsDTO(Offer offer) {
        OfferDetailsDTO offerDetailsDTO = new OfferDetailsDTO();
        fromOfferToOfferBaseDTO(offer, offerDetailsDTO);
        offerDetailsDTO.setCategories(offer.getCategories());
        offerDetailsDTO.setLastModifiedDate(offer.getLastModifiedDate());
        offerDetailsDTO.setStatus(offer.getStatus());
        offerDetailsDTO.setId(offer.getId());
        offerDetailsDTO.setAuthorId(offer.getAuthorId());
        if (offer.getAddress() != null) {
            offerDetailsDTO.setAddress(addressMapper.addressToAddressDTO(offer.getAddress()));
        }
        if (offer.getImageIds() != null && offer.getImageIds().size() > 0) {
            offerDetailsDTO.setImageIds(offer.getImageIds());
        }
        offerDetailsDTO.setSeoUrl(offer.getSeoUrl());
        offerDetailsDTO.setYoutubeVideoId(offer.getYoutubeVideoId());
        offerDetailsDTO.setContactInfo(contactInfoMapper.contactInfoToContactInfoDTO(offer.getContactInfo()));
        return offerDetailsDTO;
    }

    public OfferShortDTO offerToOfferShortDTO(Offer offer) {
        OfferShortDTO offerShortDTO = new OfferShortDTO();
        fromOfferToOfferBaseDTO(offer, offerShortDTO);
        offerShortDTO.setCategories(offer.getCategories());
        offerShortDTO.setAddress(addressMapper.addressToAddressShortDTO(offer.getAddress()));
        offerShortDTO.setLastModifiedDate(offer.getLastModifiedDate());
        offerShortDTO.setId(offer.getId());
        offerShortDTO.setAuthorId(offer.getAuthorId());
        if (offer.getImageIds() != null && offer.getImageIds().size() > 0) {
            offerShortDTO.setImageIds(offer.getImageIds());
        }
        offerShortDTO.setSeoUrl(offer.getSeoUrl());
        return offerShortDTO;
    }

    private void fromOfferToOfferBaseDTO(Offer source, OfferBaseDTO target) {
        if (source.getTitle() != null) {
            target.setTitle(source.getTitle());
        }
        if (source.getDescription() != null) {
            target.setDescription(source.getDescription());
        }
        if (source.getPrice() != null) {
            target.setPrice(priceMapper.moneyToMoneyDTO(source.getPrice()));
        }
        if (source.getAttrs() != null) {
            target.setAttrs(source.getAttrs());
        }
        if (source.getMultiAttrs() != null) {
            Map<String, String> multiAttrs = new HashMap<>();
            for (String key : source.getMultiAttrs().keySet()) {
                multiAttrs.put(key, source.getMultiAttrs().get(key).stream().collect(Collectors.joining(",")));
            }
            target.setMultiAttrs(multiAttrs);
        }
        if (source.getNumAttrs() != null) {
            LinkedHashMap<String, BigDecimal> result = new LinkedHashMap<>();
            final Map<String, OfferNumericValue> numAttrs = source.getNumAttrs();
            for (String key : numAttrs.keySet()) {
                result.put(key, numAttrs.get(key).getViewValue());
            }
            target.setNumAttrs(result);
        }
        if (source.getBoolAttrs() != null) {
            target.setBoolAttrs(source.getBoolAttrs());
        }
    }

    private void fromOfferBaseDTOToOffer(OfferBaseDTO source, Offer target) {
        if (source.getTitle() != null) {
            target.setTitle(source.getTitle());
        }
        if (source.getDescription() != null) {
            target.setDescription(source.getDescription());
        }
        if (source.getPrice() != null) {
            target.setPrice(priceMapper.moneyDTOToMoney(source.getPrice()));
        }
        if (source.getAttrs() != null) {
            target.setAttrs(source.getAttrs());
        }
        if (source.getMultiAttrs() != null) {
            Map<String, Set<String>> multiAttrs = new HashMap<>();
            for (String key : source.getMultiAttrs().keySet()) {
                final String[] split = source.getMultiAttrs().get(key).split(",");
                final HashSet<String> strings = new HashSet<>(Arrays.asList(split));
                multiAttrs.put(key, strings);
            }
            target.setMultiAttrs(multiAttrs);
        }
        if (source.getNumAttrs() != null) {
            LinkedHashMap<String, OfferNumericValue> result = new LinkedHashMap<>();
            final Map<String, BigDecimal> numAttrs = source.getNumAttrs();
            for (String key : numAttrs.keySet()) {
                OfferNumericValue value = new OfferNumericValue();
                value.setViewValue(numAttrs.get(key));
                value.setValue(numAttrs.get(key).doubleValue());
                result.put(key, value);
            }
            target.setNumAttrs(result);
        }
        if (source.getBoolAttrs() != null) {
            target.setBoolAttrs(source.getBoolAttrs());
        }
    }
}
