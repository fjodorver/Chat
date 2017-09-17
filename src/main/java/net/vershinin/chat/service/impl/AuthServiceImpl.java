package net.vershinin.chat.service.impl;

import lombok.val;
import net.vershinin.chat.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class AuthServiceImpl implements AuthService {

    private final TokenStore tokenStore;

    private final AuthorizationServerTokenServices tokenServices;

    @Autowired
    public AuthServiceImpl(TokenStore tokenStore, AuthorizationServerTokenServices tokenServices){
        this.tokenServices = tokenServices;
        this.tokenStore = tokenStore;
    }

    @Override
    public void logout(Principal principal) {
        val authentication = (OAuth2Authentication) principal;
        val accessToken = tokenServices.getAccessToken(authentication);
        val refreshToken = accessToken.getRefreshToken();
        tokenStore.removeAccessToken(accessToken);
        tokenStore.removeRefreshToken(refreshToken);
    }
}