package com.example.lab4server.sequrity.service;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.ArrayList;

public class SecurityToken extends AbstractAuthenticationToken {
    private final SecurityUser securityUser;

    public SecurityToken(SecurityUser securityUser) {
        super(new ArrayList<>());
        this.securityUser = securityUser;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return securityUser;
    }
}
