package net.vershinin.chat.controller;

import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@FrameworkEndpoint
public class RevokeTokenEndpoint {

    @Resource(name = "consumerTokenServices")
    private ConsumerTokenServices tokenServices;

    @DeleteMapping("/oauth/token")
    @ResponseBody
    public void signOut(@RequestHeader(value = "Authorization") String token){
        if(token != null && token.contains("Bearer")){
            tokenServices.revokeToken(token.substring("Bearer".length() + 1));
        }
    }
}