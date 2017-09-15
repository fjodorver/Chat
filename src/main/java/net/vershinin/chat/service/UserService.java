package net.vershinin.chat.service;

import net.vershinin.chat.model.User;

public interface UserService {

    User findByUsername(String username);

    void save(User user);
}