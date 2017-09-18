package net.vershinin.chat.service.impl;

import com.google.common.collect.Sets;
import lombok.val;
import net.vershinin.chat.event.ConnectionEvent;
import net.vershinin.chat.model.User;
import net.vershinin.chat.service.AuthService;
import net.vershinin.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private Set<User> users = Sets.newHashSet();

    private final UserService userService;

    @Autowired
    public AuthServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @EventListener
    public void onEvent(ConnectionEvent event){
        val user = userService.findByPrincipal(event.getPrincipal());
        switch (event.getCommand()) {
            case CONNECT:
                users.add(user);
                break;
            case DISCONNECT:
                users.remove(user);
                break;
        }
    }

    @Override
    public Set<User> getOnlineUsers() {
        return users;
    }
}