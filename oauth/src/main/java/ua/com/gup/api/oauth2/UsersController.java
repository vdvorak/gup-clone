/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.gup.api.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.mongo.model.login.LoggedUser;
import ua.com.gup.service.UserService;

@RestController
@RequestMapping(path = "/api/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/exists")
    public ResponseEntity userExists(@RequestParam(name = "login") String login) {
        boolean profileExistsWithEmail = userService.profileExistsWithEmail(login.toLowerCase());
        return new ResponseEntity(profileExistsWithEmail, HttpStatus.OK);
    }

    @GetMapping(path = "/principal")
    public @ResponseBody
    LoggedUser getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        return (LoggedUser) principal;
    }


}
