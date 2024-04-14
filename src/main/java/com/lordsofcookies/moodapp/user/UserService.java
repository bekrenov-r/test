package com.lordsofcookies.moodapp.user;

import com.lordsofcookies.moodapp.model.TelegramUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final TelegramUserRepository telegramUserRepository;

    public void createUser(UserRequest request) {
        if(telegramUserRepository.existsByTelegramId(request.telegramId())){
            updateUser(request);
            return;
        }
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

    public void updateUser(UserRequest request){
        TelegramUser user = telegramUserRepository.findByTelegramId(request.telegramId());
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setUsername(request.username());
        telegramUserRepository.save(user);
    }
}
