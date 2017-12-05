/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.gup.api.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.gup.service.profile.ProfilesService;

@RestController()
public class UserServiceController {

    @Autowired
    private ProfilesService profilesService;

    @RequestMapping(value = "/api/users/exists", method = RequestMethod.GET)
    public ResponseEntity userExists(@RequestParam(name = "login") String login) {
        boolean profileExistsWithEmail = profilesService.profileExistsWithEmail(login.toLowerCase());
        return new ResponseEntity(profileExistsWithEmail, HttpStatus.OK);                
    }
    
    
}
