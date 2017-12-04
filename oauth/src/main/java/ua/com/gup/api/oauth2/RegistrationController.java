/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.gup.api.oauth2;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.com.gup.config.annotation.Email;
import ua.com.gup.dto.profile.ProfileDTO;
import ua.com.gup.dto.profile.RegisterProfileDTO;
import ua.com.gup.event.OnForgetPasswordEvent;
import ua.com.gup.event.OnInitialRegistrationByEmailEvent;
import ua.com.gup.exception.VerificationTokenExpiredException;
import ua.com.gup.exception.VerificationTokenNotFoundException;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.mongo.composition.domain.verification.VerificationToken;
import ua.com.gup.mongo.model.enumeration.UserRole;
import ua.com.gup.mongo.model.enumeration.UserType;
import ua.com.gup.mongo.model.login.FormChangePassword;
import ua.com.gup.service.profile.ProfilesService;
import ua.com.gup.service.profile.VerificationTokenService;

/**
 *
 * @author dimka
 */
@Controller
public class RegistrationController {

    @Autowired
    private ProfilesService profilesService;
    @Autowired
    private VerificationTokenService verificationTokenService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView goToRegistration() {
        System.out.println("test go to register");

        ModelAndView modelAndView = new ModelAndView("/register", "user", new RegisterProfileDTO());
        modelAndView.addObject("title", "register.title");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute(name = "user") @Valid RegisterProfileDTO registerProfileDTO, BindingResult bindingResult) {
        //validate if email already exists 
        if (profilesService.profileExistsWithEmail(registerProfileDTO.getEmail().toLowerCase())) {
            bindingResult.rejectValue("email", "email.error.exists");
        }

        if (bindingResult.hasErrors()) {
            return "/register";
        }

        Profile profile = new Profile();
        profile.setPassword(registerProfileDTO.getPassword());
        profile.setEmail(registerProfileDTO.getEmail().toLowerCase());
        profile.setSocWendor("GUP");
        profile.setUserType(UserType.LEGAL_ENTITY);
        profile.setActive(false);
        profile.getUserRoles().add(UserRole.ROLE_USER);
        profilesService.createProfile(profile);
        eventPublisher.publishEvent(new OnInitialRegistrationByEmailEvent(profile));
        return "/register/success";
    }

    @RequestMapping(value = "/register/confirm", method = RequestMethod.GET)
    public ModelAndView registerConfirm(@RequestParam("token") String token) {
        VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);
        ModelAndView modelAndView = new ModelAndView("/register/confirm");
        if (verificationToken == null) {
            modelAndView.addObject("error", "verification.token.notfound.exception");
            return modelAndView;
        }

        if (verificationToken.getExpiryDate().before(new Date())) {
            modelAndView.addObject("error", "verification.token.expired.exception");
            return modelAndView;
        }
        Profile profile = profilesService.findById(verificationToken.getUserId());
        if (!Boolean.TRUE.equals(profile.getActive())) {
            profile.setActive(Boolean.TRUE);
            profilesService.editProfile(profile);
        }
        verificationTokenService.deleteToken(verificationToken);
        return modelAndView;
    }

    @RequestMapping(value = "/register/password/restore", method = RequestMethod.GET)
    public ModelAndView restorePassword() {
        ModelAndView modelAndView = new ModelAndView("/register/password/restore", "password", new RegisterProfileDTO());
        return modelAndView;
    }

    @RequestMapping(value = "/register/password/restore", method = RequestMethod.POST)
    public ModelAndView restorePassword(@RequestParam String email) {
        //ModelAndView modelAndView = new ModelAndView("/register/password/restore");
        ModelAndView modelAndView = new ModelAndView("/register/password/restore");

        if (StringUtils.isEmpty(email)) {
            modelAndView.addObject("error", "account.restore.email.required");
            return modelAndView;
        }

        if (!EmailValidator.getInstance().isValid(email)) {
            modelAndView.addObject("error", "account.restore.email.invalid");
            return modelAndView;
        }

        Profile profile = profilesService.findProfileByEmail(email);
        if (profile == null) {
            modelAndView.addObject("error", "account.restore.email.notfound");
            return modelAndView;
        }

        eventPublisher.publishEvent(new OnForgetPasswordEvent(profile));
        return new ModelAndView("/register/password/restore/success");

    }

    @RequestMapping(value = "/register/password/reset", method = RequestMethod.GET)
    public ModelAndView resetPasswordByToken(@RequestParam("token") String token) {

        ModelAndView modelAndView = new ModelAndView("/register/password/reset", "formChangePassword", new FormChangePassword(token, null, null));       

        VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);

        if (verificationToken == null) {            
            modelAndView.addObject("error", "verification.token.notfound.exception");
            return modelAndView;
        }

        if (verificationToken.getExpiryDate().before(new Date())) {
            modelAndView.addObject("error", "verification.token.expired.exception");
            return modelAndView;
        }

//        Profile profile = profilesService.findById(verificationToken.getUserId());
//        profile.setPassword(passwordEncoder.encode(fcp.getNewPassword()));
//        profilesService.editProfile(profile);
//
//        verificationTokenService.deleteToken(verificationToken);
        return modelAndView;
    }
    
    
    @RequestMapping(value = "/register/password/reset", method = RequestMethod.POST)
    public ModelAndView resetPasswordByToken(@ModelAttribute @Valid FormChangePassword form, BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView("/register/password/reset");       
        if(bindingResult.hasErrors()){
            return modelAndView;
        }

        
        if (!form.getPassword().equals(form.getNewPassword())) {
            bindingResult.rejectValue("", "account.reset.password.notequal");            
            return modelAndView;
        }
        
        VerificationToken verificationToken = verificationTokenService.getVerificationToken(form.getToken());

        if (verificationToken == null) {
            bindingResult.rejectValue("", "verification.token.notfound.exception");
            return modelAndView;
        }

        if (verificationToken.getExpiryDate().before(new Date())) {
            bindingResult.rejectValue("", "verification.token.expired.exception");            
            return modelAndView;
        }
        
        

        Profile profile = profilesService.findById(verificationToken.getUserId());
        profile.setPassword(passwordEncoder.encode(form.getNewPassword()));
        profilesService.editProfile(profile);

        verificationTokenService.deleteToken(verificationToken);
        return new ModelAndView("/register/password/reset/success");
    }

}
