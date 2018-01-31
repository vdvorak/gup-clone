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
import ua.com.gup.rent.component.executor.RentCommandExecutor;
import ua.com.gup.rent.service.dto.profile.bonus.ProfileCreateBonusDTO;
import ua.com.gup.rent.service.dto.profile.bonus.ProfileEditBonusDTO;
import ua.com.gup.rent.service.profile.bonus.ProfileBonusService;
import ua.com.gup.rent.validator.profile.bonus.ProfileBonusDTOValidator;

import javax.validation.Valid;

/**
 * REST controller for managing Profile with Bonus.
 */
@Slf4j
@RestController
@RequestMapping("/api/rest/profilesService/profile/bonus/")
public class ProfileBonusEndpoint {

    @Autowired
    private ProfileBonusService profileBonusService;

    @Autowired
    private ProfileBonusDTOValidator profileBonusDTOValidator;

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
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity getBonus(@PathVariable(name = "name") String name) throws CommandException {
        ProfileEditBonusDTO profileBonusDTO = profileBonusService.findOneByName(name);
        if (profileBonusDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // UpdateRentOfferCommand updateRentOfferCommand = new UpdateRentOfferCommand(offerService, offerUpdateDTO, offerService.getRentOfferIdBySeoUrl(seoUrl));
        //   executor.doCommand(updateRentOfferCommand);
        return new ResponseEntity<>(profileBonusDTO,HttpStatus.OK);
    }

    @ApiOperation(value = "Create Bonus")
    // @PreAuthorize("hasAuthority('CREATE_OFFER')")
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createBonus(@Valid @RequestBody ProfileCreateBonusDTO createBonusDTO) throws CommandException {
        log.debug(" create bonus  : {}", createBonusDTO);
        // CreateRentOfferCommand createRentOfferCommand = new CreateRentOfferCommand(offerService, offerCreateDTO);
        // executor.doCommand(createRentOfferCommand);
        //  return new ResponseEntity<>(new CommonCreateDTO(createRentOfferCommand.getObjectId()), HttpStatus.CREATED);
         return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Edit Bonus")
    @RequestMapping(value = "/edit/{name}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity editBonus(@PathVariable(name = "name") String name,
                                    @Valid @RequestBody ProfileEditBonusDTO editBonusDTO) throws CommandException {
        log.debug("Edit Bonus : {}", editBonusDTO);
        ProfileEditBonusDTO profileBonusDTO = profileBonusService.findOneByName(name);
        if (profileBonusDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // UpdateRentOfferCommand updateRentOfferCommand = new UpdateRentOfferCommand(offerService, offerUpdateDTO, offerService.getRentOfferIdBySeoUrl(seoUrl));
        //   executor.doCommand(updateRentOfferCommand);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}




