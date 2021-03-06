package net.vershinin.chat.service.impl;

import net.vershinin.chat.model.User;
import net.vershinin.chat.repository.UserRepository;
import net.vershinin.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByPrincipal(Principal principal) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException(""));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(""));
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}