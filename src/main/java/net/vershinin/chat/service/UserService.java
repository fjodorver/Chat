package net.vershinin.chat.service;

import net.vershinin.chat.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.security.Principal;
import java.util.Set;

public interface UserService extends UserDetailsService {

    User findByPrincipal(Principal principal) throws UsernameNotFoundException;

    void save(User user);
}