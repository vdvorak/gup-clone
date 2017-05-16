package ua.com.gup.service.mapper;

import org.springframework.stereotype.Component;
import ua.com.gup.domain.offer.OfferContactInfo;
import ua.com.gup.service.dto.offer.OfferContactInfoDTO;

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
