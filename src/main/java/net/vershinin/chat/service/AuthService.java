package net.vershinin.chat.service;

import net.vershinin.chat.model.User;

import java.security.Principal;
import java.util.Set;

public interface AuthService {

    Set<User> getOnlineUsers();

    boolean logout(String token);
}