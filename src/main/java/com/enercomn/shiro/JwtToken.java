package com.enercomn.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Created by zhaojy on 2018-02-01.
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
