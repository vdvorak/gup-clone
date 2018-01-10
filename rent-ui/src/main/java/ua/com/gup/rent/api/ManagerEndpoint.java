package ua.com.gup.rent.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.common.dto.profile.ProfileShortAdminDTO;
import ua.com.gup.common.dto.profile.manager.event.ManagerActionDto;
import ua.com.gup.common.mapper.manager.action.ManagerActionMapper;
import ua.com.gup.common.model.enumeration.CommonStatus;
import ua.com.gup.common.model.enumeration.CommonUserRole;
import ua.com.gup.common.model.filter.ManagerActionFilter;
import ua.com.gup.common.model.mongo.profile.manager.event.ManagerAction;
import ua.com.gup.common.service.ManagerActionService;
import ua.com.gup.rent.service.dto.rent.offer.profile.RentOfferProfileDTO;
import ua.com.gup.rent.service.dto.rent.offer.profile.manager.*;
import ua.com.gup.rent.service.dto.rent.offer.view.RentOfferViewDetailsDTO;
import ua.com.gup.rent.service.dto.rent.offer.view.RentOfferViewShortDTO;
import ua.com.gup.rent.service.profile.ProfileFilter;
import ua.com.gup.rent.service.profile.RentOfferProfilesService;
import ua.com.gup.rent.service.rent.RentOfferService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/managers")
public class ManagerEndpoint {

    @Autowired
    private RentOfferProfilesService profilesService;
    @Autowired
    private RentOfferService offerService;
    @Autowired
    private ManagerActionService managerActionService;

    @Autowired
    private ManagerActionMapper managerActionMapper;

    /**
     * get all managers with ROLE_USER
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping(value = {"", "/"})
    public ResponseEntity<Page<ProfileShortAdminDTO>> findManagersShortByFilter(ProfileFilter filter, Pageable pageable) {
        Page<ProfileShortAdminDTO> profilesPageable = profilesService.findByRole(filter, CommonUserRole.ROLE_MANAGER, pageable);
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

    //@PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
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
    public ResponseEntity<UserProfileManagerDto> findFullProfileByPublicId(@PathVariable("profilePublicId") String profilePublicId) {
        UserProfileManagerDto profile = profilesService.findUserProfile(profilePublicId);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    /**
     * link user to manager
     * <p>
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

        if (!profilesService.profileExistsByPublicId(profilePublicId)) {
            return new ResponseEntity("error.user.not.found", HttpStatus.NOT_FOUND);
        }

        if (!profilesService.profileExistsByPublicId(managerPublicId)) {
            return new ResponseEntity("error.manager.not.found", HttpStatus.NOT_FOUND);
        }

        if (!profilesService.hasManager(profilePublicId)) {
            return new ResponseEntity("error.user.not.exists.manager", HttpStatus.BAD_REQUEST);
        }
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
    //@PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @GetMapping(value = "/{managerPublicId}/users/{userPublicId}")
    public ResponseEntity<UserProfileManagerDto> getUser(
            @PathVariable("managerPublicId") String managerPublicId,
            @PathVariable("userPublicId") String userPublicId) {

        if (!profilesService.profileExistsByPublicId(userPublicId)) {
            return new ResponseEntity("error.user.not.found", HttpStatus.NOT_FOUND);
        }

        if (!profilesService.profileExistsByPublicId(managerPublicId)) {
            return new ResponseEntity("error.manager.not.found", HttpStatus.NOT_FOUND);
        }

        UserProfileManagerDto user = profilesService.findUserProfile(userPublicId);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * update user contact_info
     */
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @RequestMapping(value = "/{managerPublicId}/users/{userPublicId}/contactinfo", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity creeateContactInfo(
            @PathVariable("managerPublicId") String managerPublicId,
            @PathVariable("userPublicId") String userPublicId,
            @RequestBody ManagerContactInfoEditDto contactInfoEditDto) {

        if (!profilesService.profileExistsByPublicId(userPublicId)) {
            return new ResponseEntity("error.user.not.found", HttpStatus.NOT_FOUND);
        }

        if (!profilesService.profileExistsByPublicId(managerPublicId)) {
            return new ResponseEntity("error.manager.not.found", HttpStatus.NOT_FOUND);
        }

        profilesService.updateUserContactInfoManager(userPublicId, contactInfoEditDto);
        return new ResponseEntity<>(HttpStatus.OK);
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

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @GetMapping(value = "/{managerPublicId}/actions")
    public ResponseEntity<Page<ManagerActionDto>> searchActions(
            @PathVariable("managerPublicId") String managerPublicId,
            ManagerActionFilter filter,
            Pageable pageable) {

        filter.setManagerPublicId(managerPublicId);
        Page<ManagerActionDto> actions = managerActionService.findAll(filter, pageable);
        return new ResponseEntity(actions, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @GetMapping(value = "/{managerPublicId}/actions/{actionId}")
    public ResponseEntity<ManagerActionDto> getAction(
            @PathVariable("managerPublicId") String managerPublicId,
            @PathVariable("actionId") String actionId) {
        if(!managerActionService.exists(actionId)){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        ManagerActionDto action= managerActionService.getById(actionId);

        return new ResponseEntity(action, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @PostMapping(value = "/{managerPublicId}/actions")
    public ResponseEntity<ManagerActionDto> createAction(
            @PathVariable("managerPublicId") String managerPublicId,
            @RequestBody @Valid ManagerActionDto dto) {

        dto.setId(null);
        if(StringUtils.isEmpty(dto.getManagerPublicId())){
            dto.setManagerPublicId(managerPublicId);
        }
        ManagerAction action = managerActionService.create(dto);

        return new ResponseEntity(action.getId(), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @PutMapping(value = "/{managerPublicId}/actions/{actionId}")
    public ResponseEntity<ManagerActionDto> editAction(
            @PathVariable("managerPublicId") String managerPublicId,
            @PathVariable("actionId") String actionId,
            @RequestBody @Valid ManagerActionDto dto) {

        if(!managerActionService.exists(actionId)){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        if(StringUtils.isEmpty(dto.getManagerPublicId())){
            dto.setManagerPublicId(managerPublicId);
        }
        dto.setId(actionId);
        managerActionService.save(dto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @DeleteMapping(value = "/{managerPublicId}/actions/{actionId}")
    public ResponseEntity<ManagerActionDto> deleteAction(
            @PathVariable("managerPublicId") String managerPublicId,
            @PathVariable("actionId") String actionId) {

        if(!managerActionService.exists(actionId)){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        managerActionService.remove(actionId);
        return new ResponseEntity(HttpStatus.OK);
    }

}
