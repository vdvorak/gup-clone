package ua.com.gup.service.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.domain.Offer;
import ua.com.gup.domain.OfferCategory;
import ua.com.gup.domain.OfferModerationReport;
import ua.com.gup.domain.OfferStatistic;
import ua.com.gup.service.CategoryService;
import ua.com.gup.service.dto.*;
import ua.com.gup.service.security.SecurityUtils;

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
        if (source.getCategories() != null) {
            target.setCategoriesRegExp(source.getCategories().stream().map(c -> "" + c.getCode()).collect(Collectors.joining("/")));
            final LinkedList<OfferCategory> categories = source.getCategories().stream()
                    .map(c -> categoryService.getOfferCategory(c.getCode()))
                    .collect(Collectors.toCollection(LinkedList::new));
            target.setCategories(categories);
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

        if (source.getCategories() != null) {
            target.setCategories(source.getCategories());
        }
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
            target.setNumAttrs(source.getNumAttrs());
        }
        if (source.getBoolAttrs() != null) {
            target.setBoolAttrs(source.getBoolAttrs());
        }
    }

    private void fromOfferBaseDTOToOffer(OfferBaseDTO source, Offer target) {
        if (source.getCategories() != null) {
            target.setCategoriesRegExp(source.getCategories().stream().map(c -> "" + c.getCode()).collect(Collectors.joining("/")));
            final LinkedList<OfferCategory> categories = source.getCategories().stream()
                    .map(c -> categoryService.getOfferCategory(c.getCode()))
                    .collect(Collectors.toCollection(LinkedList::new));
            target.setCategories(categories);
        }
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
            target.setNumAttrs(source.getNumAttrs());
        }
        if (source.getBoolAttrs() != null) {
            target.setBoolAttrs(source.getBoolAttrs());
        }
    }
}
