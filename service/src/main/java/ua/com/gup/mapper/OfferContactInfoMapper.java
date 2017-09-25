package ua.com.gup.mapper;

import org.springframework.stereotype.Component;
import ua.com.gup.model.offer.OfferContactInfo;
import ua.com.gup.dto.offer.OfferContactInfoDTO;

@Component
public class OfferContactInfoMapper {

    public OfferContactInfoDTO contactInfoToContactInfoDTO(OfferContactInfo contactInfo) {
        OfferContactInfoDTO contactInfoDTO = new OfferContactInfoDTO();
        contactInfoDTO.setContactName(contactInfo.getContactName());
        contactInfoDTO.setPhoneNumbers(contactInfo.getPhoneNumbers());
        return contactInfoDTO;
    }

    public OfferContactInfo contactInfoDTOToContactInfo(OfferContactInfoDTO contactInfoDTO) {
        OfferContactInfo contactInfo = new OfferContactInfo();
        contactInfo.setContactName(contactInfoDTO.getContactName());
        contactInfo.setPhoneNumbers(contactInfoDTO.getPhoneNumbers());
        return contactInfo;
    }
}
