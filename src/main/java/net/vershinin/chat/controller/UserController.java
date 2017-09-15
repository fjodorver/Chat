package net.vershinin.chat.controller;

import net.vershinin.chat.model.User;
import net.vershinin.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.boot.actuate.audit.listener.AuditApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Set;

@RestController("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, name = "/")
    public void signIn(User user){
        userService.save(user);
    }

    @RequestMapping("/")
    public Set<User> getOnlineUsers(){
        throw new NotImplementedException();
    }

    @EventListener
    public void onAuditEvent(AuditApplicationEvent applicationEvent){
        AuditEvent event = applicationEvent.getAuditEvent();
    }
}