package ua.com.itproekt.gup.server.api.rest.invitecodes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.bank_api.BankSession;
import ua.com.itproekt.gup.bank_api.entity.InternalTransaction;
import ua.com.itproekt.gup.util.SecurityOperations;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/api/rest/inviteCodes")
public class InviteCodeRestController {

    @Autowired
    private BankSession bankSession;

    /**
     * Method add exchanges invite code on bonus amount for user
     *
     * FUNCTION 'fn_check_bonus_amount(<current_user_id>, <current_code>)'
     *
     * @param inviteCode
     * @return
     */
    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/code/exchange", method = RequestMethod.POST)
    public ResponseEntity<Void> inviteCodeExchange(@RequestBody String inviteCode) {
        String userId = SecurityOperations.getLoggedUserId();
        bankSession.addBonusByUserId(userId, inviteCode);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    /**
     * Method return user bonus balance amount.
     *
     * FUNCTION 'fn_check_bonus_amount(<current_user_id>)'
     *
     * @return
     */
    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/code/exchange", method = RequestMethod.GET)
    public ResponseEntity<String> inviteCodeExchange() {
        String userId = SecurityOperations.getLoggedUserId();
        String bonus = bankSession.getBonusByUserId(userId);
        return new ResponseEntity<>(bonus, HttpStatus.OK);
    }

    /**
     * Method allow to buy paid services for bonus money.
     *
     * FUNCTION 'fn_buy_bonus_account(<current_user_id>, <current_trans_type>, <current_amount>, <current_offer_id>)'
     *
     * @param transType
     * @param cost
     * @param offerId
     * @return
     */
    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/bonus/buy", method = RequestMethod.POST)
    public ResponseEntity<String> buyByBonusAccount(
            @RequestParam("transType") String transType,
            @RequestParam("cost") String cost,
            @RequestParam("offerId") String offerId){
        String userId = SecurityOperations.getLoggedUserId();
        String buy = bankSession.buyByBonusAccount(userId, Integer.valueOf(transType), Integer.valueOf(cost), offerId);

        return new ResponseEntity<>(buy, HttpStatus.OK);
    }

    /**
     * Method return all internal transactions which satisfied input parameters.
     *
     * FUNCTION 'fn_check_internal_transactions(<current_user_id>, <by_type>)'
     *
     * @param transType
     * @return
     * @throws Exception
     */
    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/bonus/buy", method = RequestMethod.GET)
    public ResponseEntity<String> buyByBonusAccount(
            @RequestParam("transType") String transType)
            throws Exception {
        String userId = SecurityOperations.getLoggedUserId();
        String res = bankSession.getAllPendingTransactions(userId, transType);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
