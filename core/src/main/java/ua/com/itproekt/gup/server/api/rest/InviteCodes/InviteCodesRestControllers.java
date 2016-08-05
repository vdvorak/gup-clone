package ua.com.itproekt.gup.server.api.rest.invitecodes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.itproekt.gup.bank_api.BankSession;
import ua.com.itproekt.gup.service.profile.ProfilesService;

@Controller
public class InviteCodesRestControllers {


    @Autowired
    ProfilesService profilesService;

    @Autowired
    BankSession bankSession;


    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/inviteCode/exchange/{userId}", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteOffer(@PathVariable String userId, @RequestBody String inviteCode) {


        // Запрос в банк на обмен invite кода


        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
