package com.lordsofcookies.moodapp.user;

import com.lordsofcookies.moodapp.model.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelegramUserRepository extends JpaRepository<TelegramUser, Long> {
    boolean existsByTelegramId(String telegramId);
    TelegramUser findByTelegramId(String telegramId);
}
