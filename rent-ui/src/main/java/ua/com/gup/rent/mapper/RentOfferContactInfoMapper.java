package ua.com.gup.rent.mapper;

import org.springframework.stereotype.Component;
import ua.com.gup.rent.model.rent.RentOfferContactInfo;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferContactInfoDTO;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RentOfferContactInfoMapper {

    public RentOfferContactInfoDTO contactInfoToContactInfoDTO(RentOfferContactInfo contactInfo, boolean hidePhoneNumber) {
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

    public RentOfferContactInfo contactInfoDTOToContactInfo(RentOfferContactInfoDTO contactInfoDTO) {
        RentOfferContactInfo contactInfo = new RentOfferContactInfo();
        contactInfo.setContactName(contactInfoDTO.getContactName());
        contactInfo.setPhoneNumbers(contactInfoDTO.getPhoneNumbers());
        return contactInfo;
    }
}
