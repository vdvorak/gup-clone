package ua.com.gup.server.api.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.common.GupLoggedUser;
import ua.com.gup.common.command.CommandException;
import ua.com.gup.common.dto.profile.ProfileDTO;
import ua.com.gup.common.dto.profile.ProfileShortAdminDTO;
import ua.com.gup.dto.profile.*;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.repository.profile.ProfileFilter;
import ua.com.gup.server.command.user.BanUserCommand;
import ua.com.gup.server.command.user.EditUserCommand;
import ua.com.gup.server.command.user.UnbanUserCommand;
import ua.com.gup.server.component.executor.SaleCommandExecutor;
import ua.com.gup.service.filestorage.StorageService;
import ua.com.gup.service.profile.ProfilesService;
import ua.com.gup.util.security.SecurityUtils;

import javax.validation.Valid;

/**
 * Rest controllers for using by administrative module.
 */
@RestController
@RequestMapping("/api/users/admin")
public class ProfileAdminEndpoint {

    @Autowired
    private ProfilesService profilesService;
    @Autowired
    private StorageService storageService;
    @Autowired
    @Lazy
    private SaleCommandExecutor executor;


    @PreAuthorize("hasAuthority('SEARCH_PROFILES_ADMIN')")
    @GetMapping(value = "/profiles")
    public ResponseEntity<Page<ProfileShortAdminDTO>> findProfilesShortByFilter(ProfileFilter profileFilter, Pageable pageable) {
        Page<ProfileShortAdminDTO> profilesPageable = profilesService.findAllProfilesForAdminShort(profileFilter, pageable);
        return new ResponseEntity<>(profilesPageable, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('READ_PROFILE_ADMIN')")
    @GetMapping(value = "/profiles/{profilePublicId}")
    public ResponseEntity<ProfileDTO> findFullProfileByPublicId(@PathVariable("profilePublicId") String profilePublicId) {
        ProfileDTO profile = profilesService.findPrivateProfileDTOForAdminByPublicId(profilePublicId);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('CREATE_PROFILE_ADMIN')")
    @PostMapping(value = "/profiles")
    public ResponseEntity createProfile(@RequestBody CreateProfileDTO profileDTO) {

        if (StringUtils.isEmpty(profileDTO.getEmail())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Profile foundByEmailProfile = profilesService.findProfileByEmail(profileDTO.getEmail());
        if (foundByEmailProfile != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        profilesService.createProfile(profileDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('UPDATE_PROFILE_ADMIN')")
    @PutMapping(value = "/profiles/{profilePublicId}")
    public ResponseEntity updateProfile(@PathVariable String profilePublicId, @RequestBody EditProfileDTO profileDTO) throws CommandException {
        Profile profile = profilesService.findByPublicId(profilePublicId);
        if (profile == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        executor.doCommand(new EditUserCommand(profileDTO.updateModel(profile), profilesService));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('BAN_PROFILE')")
    @PutMapping(value = "/profiles/ban/{id}")
    public ResponseEntity<String> banProfileByID(@PathVariable("id") String id, @Valid @RequestBody BanInfoDto explanation) throws CommandException {
        GupLoggedUser loggedUser = SecurityUtils.getLoggedUser();
        if (id.equals(loggedUser.getId())) {
            return new ResponseEntity<>("cant ban you self", HttpStatus.FORBIDDEN);
        }
        Profile profile = profilesService.findByPublicId(id);
        if (profile == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        executor.doCommand(new BanUserCommand(profile, profilesService,
                explanation.getPrivateExplanation(), explanation.getPublicExplanation()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('UNBAN_PROFILE')")
    @PutMapping(value = "/profiles/unban/{id}")
    public ResponseEntity<String> unbanProfileByID(@PathVariable("id") String id) throws CommandException {
        String loggedUserId = SecurityUtils.getCurrentUserId();
        if (id.equals(loggedUserId)) {
            return new ResponseEntity<>("cant unban you self", HttpStatus.FORBIDDEN);
        }
        Profile profile = profilesService.findByPublicId(id);
        if (profile == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        executor.doCommand(new UnbanUserCommand(profile, profilesService));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/profiles/image/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable("id") String id) {
        if (profilesService.profileExistsByPublicId(id)) {
            storageService.deleteProfileImageByPublicId(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
