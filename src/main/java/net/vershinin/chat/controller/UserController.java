package net.vershinin.chat.controller;

import lombok.val;
import net.vershinin.chat.event.ConnectionEvent;
import net.vershinin.chat.model.Connection;
import net.vershinin.chat.model.Connection.Status;
import net.vershinin.chat.model.User;
import net.vershinin.chat.service.AuthService;
import net.vershinin.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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

    @GetMapping("/api/v1/users/")
    public Set<User> getOnlineUsers(){
        return authService.getOnlineUsers();
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<User> signUp(@RequestBody User user){
        userService.save(user);
        return ResponseEntity.ok(user);
    }

    @EventListener
    @SendTo("/users/receive")
    public void onEvent(ConnectionEvent event){
        val user = userService.findByPrincipal(event.getPrincipal());
        Connection connection;
        switch (event.getCommand()){
            case CONNECT:
                connection = new Connection(Status.CONNECTED, user);
                messagingTemplate.convertAndSend("/users/receive", connection);
                break;
            case DISCONNECT:
                connection = new Connection(Status.DISCONNECTED, user);
                messagingTemplate.convertAndSend("/users/receive", connection);
                break;
        }
    }
}