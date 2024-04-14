package com.lordsofcookies.moodapp.post;

import com.lordsofcookies.moodapp.user.TelegramUserResponse;

import java.time.LocalDateTime;
import java.util.UUID;

public record PostResponse(
        UUID id,
        TelegramUserResponse user,
        int emojiId,
        LocalDateTime createdAt
) { }
