package ua.com.gup.rent.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/api/users")
public class UserEndpoint {

    @Autowired
    private Environment e;
//    @Autowired
//    private ProfilesService profilesService;


    @GetMapping(path = "/authenticate")
    public String isAuthenticated(HttpServletRequest request) {
        String referrer = request.getHeader("referer");
        return !StringUtils.isEmpty(referrer) ? "redirect:" + referrer : "redirect:" + e.getRequiredProperty("application.url");
    }

    @GetMapping(path = "/current")
    public ResponseEntity getPrincipal() throws IOException {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Object principal = authentication.getPrincipal();
        if ("anonymousUser".equals(principal)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

//        ProfileDTO profile = profilesService.findPrivateProfileByEmailAndUpdateLastLoginDate(((LoggedUser)principal).getUsername());
        return new ResponseEntity<>(principal, HttpStatus.OK);
    }
}
