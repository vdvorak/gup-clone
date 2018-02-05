package ua.com.gup.rent.api;


import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.common.command.CommandException;
import ua.com.gup.common.dto.CommonCreateDTO;
import ua.com.gup.rent.command.rent.profile.bonus.CreateProfileBonusCommand;
import ua.com.gup.rent.command.rent.profile.bonus.UpdateProfileBonusCommand;
import ua.com.gup.rent.component.executor.RentCommandExecutor;
import ua.com.gup.rent.mapper.ProfileBonusMapper;
import ua.com.gup.rent.service.dto.profile.bonus.ProfileCreateBonusDTO;
import ua.com.gup.rent.service.dto.profile.bonus.ProfileEditBonusDTO;
import ua.com.gup.rent.service.profile.bonus.ProfileBonusService;
import ua.com.gup.rent.validator.profile.bonus.ProfileBonusDTOValidator;

import javax.validation.Valid;

/**
 * REST controller for managing Profile with Bonus.
 */
@RestController
@RequestMapping("/api/rest/profilesService/profile/bonus/")
public class ProfileBonusEndpoint {

    final static Logger logger = LoggerFactory.getLogger(ProfileBonusEndpoint.class);

    @Autowired
    private ProfileBonusService profileBonusService;

    @Autowired
    private ProfileBonusDTOValidator profileBonusDTOValidator;

    @Autowired
    private ProfileBonusMapper profileBonusMapper;

    @Autowired
    private RentCommandExecutor executor;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() != null) {
            final Class<?> clazz = binder.getTarget().getClass();
            if (ProfileCreateBonusDTO.class.equals(clazz) || ProfileEditBonusDTO.class.equals(clazz)) {
                binder.addValidators(profileBonusDTOValidator);
            }
        }
    }

    @ApiOperation(value = "Get Bonus")
    @RequestMapping(value = "/get/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBonus(@PathVariable(name = "name") String name) throws CommandException {
        ProfileEditBonusDTO profileBonusDTO = profileBonusService.findOneByName(name);
        if (profileBonusDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(profileBonusDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Create Bonus")
    // @PreAuthorize("hasAuthority('CREATE_OFFER')")
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createBonus(@Valid @RequestBody ProfileCreateBonusDTO createBonusDTO) throws CommandException {
        logger.debug(" create bonus  : {}", createBonusDTO);
        CreateProfileBonusCommand createProfileBonusCommand = new CreateProfileBonusCommand(profileBonusService,  createBonusDTO, profileBonusMapper);
        executor.doCommand(createProfileBonusCommand);
        return new ResponseEntity<>(new CommonCreateDTO(createProfileBonusCommand.getObjectId()), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Edit Bonus")
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/edit/{name}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity editBonus(@PathVariable(name = "name") String name,
                                    @Valid @RequestBody ProfileEditBonusDTO editBonusDTO) throws CommandException {
        logger.debug("Edit Bonus : {}", editBonusDTO);
        ProfileEditBonusDTO profileBonusDTO = profileBonusService.findOneByName(name);
        if (profileBonusDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UpdateProfileBonusCommand updateProfileBonusCommand = new UpdateProfileBonusCommand(profileBonusService, editBonusDTO, profileBonusMapper);
        executor.doCommand(updateProfileBonusCommand);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}




