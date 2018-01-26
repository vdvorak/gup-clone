/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.gup.api.oauth2;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.com.gup.common.model.enumeration.CommonUserType;
import ua.com.gup.common.model.security.Role;
import ua.com.gup.event.OnForgetPasswordEvent;
import ua.com.gup.event.OnInitialRegistrationByEmailEvent;
import ua.com.gup.model.RegisterProfile;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.mongo.composition.domain.verification.VerificationToken;
import ua.com.gup.mongo.model.login.FormChangePassword;
import ua.com.gup.service.UserService;
import ua.com.gup.service.VerificationTokenService;

import javax.validation.Valid;
import java.util.Date;

/**
 * @author dimka
 */
@Controller
public class RegisterController {

    @Autowired
    private UserService userService;
    @Autowired
    private VerificationTokenService verificationTokenService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView goToRegistration() {
        ModelAndView modelAndView = new ModelAndView("register", "user", new RegisterProfile());
        modelAndView.addObject("title", "register.title");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute(name = "user") @Valid RegisterProfile registerProfile, BindingResult bindingResult) {
        //validate if email already exists 
        if (userService.profileExistsWithEmail(registerProfile.getEmail().toLowerCase())) {
            bindingResult.rejectValue("email", "email.error.exists");
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }

        Profile profile = new Profile();
        profile.setFirstname(registerProfile.getName());
        profile.setLastname(registerProfile.getSurname());
        profile.setPassword(registerProfile.getPassword());
        profile.setEmail(registerProfile.getEmail().toLowerCase());
        profile.setSocWendor("GUP");
        profile.setUserType(CommonUserType.LEGAL_ENTITY);
        profile.setActive(false);
        profile.getUserRoles().add(Role.ROLE_USER);
        userService.createProfile(profile);
        eventPublisher.publishEvent(new OnInitialRegistrationByEmailEvent(profile));
        return "register/success";
    }

    @RequestMapping(value = "/register/confirm", method = RequestMethod.GET)
    public ModelAndView registerConfirm(@RequestParam("token") String token) {
        VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);
        ModelAndView modelAndView = new ModelAndView("register/confirm");
        if (verificationToken == null) {
            modelAndView.addObject("error", "verification.token.notfound.exception");
            return modelAndView;
        }

        if (verificationToken.getExpiryDate().before(new Date())) {
            modelAndView.addObject("error", "verification.token.expired.exception");
            return modelAndView;
        }
        Profile profile = userService.findById(verificationToken.getUserId());
        if (!Boolean.TRUE.equals(profile.getActive())) {
            profile.setActive(Boolean.TRUE);
            userService.updateProfile(profile);
        }
        verificationTokenService.deleteToken(verificationToken);
        modelAndView.addObject("username",profile.getEmail());
        return modelAndView;
    }

    @RequestMapping(value = "/register/password/restore", method = RequestMethod.GET)
    public ModelAndView restorePassword() {
        ModelAndView modelAndView = new ModelAndView("register/password/restore", "password", new RegisterProfile());
        return modelAndView;
    }

    @RequestMapping(value = "/register/password/restore", method = RequestMethod.POST)
    public ModelAndView restorePassword(@RequestParam String email) {

        ModelAndView modelAndView = new ModelAndView("register/password/restore");

        if (StringUtils.isEmpty(email)) {
            modelAndView.addObject("error", "account.restore.email.required");
            return modelAndView;
        }

        if (!EmailValidator.getInstance().isValid(email)) {
            modelAndView.addObject("error", "account.restore.email.invalid");
            return modelAndView;
        }

        Profile profile = userService.findProfileByEmail(email);
        if (profile == null) {
            modelAndView.addObject("error", "account.restore.email.notfound");
            return modelAndView;
        }

        eventPublisher.publishEvent(new OnForgetPasswordEvent(profile));
        return new ModelAndView("register/password/restore/success");

    }

    @RequestMapping(value = "/register/password/reset", method = RequestMethod.GET)
    public ModelAndView resetPasswordByToken(@RequestParam("token") String token) {

        ModelAndView modelAndView = new ModelAndView("register/password/reset", "formChangePassword", new FormChangePassword(token, null, null));

        VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);

        if (verificationToken == null) {
            modelAndView.addObject("error", "verification.token.notfound.exception");
            return modelAndView;
        }

        if (verificationToken.getExpiryDate().before(new Date())) {
            modelAndView.addObject("error", "verification.token.expired.exception");
            return modelAndView;
        }

//        Profile profile = userService.findById(verificationToken.getUserId());
//        profile.setPassword(passwordEncoder.encode(fcp.getNewPassword()));
//        userService.editProfile(profile);
//
//        verificationTokenService.deleteToken(verificationToken);
        return modelAndView;
    }


    @RequestMapping(value = "/register/password/reset", method = RequestMethod.POST)
    public ModelAndView resetPasswordByToken(@ModelAttribute @Valid FormChangePassword form, BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView("register/password/reset");
        if (bindingResult.hasErrors()) {
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


        Profile profile = userService.findById(verificationToken.getUserId());
        profile.setPassword(passwordEncoder.encode(form.getNewPassword()));
        userService.updateProfile(profile);

        verificationTokenService.deleteToken(verificationToken);
        return new ModelAndView("register/password/reset/success");
    }

}
