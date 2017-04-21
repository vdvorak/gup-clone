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
    private MoneyMapper moneyMapper;

    @Autowired
    private AddressMapper addressMapper;

    public Offer offerCreateDTOToOffer(OfferCreateDTO offerCreateDTO) {
        Offer offer = new Offer();
        fromOfferBaseDTOToOffer(offerCreateDTO, offer);
        if (offerCreateDTO.getAddress() != null) {
            offer.setAddress(addressMapper.addressDTOToAddress(offerCreateDTO.getAddress()));
        }
        if (offerCreateDTO.getTitle() != null) {
            offer.setTitle(offerCreateDTO.getTitle());
        }
        return offer;
    }

    public void offerUpdateDTOToOffer(OfferUpdateDTO source, Offer target) {
        fromOfferBaseDTOToOffer(source, target);
        if (source.getTitle() != null) {
            target.setTitle(source.getTitle());
        }
    }

    public OfferDetailsDTO offerToOfferDetailsDTO(Offer offer) {
        OfferDetailsDTO offerDetailsDTO = new OfferDetailsDTO();
        fromOfferToOfferBaseDTO(offer, offerDetailsDTO);
        offerDetailsDTO.setId(offer.getId());
        offerDetailsDTO.setSeoUrl(offer.getSeoUrl());
        offerDetailsDTO.setStatus(offer.getStatus());
        offerDetailsDTO.setAuthorId(offer.getAuthorId());
        if (offer.getAddress() != null) {
            offerDetailsDTO.setAddress(addressMapper.addressToAddressDTO(offer.getAddress()));
        }
        offerDetailsDTO.setTitle(offer.getTitle());
        return offerDetailsDTO;
    }

    public OfferShortDTO offerToOfferShortDTO(Offer offer) {
        OfferShortDTO offerShortDTO = new OfferShortDTO();
        fromOfferToOfferBaseDTO(offer, offerShortDTO);
        offerShortDTO.setId(offer.getId());
        offerShortDTO.setSeoUrl(offer.getSeoUrl());
        offerShortDTO.setAuthorId(offer.getAuthorId());
        offer.setTitle(offer.getTitle());
        return offerShortDTO;
    }

    private void fromOfferToOfferBaseDTO(Offer source, OfferBaseDTO target) {

        if (source.getCategories() != null) {
            target.setCategories(source.getCategories());
        }
        if (source.getDescription() != null) {
            target.setDescription(source.getDescription());
        }
        if (source.getImageIds() != null) {
            LinkedHashSet<OfferImageDTO> offerImageDTOS = new LinkedHashSet<>();
            source.getImageIds().forEach(id -> offerImageDTOS.add(new OfferImageDTO(id)));
            target.setImages(offerImageDTOS);
        }

        if (source.getPrice() != null) {
            target.setPrice(moneyMapper.moneyToMoneyDTO(source.getPrice()));
        }
        if (source.isPriceWithVAT() != null) {
            target.setPriceWithVAT(source.isPriceWithVAT());
        }
        if (source.getVideoUrl() != null) {
            target.setVideoUrl(source.getVideoUrl());
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
        if (source.getDescription() != null) {
            target.setDescription(source.getDescription());
        }
        if (source.getImages() != null) {
            LinkedHashSet<String> imageIds = new LinkedHashSet<>();
            source.getImages().forEach(c -> imageIds.add(c.getImageId()));
            target.setImageIds(imageIds);
        }
        if (source.getPrice() != null) {
            target.setPrice(moneyMapper.moneyDTOToMoney(source.getPrice(), Currency.UAH));
        }
        if (source.getPriceWithVAT() != null) {
            target.setPriceWithVAT(source.getPriceWithVAT());
        }
        if (source.getVideoUrl() != null) {
            target.setVideoUrl(source.getVideoUrl());
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
