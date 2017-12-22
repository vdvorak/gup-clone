package ua.com.gup.server.api.manager;


import jdk.net.SocketFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.common.model.enumeration.CommonStatus;
import ua.com.gup.common.model.enumeration.CommonUserRole;
import ua.com.gup.common.model.filter.CommonAuthorFilter;
import ua.com.gup.dto.offer.view.OfferViewDetailsDTO;
import ua.com.gup.dto.offer.view.OfferViewShortDTO;
import ua.com.gup.dto.profile.ProfileDTO;
import ua.com.gup.dto.profile.ProfileShortAdminDTO;
import ua.com.gup.dto.profile.manager.UserProfileShortAdminDto;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.mongo.model.filter.OfferFilter;
import ua.com.gup.service.offer.OfferService;
import ua.com.gup.service.profile.ProfilesService;
import ua.com.gup.util.security.SecurityUtils;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/manager")
public class ManagerEndpoint {

    @Autowired
    private ProfilesService profilesService;


    /**
     * get all profile with ROLE_USER
     */

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping(value = "/profiles")
    public ResponseEntity<Page<ProfileShortAdminDTO>> findProfilesShortByFilter(Profile profileFilter, Pageable pageable) {
        Page<ProfileShortAdminDTO> profilesPageable = profilesService.findByRole(CommonUserRole.ROLE_USER, pageable);
        return new ResponseEntity<>(profilesPageable, HttpStatus.OK);
    }

    /**
     * get profile by pulickID
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
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

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping(value = "/profiles/{profilePublicId}/link")
    public ResponseEntity<ProfileDTO> linkProfile(@PathVariable("profilePublicId") String profilePublicId) {
        if (profilesService.hasManager(profilePublicId)) {
            return new ResponseEntity("error.user.exists.manager", HttpStatus.BAD_REQUEST);
        }
        String currentUserId = SecurityUtils.getCurrentUserId();
        profilesService.linkProfile(currentUserId, profilePublicId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * remove user to manager.users
     * set null to user.manager
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping(value = "/profiles/{profilePublicId}/unlink")
    public ResponseEntity<ProfileDTO> unLinkProfile(@PathVariable("profilePublicId") String profilePublicId) {
        String currentUserId = SecurityUtils.getCurrentUserId();
        profilesService.unlinkProfile(currentUserId, profilePublicId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * get users of current manager
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping(value = "/users")
    public ResponseEntity<List<UserProfileShortAdminDto>> getUsers() {
        String currentUserId = SecurityUtils.getCurrentUserId();
        List<UserProfileShortAdminDto> users = profilesService.getManagerUsers(currentUserId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * get user by publicId of current manager
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping(value = "/users/{publicId}")
    public ResponseEntity<ProfileDTO> getUser(@PathVariable("publicId") String publicId) {
        String currentUserId = SecurityUtils.getCurrentUserId();
        ProfileDTO user = profilesService.getManagerUser(currentUserId, publicId);
        if (user == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Autowired
    private OfferService offerService;

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping(value = "/users/{publicId}/offers")
    public ResponseEntity<Page<OfferViewShortDTO>> searchOffers(@PathVariable String publicId, CommonStatus status, Pageable pageable) {
        Page<OfferViewShortDTO> offers = offerService.findByManagerAndPublicIdAndStatus(status, publicId, pageable);
        return new ResponseEntity(offers, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_MANAGER') and hasPermission(#offerUpdateDTO.id, 'offer','EDIT')")
    @GetMapping(value = "/users/{publicId}/offers/{offerId}")
    public ResponseEntity<OfferViewDetailsDTO> searchOffer(@PathVariable("offerId") String offerId) {
        Optional<OfferViewDetailsDTO> one = offerService.findOne(offerId);
        if (one.isPresent()) {
            OfferViewDetailsDTO offer = one.get();
            return new ResponseEntity(offer, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
