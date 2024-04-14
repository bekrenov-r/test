package com.lordsofcookies.moodapp.config;

import com.lordsofcookies.moodapp.model.TelegramUser;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class MockAuthenticationToken extends AbstractAuthenticationToken {
    private final TelegramUser telegramUser;

    public MockAuthenticationToken(Collection<? extends GrantedAuthority> authorities, TelegramUser telegramUser) {
        super(authorities);
        this.telegramUser = telegramUser;
        this.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return telegramUser;
    }

    @Override
    public String getName(){
        return telegramUser.getTelegramId();
    }
}
