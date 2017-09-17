package net.vershinin.chat.controller;

import net.vershinin.chat.model.User;
import net.vershinin.chat.service.AuthService;
import net.vershinin.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Set;

@RestController()
public class UserController {

    private final UserService userService;

    private final AuthService authService;

    @Autowired
    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping("/logout")
    public void logout(Principal principal) {
        authService.logout(principal);
    }
}