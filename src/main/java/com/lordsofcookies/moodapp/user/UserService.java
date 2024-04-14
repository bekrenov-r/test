package com.lordsofcookies.moodapp.user;

import com.lordsofcookies.moodapp.model.TelegramUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final TelegramUserRepository telegramUserRepository;

    public void register(RegistrationRequest request) {
        TelegramUser tgUser = new TelegramUser(
                null,
                request.telegramId(),
                request.firstName(),
                request.lastName(),
                request.username(),
                LocalDateTime.now()
        );
        telegramUserRepository.save(tgUser);
    }
}
