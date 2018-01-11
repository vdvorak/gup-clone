package ua.com.gup.server.api.manager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.common.dto.profile.ProfileDTO;
import ua.com.gup.common.model.enumeration.CommonStatus;
import ua.com.gup.common.model.enumeration.CommonUserRole;
import ua.com.gup.dto.offer.view.OfferViewDetailsDTO;
import ua.com.gup.dto.offer.view.OfferViewShortDTO;
import ua.com.gup.common.dto.profile.ProfileShortAdminDTO;
import ua.com.gup.dto.profile.manager.ManagerPrivateProfileDto;
import ua.com.gup.dto.profile.manager.UserPrivateProfileDto;
import ua.com.gup.dto.profile.manager.UserProfileShortAdminDto;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.server.service.OfferService;
import ua.com.gup.service.profile.ProfilesService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/managers")
public class ManagerEndpoint {

    @Autowired
    private ProfilesService profilesService;
    @Autowired
    private OfferService offerService;


    /**
     * get all managers with ROLE_USER
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping(value = {"","/"})
    public ResponseEntity<Page<ProfileShortAdminDTO>> findManagersShortByFilter(Profile profileFilter, Pageable pageable) {
        Page<ProfileShortAdminDTO> profilesPageable = profilesService.findByRole(CommonUserRole.ROLE_MANAGER, pageable);
        return new ResponseEntity<>(profilesPageable, HttpStatus.OK);
    }


    /**
     * get manager profile by pulickID
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping(value = "/{managerPublicId}")
    public ResponseEntity<ManagerPrivateProfileDto> findFullManagerProfileByPublicId(@PathVariable("managerPublicId") String managerPublicId) {
        ManagerPrivateProfileDto profile = profilesService.findManagerPrivateProfileDTOForAdminByPublicId(managerPublicId);
        return new ResponseEntity(profile, HttpStatus.OK);
    }

    /**
     * get all profile with ROLE_USER
     */

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @GetMapping(value = "/profiles")
    public ResponseEntity<Page<ProfileShortAdminDTO>> findProfilesShortByFilter(Profile profileFilter, Pageable pageable) {
        Page<ProfileShortAdminDTO> profilesPageable = profilesService.findByRole(CommonUserRole.ROLE_USER, pageable);
        return new ResponseEntity<>(profilesPageable, HttpStatus.OK);
    }

    /**
     * get profile by pulickID
     */
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @GetMapping(value = "/profiles/{profilePublicId}")
    public ResponseEntity<ProfileDTO> findFullProfileByPublicId(@PathVariable("profilePublicId") String profilePublicId) {
        ProfileDTO profile = profilesService.findPrivateProfileDTOForAdminByPublicId(profilePublicId);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    /**
     * link user to manager
     *
     * add user to manager.users
     * set manager to user.manager
     */

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @PostMapping(value = "/{managerPublicId}/profiles/{profilePublicId}/link")
    public ResponseEntity<ProfileDTO> linkProfile(
            @PathVariable("managerPublicId") String managerPublicId,
            @PathVariable("profilePublicId") String profilePublicId) {

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
    @PostMapping(value = "/{managerPublicId}/profiles/{profilePublicId}/unlink")
    public ResponseEntity<ProfileDTO> unLinkProfile(
            @PathVariable("managerPublicId") String managerPublicId,
            @PathVariable("profilePublicId") String profilePublicId) {

        profilesService.unlinkProfile(managerPublicId, profilePublicId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * get users of current manager
     */
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @GetMapping(value = "/{managerPublicId}/users")
    public ResponseEntity<List<UserProfileShortAdminDto>> getUsers(@PathVariable("managerPublicId") String managerPublicId) {
        List<UserProfileShortAdminDto> users = profilesService.getManagerUsers(managerPublicId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * get user by publicId of current manager
     */
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @GetMapping(value = "/{managerPublicId}/users/{userPublicId}")
    public ResponseEntity<UserPrivateProfileDto> getUser(
            @PathVariable("managerPublicId") String managerPublicId,
            @PathVariable("userPublicId") String userPublicId) {

        UserPrivateProfileDto user = profilesService.getManagerUser(managerPublicId, userPublicId);
        if (user == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }



    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @GetMapping(value = "/{managerPublicId}/users/{userPublicId}/offers")
    public ResponseEntity<Page<OfferViewShortDTO>> searchOffers(
            @PathVariable("managerPublicId") String managerPublicId,
            @PathVariable("userPublicId") String userPublicId,
            CommonStatus status,
            Pageable pageable) {

        Page<OfferViewShortDTO> offers = offerService.findByManagerAndPublicIdAndStatus(status, userPublicId, pageable);
        return new ResponseEntity(offers, HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN') and hasPermission(#offerUpdateDTO.id, 'offer','EDIT')")
    @GetMapping(value = "/{managerPublicId}/users/{userPublicId}/offers/{offerId}")
    public ResponseEntity<OfferViewDetailsDTO> searchOffer(
            @PathVariable("managerPublicId") String managerPublicId,
            @PathVariable("userPublicId") String userPublicId,
            @PathVariable("offerId") String offerId) {
        Optional<OfferViewDetailsDTO> one = offerService.findOne(offerId);
        if (one.isPresent()) {
            return new ResponseEntity(one.get(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
