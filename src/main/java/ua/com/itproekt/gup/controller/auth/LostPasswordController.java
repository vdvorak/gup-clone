package ua.com.itproekt.gup.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.profile.VerificationTokenService;

/**
 * Created by Zver on 28.12.2015.
 */

@Controller
public class LostPasswordController {

    @Autowired
    VerificationTokenService verificationTokenService;

    @Autowired
    ProfilesService profilesService;

    @RequestMapping(value = "/restore")
    public String resetPasswordForm() {
        return "restorePassword";
    }

    @RequestMapping(value = "/newPasswordForm", method = RequestMethod.GET)
    public String resetPasswordForm(@RequestParam String token, Model model) {

        model.addAttribute("token", token);
        return "newPasswordForm";
    }

    @RequestMapping(value = "/sendLostPasswordEmail", method = RequestMethod.POST)
    public String sendLostPasswordEmail(@RequestParam String email, Model model) {

        if (profilesService.profileExistsWithEmail(email)) {
            verificationTokenService.sendLostPasswordToken(email);
            model.addAttribute("message", "Подтверждение на изменение пароля выслано на вашу почту");
        } else {
            model.addAttribute("message", "Пользователя с такой почтой нет");
        }

        return "temp";
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public String resetPassword(@RequestParam String token, @RequestParam String newPassword, Model model) {

//        VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);

        verificationTokenService.resetPassword(token, newPassword);

        model.addAttribute("message", "Пароль изменен");
        return "temp";
    }
}
