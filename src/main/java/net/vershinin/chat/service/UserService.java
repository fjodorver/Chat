package net.vershinin.chat.service;

import net.vershinin.chat.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void save(User user);
}