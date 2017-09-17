package net.vershinin.chat.service;

import java.security.Principal;

public interface AuthService {

    void logout(Principal principal);
}
