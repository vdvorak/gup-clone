package ua.com.itproekt.gup.server.api.rest.finance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.bank_api.BankSession;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.SecurityOperations;

import java.util.Map;

/**
 * @author Kobylyatskyy Alexander
 */
@RestController
@RequestMapping("/finance")
public class LiqPay {

    BankSession session = new BankSession();

    @Autowired
    ProfilesService profilesService;


    /**
     * This method give you two parameters for LiqPay.
     *
     * @param amount - the amount.
     * @return - two result values or nothing if was something wrong.
     */
    @RequestMapping(value = "/account/getLiqPayParam", method = RequestMethod.POST)
    @ResponseBody
    public String[] getLiqPayParam(@RequestParam("amount") Long amount) {

        String loggedUserId = SecurityOperations.getLoggedUserId();

        if (loggedUserId != null) {
            Map<String, String> result = session.liqPayGenerateParamForHtmlForm(loggedUserId, amount);
            return new String[]{result.get("data"), result.get("signature")};
        } else {
            return new String[]{};
        }

    }


}
