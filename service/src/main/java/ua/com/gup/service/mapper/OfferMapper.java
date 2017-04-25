package ua.com.gup.service.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.domain.Offer;
import ua.com.gup.domain.OfferCategory;
import ua.com.gup.domain.enumeration.Currency;
import ua.com.gup.service.dto.*;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;

@Component
public class OfferMapper {

    @Autowired
    private PriceMapper priceMapper;

    @Autowired
    private AddressMapper addressMapper;

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
    }

    public OfferDetailsDTO offerToOfferDetailsDTO(Offer offer) {
        OfferDetailsDTO offerDetailsDTO = new OfferDetailsDTO();
        fromOfferToOfferBaseDTO(offer, offerDetailsDTO);
        offerDetailsDTO.setLastModifiedDate(offer.getLastModifiedDate());
        offerDetailsDTO.setId(offer.getId());
        offerDetailsDTO.setAuthorId(offer.getAuthorId());
        if (offer.getAddress() != null) {
            offerDetailsDTO.setAddress(addressMapper.addressToAddressDTO(offer.getAddress()));
        }
        offerDetailsDTO.setSeoUrl(offer.getSeoUrl());
        offerDetailsDTO.setYoutubeVideoId(offer.getYoutubeVideoId());
        return offerDetailsDTO;
    }

    public OfferShortDTO offerToOfferShortDTO(Offer offer) {
        OfferShortDTO offerShortDTO = new OfferShortDTO();
        fromOfferToOfferBaseDTO(offer, offerShortDTO);
        offerShortDTO.setLastModifiedDate(offer.getLastModifiedDate());
        offerShortDTO.setId(offer.getId());
        offerShortDTO.setAuthorId(offer.getAuthorId());
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
        if (source.getNumAttrs() != null) {
            target.setNumAttrs(source.getNumAttrs());
        }
        if (source.getBoolAttrs() != null) {
            target.setBoolAttrs(source.getBoolAttrs());
        }
    }

    private void fromOfferBaseDTOToOffer(OfferBaseDTO source, Offer target) {
        if (source.getCategories() != null) {
            target.setCategoriesRegExp(source.getCategories().stream().map(OfferCategory::getCode).collect(Collectors.joining("/")));
            target.setCategories(source.getCategories());
        }
        if (source.getTitle() != null) {
            target.setTitle(source.getTitle());
        }
        if (source.getDescription() != null) {
            target.setDescription(source.getDescription());
        }
        if (source.getPrice() != null) {
            target.setPrice(priceMapper.moneyDTOToMoney(source.getPrice(), Currency.UAH));
        }
        if (source.getAttrs() != null) {
            target.setAttrs(source.getAttrs());
        }
        if (source.getNumAttrs() != null) {
            target.setNumAttrs(source.getNumAttrs());
        }
        if (source.getBoolAttrs() != null) {
            target.setBoolAttrs(source.getBoolAttrs());
        }
    }
}
