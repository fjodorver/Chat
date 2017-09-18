package net.vershinin.chat.service;

import net.vershinin.chat.model.User;

import java.util.Set;

public interface AuthService {

    Set<User> getOnlineUsers();
}