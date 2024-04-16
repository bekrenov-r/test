package com.lordsofcookies.moodapp.user;

import com.lordsofcookies.moodapp.model.TelegramUser;
import com.lordsofcookies.moodapp.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final TelegramUserRepository telegramUserRepository;
    private final JwtProvider jwtProvider;

    public String createUser(UserRequest request) {
        if(telegramUserRepository.existsByTelegramId(request.telegramId())){
            TelegramUser updatedUser = updateUser(request);
            return jwtProvider.generateToken(updatedUser);
        }
        TelegramUser tgUser = new TelegramUser(
                null,
                request.telegramId(),
                request.firstName(),
                request.lastName(),
                request.username(),
                LocalDateTime.now()
        );
        TelegramUser savedUser = telegramUserRepository.save(tgUser);
        return jwtProvider.generateToken(savedUser);
    }

    public TelegramUser updateUser(UserRequest request){
        TelegramUser user = telegramUserRepository.findByTelegramId(request.telegramId());
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setUsername(request.username());
        return telegramUserRepository.save(user);
    }
}
