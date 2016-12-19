package ua.com.itproekt.gup.server.api.rest.invitecodes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.itproekt.gup.bank_api.BankSession;
import ua.com.itproekt.gup.util.SecurityOperations;


@Controller
@RequestMapping("/api/rest/inviteCodes")
public class InviteCodeRestController {

    @Autowired
    private BankSession bankSession;

    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/code/exchange", method = RequestMethod.POST)
    public ResponseEntity<Void> inviteCodeExchange(@RequestBody String inviteCode) {
        String userId = SecurityOperations.getLoggedUserId();
        bankSession.addBonusByUserId(userId, inviteCode);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
