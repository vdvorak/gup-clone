package ua.com.gup.rent.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.common.model.enumeration.CommonStatus;
import ua.com.gup.common.model.enumeration.CommonUserRole;
import ua.com.gup.rent.service.dto.rent.offer.profile.RentOfferProfileDTO;
import ua.com.gup.rent.service.dto.rent.offer.profile.RentOfferProfileShortAdminDTO;
import ua.com.gup.rent.service.dto.rent.offer.profile.manager.RentOfferManagerPrivateProfileDto;
import ua.com.gup.rent.service.dto.rent.offer.profile.manager.RentOfferUserPrivateProfileDto;
import ua.com.gup.rent.service.dto.rent.offer.profile.manager.UserProfileShortManagerDto;
import ua.com.gup.rent.service.dto.rent.offer.view.RentOfferViewDetailsDTO;
import ua.com.gup.rent.service.dto.rent.offer.view.RentOfferViewShortDTO;
import ua.com.gup.rent.service.profile.ProfileFilter;
import ua.com.gup.rent.service.profile.RentOfferProfilesService;
import ua.com.gup.rent.service.rent.RentOfferService;

import java.util.Optional;

@RestController
@RequestMapping("/api/managers")
public class ManagerEndpoint {

    @Autowired
    private RentOfferProfilesService profilesService;
    @Autowired
    private RentOfferService offerService;


    /**
     * get all managers with ROLE_USER
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping(value = {"","/"})
    public ResponseEntity<Page<RentOfferProfileShortAdminDTO>> findManagersShortByFilter(ProfileFilter filter, Pageable pageable) {
        Page<RentOfferProfileShortAdminDTO> profilesPageable = profilesService.findByRole(filter, CommonUserRole.ROLE_MANAGER, pageable);
        return new ResponseEntity<>(profilesPageable, HttpStatus.OK);
    }


    /**
     * get manager profile by pulickID
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping(value = "/{managerPublicId}")
    public ResponseEntity<RentOfferManagerPrivateProfileDto> findFullManagerProfileByPublicId(@PathVariable("managerPublicId") String managerPublicId) {
        RentOfferManagerPrivateProfileDto profile = profilesService.findManagerPrivateProfileDTOForAdminByPublicId(managerPublicId);
        return new ResponseEntity(profile, HttpStatus.OK);
    }

    /**
     * get all profile with ROLE_USER
     */

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @GetMapping(value = "/profiles")
    public ResponseEntity<Page<UserProfileShortManagerDto>> findProfilesShortByFilter(ProfileFilter filter, Pageable pageable) {
        Page<UserProfileShortManagerDto> profilesPageable = profilesService.findUserProfiles(filter, pageable);
        return new ResponseEntity<>(profilesPageable, HttpStatus.OK);
    }

    /**
     * get profile by pulickID
     */
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @GetMapping(value = "/profiles/{profilePublicId}")
    public ResponseEntity<RentOfferProfileDTO> findFullProfileByPublicId(@PathVariable("profilePublicId") String profilePublicId) {
        RentOfferProfileDTO profile = profilesService.findPrivateProfileDTOForAdminByPublicId(profilePublicId);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    /**
     * link user to manager
     *
     * add user to manager.users
     * set manager to user.manager
     */

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @PostMapping(value = "/profiles/{profilePublicId}/link/{managerPublicId}")
    public ResponseEntity<RentOfferProfileDTO> linkProfile(
            @PathVariable("profilePublicId") String profilePublicId,
            @PathVariable("managerPublicId") String managerPublicId) {

        if (!profilesService.hasRole(profilePublicId, CommonUserRole.ROLE_USER)) {
            return new ResponseEntity("error.user.invalid.userType", HttpStatus.BAD_REQUEST);
        }
        if (profilesService.hasManager(profilePublicId)) {
            return new ResponseEntity("error.user.exists.manager", HttpStatus.BAD_REQUEST);
        }
        profilesService.linkProfile(managerPublicId, profilePublicId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * remove user to manager.users
     * set null to user.manager
     */
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @PostMapping(value = "/profiles/{profilePublicId}/unlink/{managerPublicId}")
    public ResponseEntity<RentOfferProfileDTO> unLinkProfile(
            @PathVariable("profilePublicId") String profilePublicId,
            @PathVariable("managerPublicId") String managerPublicId) {

        profilesService.unlinkProfile(managerPublicId, profilePublicId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * get users of manager by publicId{managerPublicId}
     */
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @GetMapping(value = "/{managerPublicId}/users")
    public ResponseEntity<Page<UserProfileShortManagerDto>> getUsers(
            @PathVariable("managerPublicId") String managerPublicId,
            ProfileFilter filter,
            Pageable pageable) {
        Page<UserProfileShortManagerDto> profilesPageable = profilesService.findUserProfiles(managerPublicId, filter, pageable);
        return new ResponseEntity<>(profilesPageable, HttpStatus.OK);

    }

    /**
     * get user by manager publicId and user publicId
     */
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @GetMapping(value = "/{managerPublicId}/users/{userPublicId}")
    public ResponseEntity<RentOfferUserPrivateProfileDto> getUser(
            @PathVariable("managerPublicId") String managerPublicId,
            @PathVariable("userPublicId") String userPublicId) {

        RentOfferUserPrivateProfileDto user = profilesService.getManagerUser(managerPublicId, userPublicId);
        if (user == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }



    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @GetMapping(value = "/{managerPublicId}/users/{userPublicId}/offers")
    public ResponseEntity<Page<RentOfferViewShortDTO>> searchOffers(
            @PathVariable("managerPublicId") String managerPublicId,
            @PathVariable("userPublicId") String userPublicId,
            CommonStatus status,
            Pageable pageable) {

        Page<RentOfferViewShortDTO> offers = offerService.findByManagerAndPublicIdAndStatus(status, userPublicId, pageable);
        return new ResponseEntity(offers, HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN') and hasPermission(#offerUpdateDTO.id, 'offer','EDIT')")
    @GetMapping(value = "/{managerPublicId}/users/{userPublicId}/offers/{offerId}")
    public ResponseEntity<RentOfferViewDetailsDTO> searchOffer(
            @PathVariable("managerPublicId") String managerPublicId,
            @PathVariable("userPublicId") String userPublicId,
            @PathVariable("offerId") String offerId) {
        Optional<RentOfferViewDetailsDTO> one = offerService.findOne(offerId);
        if (one.isPresent()) {
            return new ResponseEntity(one.get(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
