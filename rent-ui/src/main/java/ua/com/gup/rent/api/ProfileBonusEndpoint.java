package ua.com.gup.rent.api;


import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.common.command.CommandException;
import ua.com.gup.common.dto.CommonCreateDTO;
import ua.com.gup.common.dto.profile.bonus.CommonProfileBonusDTO;
import ua.com.gup.common.model.enumeration.CommonBonusScenarios;
import ua.com.gup.common.model.mongo.operation.OperationType;
import ua.com.gup.rent.command.rent.profile.bonus.CreateProfileBonusCommand;
import ua.com.gup.rent.command.rent.profile.bonus.UpdateProfileBonusCommand;
import ua.com.gup.rent.component.executor.RentCommandExecutor;
import ua.com.gup.rent.mapper.ProfileBonusMapper;
import ua.com.gup.rent.model.mongo.profile.bonus.ProfileBonus;
import ua.com.gup.rent.service.dto.profile.bonus.ProfileCreateBonusDTO;
import ua.com.gup.rent.service.dto.profile.bonus.ProfileEditBonusDTO;
import ua.com.gup.rent.service.profile.bonus.ProfileBonusService;
import ua.com.gup.rent.validator.profile.bonus.ProfileBonusDTOValidator;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * REST controller for managing Profile with Bonus.
 */
@Slf4j
@RestController
@RequestMapping("/api/users/bonus/")
public class ProfileBonusEndpoint {

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
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBonus(@PathVariable(name = "id") String id) throws CommandException {
        CommonProfileBonusDTO profileBonusDTO = profileBonusMapper.fromModelToDTO(profileBonusService.findOneById(id));
        if (profileBonusDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(profileBonusDTO, HttpStatus.OK);
    }


    @ApiOperation(value = "Get All Bonus Type Scenario")
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllBonusScenario() throws CommandException {
        List<CommonBonusScenarios> allBonusScenario = Arrays.asList(CommonBonusScenarios.values());
        if (allBonusScenario == null || allBonusScenario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(allBonusScenario, HttpStatus.OK);

    }

    @ApiOperation(value = "Get All Bonus")
    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllBonus() throws CommandException {
        List<ProfileBonus> bonusList = profileBonusService.findAll();
        if (bonusList == null || bonusList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<CommonProfileBonusDTO> profileBonusDTOList = profileBonusMapper.fromModelToDTO(bonusList);
        ResponseEntity<List<CommonProfileBonusDTO>> listItems = new ResponseEntity<>(profileBonusDTOList, HttpStatus.OK);

        return new ResponseEntity<>(listItems, HttpStatus.OK);
    }


    @ApiOperation(value = "Set activate deactivate bonus")
    @RequestMapping(value = "/activate/{id}/{activate}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity setActivate(@PathVariable(name = "id") String id, @PathVariable(name = "activate") Boolean activate) throws CommandException {
        ProfileBonus bonus = profileBonusService.findOneById(id);
        if (bonus == null || bonus.getId() != null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bonus.setActive(activate);
        profileBonusService.update(bonus);
        ResponseEntity<CommonProfileBonusDTO> item = new ResponseEntity<>(profileBonusMapper.fromModelToDTO(bonus), HttpStatus.OK);
        UpdateProfileBonusCommand updateProfileBonusCommand = new UpdateProfileBonusCommand(profileBonusService, bonus, OperationType.PROFILE_BONUS_ACTIVATE_OR_DEACTIVATE);
        executor.doCommand(updateProfileBonusCommand);
        return item;
    }

    @ApiOperation(value = "Create Bonus")
    // @PreAuthorize("hasAuthority('CREATE_OFFER')")
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createBonus(@Valid @RequestBody ProfileCreateBonusDTO createBonusDTO) throws CommandException {
        log.debug(" create bonus  : {}", createBonusDTO);
        CreateProfileBonusCommand createProfileBonusCommand = new CreateProfileBonusCommand(profileBonusService, profileBonusMapper.fromDTOToModel(createBonusDTO));
        executor.doCommand(createProfileBonusCommand);
        return new ResponseEntity<>(new CommonCreateDTO(createProfileBonusCommand.getObjectId()), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Edit Bonus")
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity editBonus(@PathVariable(name = "id") String id,
                                    @Valid @RequestBody ProfileEditBonusDTO editBonusDTO) throws CommandException {
        log.debug("Edit Bonus : {}", editBonusDTO);
        ProfileBonus profileBonus = profileBonusService.findOneById(id);
        if (profileBonus == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        profileBonus = profileBonusMapper.fromDTOToModel(editBonusDTO);

        UpdateProfileBonusCommand updateProfileBonusCommand = new UpdateProfileBonusCommand(profileBonusService, profileBonus, OperationType.PROFILE_BONUS_UPDATE);
        executor.doCommand(updateProfileBonusCommand);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}




