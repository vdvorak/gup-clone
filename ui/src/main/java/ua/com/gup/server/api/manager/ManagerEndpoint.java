package ua.com.gup.server.api.manager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.common.model.enumeration.CommonUserRole;
import ua.com.gup.dto.profile.ProfileDTO;
import ua.com.gup.dto.profile.ProfileShortAdminDTO;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.service.profile.ProfilesService;
import ua.com.gup.util.security.SecurityUtils;

@RestController
@RequestMapping("/api/manager")
public class ManagerEndpoint {

    @Autowired
    private ProfilesService profilesService;


    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping(value = "/profiles")
    public ResponseEntity<Page<ProfileShortAdminDTO>> findProfilesShortByFilter(Profile profileFilter, Pageable pageable) {
        Page<ProfileShortAdminDTO> profilesPageable = profilesService.findByRole(CommonUserRole.ROLE_USER, pageable);
        return new ResponseEntity<>(profilesPageable, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping(value = "/profiles/{profilePublicId}")
    public ResponseEntity<ProfileDTO> findFullProfileByPublicId(@PathVariable("profilePublicId") String profilePublicId) {
        ProfileDTO profile = profilesService.findPrivateProfileDTOForAdminByPublicId(profilePublicId);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }



    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping(value = "/profiles/{profilePublicId}/link")
    public ResponseEntity<ProfileDTO> linkProfile(@PathVariable("profilePublicId") String profilePublicId) {
        String currentUserId = "5a250d6976b8b73a70d4ee7a";SecurityUtils.getCurrentUserId();
        profilesService.linkProfile(currentUserId, profilePublicId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping(value = "/profiles/{profilePublicId}/unlink")
    public ResponseEntity<ProfileDTO> unLinkProfile(@PathVariable("profilePublicId") String profilePublicId) {
        String currentUserId = "5a250d6976b8b73a70d4ee7a"; SecurityUtils.getCurrentUserId();
        profilesService.unlinkProfile(currentUserId, profilePublicId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
