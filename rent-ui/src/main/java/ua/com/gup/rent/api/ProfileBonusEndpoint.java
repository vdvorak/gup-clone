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
import ua.com.gup.common.dto.profile.bonus.CommonBonusCreateDTO;
import ua.com.gup.common.dto.profile.bonus.CommonProfileBonusDTO;
import ua.com.gup.common.model.enumeration.CommonBonusScenarios;
import ua.com.gup.common.model.mongo.operation.OperationType;
import ua.com.gup.rent.command.rent.profile.bonus.CreateProfileBonusCommand;
import ua.com.gup.rent.command.rent.profile.bonus.ExecuteScenarioProfileBonusCommand;
import ua.com.gup.rent.command.rent.profile.bonus.UpdateProfileBonusCommand;
import ua.com.gup.rent.component.executor.RentCommandExecutor;
import ua.com.gup.rent.mapper.ProfileBonusMapper;
import ua.com.gup.rent.model.mongo.profile.bonus.ProfileBonus;
import ua.com.gup.rent.service.dto.profile.bonus.ProfileCreateBonusDTO;
import ua.com.gup.rent.service.dto.profile.bonus.ProfileEditBonusDTO;
import ua.com.gup.rent.service.profile.bonus.ProfileBonusService;
import ua.com.gup.rent.validator.profile.bonus.ProfileBonusDTOValidator;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**
 * REST controller for managing Profile with Bonus.
 */
@RestController
@RequestMapping("/api/users/bonus/")
public class ProfileBonusEndpoint {

    private final Logger logger = LoggerFactory.getLogger(ProfileBonusEndpoint.class);

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
    public ResponseEntity getBonus(@NotNull @PathVariable(name = "id") String id) throws CommandException {
        return new ResponseEntity<>(profileBonusMapper.fromModelToDTO(profileBonusService.findOneById(id)), HttpStatus.OK);
    }


    @ApiOperation(value = "Get All Bonus Type Scenario")
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllBonusScenario() throws CommandException {
        return new ResponseEntity<>(Arrays.asList(CommonBonusScenarios.values()), HttpStatus.OK);
    }

    @ApiOperation(value = "Get All Bonus")
    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllBonus() throws CommandException {
        return new ResponseEntity<>(profileBonusMapper.fromModelToDTO(profileBonusService.findAll()), HttpStatus.OK);

    }


    @ApiOperation(value = "Set activate deactivate bonus")
    @RequestMapping(value = "/activate/{id}/{activate}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity setActivate(@NotNull @PathVariable(name = "id") String id, @NotNull @PathVariable(name = "activate") Boolean activate) throws CommandException {
        ProfileBonus bonus = profileBonusService.findOneById(id);
        if (bonus == null) {
            return new ResponseEntity<>(bonus, HttpStatus.OK);
        }
        bonus.setActive(activate);
        profileBonusService.update(bonus);
        ResponseEntity<CommonProfileBonusDTO> item = new ResponseEntity<>(profileBonusMapper.fromModelToDTO(bonus), HttpStatus.OK);
        UpdateProfileBonusCommand updateProfileBonusCommand = new UpdateProfileBonusCommand(profileBonusService, bonus, OperationType.PROFILE_BONUS_ACTIVATE_OR_DEACTIVATE);
        executor.doCommand(updateProfileBonusCommand);
        return item;
    }

    @ApiOperation(value = "Create Bonus")
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createBonus(@Valid @RequestBody ProfileCreateBonusDTO createBonusDTO) throws CommandException {
        logger.debug(" create bonus  : {}", createBonusDTO);
        CreateProfileBonusCommand createProfileBonusCommand = new CreateProfileBonusCommand(profileBonusService, profileBonusMapper.fromDTOToModel(createBonusDTO));
        executor.doCommand(createProfileBonusCommand);
        return new ResponseEntity<>(new CommonBonusCreateDTO(createProfileBonusCommand.getObjectId(), ((ProfileBonus) createProfileBonusCommand.getJournalable().getObject()).getCode()), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Edit Bonus")
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity editBonus(@PathVariable(name = "id") String id,
                                    @Valid @RequestBody ProfileEditBonusDTO editBonusDTO) throws CommandException {
        logger.debug("Edit Bonus : {}", editBonusDTO);
        ProfileBonus profileBonus = profileBonusService.findOneById(id);
        if (profileBonus == null) {
            return new ResponseEntity<>(profileBonus, HttpStatus.OK);
        }
        profileBonus = profileBonusMapper.fromDTOToModel(editBonusDTO);

        UpdateProfileBonusCommand updateProfileBonusCommand = new UpdateProfileBonusCommand(profileBonusService, profileBonus, OperationType.PROFILE_BONUS_UPDATE);
        executor.doCommand(updateProfileBonusCommand);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Execute bonus scenario")
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/execute/scenario/{code}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity executeScenario(@PathVariable(name = "code") String code) throws CommandException {
        ProfileBonus profileBonus = profileBonusService.findOneByCode(code);
        if (profileBonus == null) {
            return new ResponseEntity<>(profileBonus, HttpStatus.OK);
        }
        ExecuteScenarioProfileBonusCommand executeScenarioProfileBonusCommand = new ExecuteScenarioProfileBonusCommand(profileBonusService, profileBonus);
        executor.doCommand(executeScenarioProfileBonusCommand);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}




