package ua.com.gup.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import ua.com.gup.dto.offer.OfferContactInfoDTO;
import ua.com.gup.mongo.model.offer.OfferContactInfo;

@Component
public class OfferContactInfoMapper {

    public OfferContactInfoDTO contactInfoToContactInfoDTO(OfferContactInfo contactInfo) {
        OfferContactInfoDTO contactInfoDTO = new OfferContactInfoDTO();
        contactInfoDTO.setContactName(contactInfo.getContactName());
        
        Set<String> phoneNumbers = contactInfo.getPhoneNumbers().stream().map(u -> {
            return u.substring(0, 5) + "*******";
        }).collect(Collectors.toSet());        
        contactInfoDTO.setPhoneNumbers(phoneNumbers);      
        
        return contactInfoDTO;
    }

    public OfferContactInfo contactInfoDTOToContactInfo(OfferContactInfoDTO contactInfoDTO) {
        OfferContactInfo contactInfo = new OfferContactInfo();
        contactInfo.setContactName(contactInfoDTO.getContactName());
        contactInfo.setPhoneNumbers(contactInfoDTO.getPhoneNumbers());
        return contactInfo;
    }
}
