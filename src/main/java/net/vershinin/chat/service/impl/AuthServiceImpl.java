package net.vershinin.chat.service.impl;

import com.google.common.collect.Sets;
import lombok.val;
import net.vershinin.chat.event.ConnectionEvent;
import net.vershinin.chat.model.User;
import net.vershinin.chat.service.AuthService;
import net.vershinin.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final Set<User> users = Sets.newHashSet();

    private final ConsumerTokenServices consumerTokenServices;

    private final UserService userService;

    @Autowired
    public AuthServiceImpl(ConsumerTokenServices consumerTokenServices, UserService userService) {
        this.consumerTokenServices = consumerTokenServices;
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

    @Override
    public boolean logout(String token) {
        return consumerTokenServices.revokeToken(token);
    }
}