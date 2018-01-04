package ua.com.gup.server.api.profile;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.gup.common.dto.profile.ProfileDTO;
import ua.com.gup.service.profile.ProfilesService;
import ua.com.gup.util.security.SecurityUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping(path = "/api/users")
public class UserEndpoint {

    @Autowired
    private Environment e;
    @Autowired
    private ProfilesService profilesService;


    @GetMapping(path = "/authenticate")
    public String isAuthenticated(HttpServletRequest request) {
        String referrer = request.getHeader("referer");
        return !StringUtils.isEmpty(referrer) ? "redirect:" + referrer : "redirect:" + e.getRequiredProperty("application.url");
    }

    @GetMapping(path = "/current")
    public ResponseEntity getPrincipal() throws IOException {                
        if(!SecurityUtils.isAuthenticated()){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        ProfileDTO profile = profilesService.findPrivateProfileDTOByPublicId(SecurityUtils.getLoggedUser().getPublicId());
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }
}
