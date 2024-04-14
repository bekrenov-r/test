package com.lordsofcookies.moodapp;

import com.lordsofcookies.moodapp.model.TelegramUser;
import com.lordsofcookies.moodapp.user.TelegramUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrentUserUtil {
    private final TelegramUserRepository telegramUserRepository;

    public TelegramUser getCurrentUser(){
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return telegramUserRepository.findByTelegramId(userId);
    }
}
