package net.vershinin.chat.controller;

import lombok.val;
import net.vershinin.chat.event.ConnectionEvent;
import net.vershinin.chat.model.User;
import net.vershinin.chat.service.AuthService;
import net.vershinin.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController()
public class UserController {

    private final AuthService authService;

    private final UserService userService;

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public UserController(AuthService authService, UserService userService, SimpMessagingTemplate messagingTemplate) {
        this.authService = authService;
        this.userService = userService;
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping(value = "/api/v1/me")
    public User getAuthUser(@AuthenticationPrincipal User user) {
        return user;
    }

    @GetMapping(value = "/signout")
    public boolean signOut(Authentication authentication) {
        val details = (OAuth2AuthenticationDetails) authentication.getDetails();
        return authService.logout(details.getTokenValue());
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<User> signUp(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok(user);
    }

    @MessageMapping("/getMessages")
    @SendTo("/users/receive")
    public Set<User> getUsers() {
        return authService.getOnlineUsers();
    }

    @EventListener()
    @SendTo("/users/receive")
    public void onEvent(ConnectionEvent event) {
        val destination = "/users/receive";
        val users = authService.getOnlineUsers();
        switch (event.getCommand()) {
            case SUBSCRIBE:
            case UNSUBSCRIBE:
                messagingTemplate.convertAndSend(destination, users);
        }
    }
}