package ua.com.gup.rent.mapper;

import org.springframework.stereotype.Component;
import ua.com.gup.common.model.mongo.offer.OfferContactInfo;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferContactInfoDTO;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RentOfferContactInfoMapper {

    public RentOfferContactInfoDTO contactInfoToContactInfoDTO(OfferContactInfo contactInfo, boolean hidePhoneNumber) {
        RentOfferContactInfoDTO contactInfoDTO = new RentOfferContactInfoDTO();
        contactInfoDTO.setContactName(contactInfo.getContactName());
        Set<String> phoneNumbers = contactInfo.getPhoneNumbers();
        if(hidePhoneNumber){
                              phoneNumbers = phoneNumbers.stream().map(u -> { return u.substring(0, 5) + "*******";
                           }).collect(Collectors.toSet());
        }
        contactInfoDTO.setPhoneNumbers(phoneNumbers);      
        
        return contactInfoDTO;
    }

    public OfferContactInfo contactInfoDTOToContactInfo(RentOfferContactInfoDTO contactInfoDTO) {
        OfferContactInfo contactInfo = new OfferContactInfo();
        contactInfo.setContactName(contactInfoDTO.getContactName());
        contactInfo.setPhoneNumbers(contactInfoDTO.getPhoneNumbers());
        return contactInfo;
    }
}
